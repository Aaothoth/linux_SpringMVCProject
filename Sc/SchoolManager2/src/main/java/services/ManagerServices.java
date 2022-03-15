package services;

import dao.*;
import model.*;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.springframework.aop.framework.AopContext.currentProxy;

@Service
public class ManagerServices implements ManagerService//, Serializable
{
    @Resource
    private UserRepository userRepository;

    @Resource
    private TeacherCourseRepository teacherCourseRepository;

    @Resource
    private TeacherRepository teacherRepository;

    @Resource
    private CollegeRepository collegeRepository;

    @Resource
    private CourseRepository courseRepository;

    @Resource
    private CourseWeekRepository courseWeekRepository;

    @Resource
    private CourseDayRepository courseDayRepository;

    @Resource
    private ClassroomRepository classroomRepository;


    public String showUsers(Model model)
    {
        model.addAttribute("listUsers", this.userRepository.findAll());
        return "manager/showUsers";
    }

    public String showColleges(Model model)
    {
        model.addAttribute("colleges", this.collegeRepository.findAll());
        return "manager/showColleges";
    }

    public String showCollegeTeachers(Model model, int id)
    {
        College college = this.collegeRepository.findByCollegeNumber(id);
        model.addAttribute("teachers", this.teacherRepository.findByCollegeNumber(college));

        return "manager/showCollegeTeachers";
    }

    @Transactional
    public String removeUserAndTeacher(int userId)
    {
        //BeanUtils.copyProperties()
        User user = this.userRepository.findByUserNumber(userId);
        Teacher teacher = this.teacherRepository.findByTeacherNumber(user.getTeacher().getTeacherNumber());
        List<TeacherCourse> teacherCourses = this.teacherCourseRepository
                .findAllByTeacherNumber(user.getTeacher());

        if (user != null && teacher != null)
        {
            for (TeacherCourse temp : teacherCourses)
            {
                this.teacherCourseRepository.delete(temp);
            }
            this.teacherRepository.delete(teacher);
            this.userRepository.delete(user);

            return "redirect: showUsers";
        }

        System.out.println("删除失败");
        return "redirect: manager/showUsers";
    }

    public String addUserAndTeacherGet(Model model)
    {
        model.addAttribute("colleges", this.collegeRepository.findAll());
        model.addAttribute("modelTeacher", new Teacher());

        return "manager/addUserAndTeacher";
    }

    public String addUserAndTeacherPost(Teacher teacher, Model model)
    {
        teacher = Teacher.createTeacher(teacher.getTeacherName(), teacher.getCollegeNumber());

        this.teacherRepository.save(teacher);
        this.userRepository.save(User.createUser(teacher));

        model.addAttribute("user", this.userRepository.findUserByOrderByUserNumberDesc().iterator().next());

        return "manager/addedHelp";
    }

    public String autoSchedule(int id, Model model)//TODO 逻辑不全 应判断重复上课 教室 专业
    {
        College college = this.collegeRepository.findByCollegeNumber(id);
        List<Course> courseList = this.courseRepository.findByCollegeNumber(college);

        List dayList = new ArrayList();
        Random random = new Random();
        int day = 1;

        while (day <= 5)
        {
            int aClass = 1;
            List tempList = new ArrayList();

            while (aClass <= 4)
            {
                int willCourse = random.nextInt(12);

                while (willCourse == 0 || willCourse == 4 ||
                        willCourse == 8 || willCourse == 11 || willCourse == 6)
                {
                    willCourse = random.nextInt(12);
                }

                List<TeacherCourse> tempCourse = this.teacherCourseRepository
                        .findByCourseNumber(this.courseRepository.findByCourseNumber(willCourse));

                if (tempCourse.size() == 1)
                {
                    tempList.add(tempCourse.iterator().next());
                }
                else if (tempCourse.size() > 1)
                {
                    tempList.add(tempCourse.toArray()[1]);
                }

                aClass++;
            }

            CourseDay courseDay = CourseDay.createCourseDay(tempList);
            dayList.add(courseDay);
            this.courseDayRepository.save(courseDay);
            day++;
        }

        CourseWeek courseWeek = CourseWeek.createCourseWeek(dayList);
        System.out.println(courseWeek);

        this.courseWeekRepository.save(courseWeek);

        model.addAttribute("week", courseWeek);

        return "manager/showWeekCourse";
    }

    public String showCoursesColleges(Model model)
    {
        model.addAttribute("colleges", this.collegeRepository.findAll());
        return "manager/showCoursesColleges";
    }

    public String showCourses(int id, Model model)
    {
        College college = this.collegeRepository.findByCollegeNumber(id);
        model.addAttribute("courses", this.courseRepository.findByCollegeNumber(college));

        return "manager/showCourses";
    }

    public String showClassroom(Model model)
    {
        model.addAttribute("classrooms", this.classroomRepository.findAll());
        return "manager/showClassroom";
    }

    public String setUserGet(Model model, int id)
    {
        ManagerServices managerServices = (ManagerServices) AopContext.currentProxy();
        managerServices.findUserByUserNumber(id);

        model.addAttribute("user", this.userRepository.findByUserNumber(id));
        return "manager/setUser";
    }

    public String setUserPost(int userId, String password, Model model)
    {
        this.userRepository.updateUserToUserNumberByPassword(userId, password);
        model.addAttribute("user", this.userRepository.findByUserNumber(userId));

        return "manager/setUserPassworded";
    }


    @Cacheable(cacheNames = "userCache", key = "#id")
    public User findUserByUserNumber(int id)
    {
        System.out.println("开始缓存");
        return this.userRepository.findByUserNumber(id);
    }

    @Autowired
    private RedisTemplate redisTemplate;
}

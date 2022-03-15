package controller;

import model.Teacher;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import services.ManagerServices;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/manager")
public class ManagerController
{
    @Resource
    private ManagerServices managerServices;

    @RequestMapping(value = "/showUsers", method = RequestMethod.GET)
    public String showUsers(Model model)
    {
        return this.managerServices.showUsers(model);
    }

    @RequestMapping(value = "/showColleges", method = RequestMethod.GET)
    public String showColleges(Model model)
    {
        return this.managerServices.showColleges(model);
    }

    @RequestMapping(value = "/showCollegeTeachers", method = RequestMethod.GET)
    public String showCollegeTeachers(@RequestParam("cId") int id,
                              Model model)
    {
        return this.managerServices.showCollegeTeachers(model, id);
    }

    @RequestMapping(value = "/removeUserAndTeacher", method = RequestMethod.GET)
    public String removeUserAndTeacher(@RequestParam("userId") int userId)
    {
        return this.managerServices.removeUserAndTeacher(userId);
    }

    @RequestMapping(value = "/addUserAndTeacher", method = RequestMethod.GET)
    public String addUserAndTeacherGet(Model model)
    {
        return this.managerServices.addUserAndTeacherGet(model);
    }

    @RequestMapping(value = "/addUserAndTeacher", method = RequestMethod.POST)
    public String addUserAndTeacherPost(@ModelAttribute Teacher teacher,
                                        Model model)
    {
        return this.managerServices.addUserAndTeacherPost(teacher, model);
    }

    @RequestMapping(value = "/showCoursesColleges", method = RequestMethod.GET)
    public String showCoursesColleges(Model model)
    {
        return this.managerServices.showCoursesColleges(model);
    }

    @RequestMapping(value = "/showCourses", method = RequestMethod.GET)
    public String showCourses(@RequestParam("cId") int id,
                              Model model)
    {
        return this.managerServices.showCourses(id, model);
    }

    @RequestMapping(value = "/autoSchedule", method = RequestMethod.GET)
    public String autoSchedule(@RequestParam("cId") int id,
                               Model model)
    {
        return this.managerServices.autoSchedule(id, model);
    }

    @RequestMapping(value = "/showClassroom", method = RequestMethod.GET)
    public String showClassroom(Model model)
    {
        return this.managerServices.showClassroom(model);
    }

    @RequestMapping(value = "/setUser", method = RequestMethod.GET)
    public String setUserGet(@RequestParam("userId") int id,
                             Model model)
    {
        return this.managerServices.setUserGet(model, id);
    }

    @RequestMapping(value = "/setUser", method = RequestMethod.POST)
    public String setUserPost(int userId, String password, Model model)
    {
        return this.managerServices.setUserPost(userId, password, model);
    }
}

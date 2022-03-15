package dao;

import model.Course;
import model.Teacher;
import model.TeacherCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherCourseRepository extends JpaRepository<TeacherCourse, Integer>
{
    List<TeacherCourse> findAllByTeacherNumber(Teacher teacherNumber);
    List<TeacherCourse> findByCourseNumber(Course courseNumber);
}

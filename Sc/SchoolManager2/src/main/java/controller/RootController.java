package controller;

import dao.CollegeRepository;
import dao.CourseRepository;
import dao.TeacherCourseRepository;
import dao.TeacherRepository;
import model.College;
import model.Course;
import model.Teacher;
import model.TeacherCourse;
import model.temp.Encode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class RootController
{
    @RequestMapping(method = RequestMethod.GET)
    public String rootIndex()
    {
        return "default";
    }
}

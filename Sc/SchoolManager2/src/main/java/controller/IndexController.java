package controller;

import dao.*;
import model.Course;
import model.Teacher;
import model.TeacherCourse;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import services.IndexService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/index")
public class IndexController
{
    @Resource
    private IndexService indexService;


    @RequestMapping(method = RequestMethod.GET)
    public String indexLoginGet(Model model)
    {
        return this.indexService.indexLoginGet(model);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String indexLoginPost(@ModelAttribute("user") User user,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
    {
        return this.indexService.indexLoginPost(user, request, response);
    }

    @RequestMapping(value = "/Index", method = RequestMethod.GET)
    public String index()
    {
        return "login/Index";
    }

    @RequestMapping(value = "/showTeacher", method = RequestMethod.GET)
    public String showTeacherSome(@RequestParam("userId") int id,
                                  Model model)
    {
        return this.indexService.showTeacherSome(id, model);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request,
                         HttpServletResponse response)
    {
        return this.indexService.logout(request, response);
    }
}

package services;

import dao.UserRepository;
import model.Teacher;
import model.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class IndexService
{
    @Resource
    private UserRepository userRepository;

    public String indexLoginGet(Model model)
    {
        model.addAttribute("user", new User());
        return "login/loginIndex";
    }

    public String indexLoginPost(User user, HttpServletRequest request,
                                 HttpServletResponse response)
    {
        if (user != null)
        {
            User tempUser = this.userRepository.findByUserNumber(user.getUserNumber());

            if (tempUser != null)
            {
                String sqlPassword = tempUser.getPassword();

                if (sqlPassword.equals(user.getPassword()))
                {
                    String competence = tempUser.getCompetence();
                    String teacherName = tempUser.getTeacher().getTeacherName();
                    int userNumber = tempUser.getUserNumber();

                    if ((request.getCookies() == null) ||
                            !(request.getCookies()[0].getName().equals("cookUser")))
                    {
                        System.out.println("核对正确");
                        Cookie cookie = new Cookie("cookUser",
                                userNumber + ":::" + competence + ":::" + teacherName);
                        System.out.println(cookie.getValue());
                        cookie.setMaxAge(60 * 24);
                        cookie.setPath("/");

                        response.addCookie(cookie);

                        return "redirect: index/Index";
                    }

                    System.out.println("存在cookie");
                    return "redirect: index/Index";
                }

                System.out.println("密码错误");
                return "login/loginIndex";
            }

            System.out.println("查无用户");
            return "login/loginIndex";
        }
        return "login/loginIndex";
    }

    public String showTeacherSome(int id, Model model)
    {
        User user = this.userRepository.findByUserNumber(id);
        Teacher teacher = user.getTeacher();

        model.addAttribute("teacher", teacher);
        model.addAttribute("user", user);

        return "/login/lookMeSome";
    }

    public String logout(HttpServletRequest request, HttpServletResponse response)
    {
        for (Cookie cookie : request.getCookies())
        {
            cookie.setMaxAge(0);
            cookie.setValue(null);
            cookie.setPath("/");

            response.addCookie(cookie);
        }

        return "redirect: /SchoolManager2_war/index";
    }
}

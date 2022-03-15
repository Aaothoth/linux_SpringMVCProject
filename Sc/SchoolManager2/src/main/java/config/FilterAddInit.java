package config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

//当以请求方式提交出现乱码时 注册一个filter到web应用 并修改一些属性 ：如下
public class FilterAddInit implements WebApplicationInitializer
{
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException
    {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();

        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);

        FilterRegistration.Dynamic filterRegistration =
                servletContext.addFilter("ecodingFilter", filter);

        filterRegistration.addMappingForUrlPatterns(null, false, "/*");
    }
}

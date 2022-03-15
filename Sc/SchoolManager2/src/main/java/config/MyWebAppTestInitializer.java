package config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyWebAppTestInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
    //设置ContextLoaderListener监听器创建的上下文的bean
    @Override
    protected Class<?>[] getRootConfigClasses()
    {
        return new Class<?>[] {RootConfig.class};
    }

    //设置dispacherServlet上下文的bean
    @Override
    protected Class<?>[] getServletConfigClasses()
    {
        return new Class<?>[] {WebConfig.class};
    }

    @Override
    protected String[] getServletMappings()
    {
        return new String[] {"/", "*.service"};
    }
}

package config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.util.function.Supplier;
import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@ComponentScan("controller")
@EnableSpringDataWebSupport			//在web层要开启这个注解以保证jpa运行
public class WebConfig implements WebMvcConfigurer
{
	//thymeleaf视图解析器 要在视图解析器中设置中文编码才会对解析的页面生效
	@Bean
	public ViewResolver viewResolver(TemplateEngine templateEngine)
	{
		System.out.println("nihaonihao");
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine);
		viewResolver.setCharacterEncoding("UTF-8");

		return viewResolver;
	}

	//模板引擎
	@Bean
	public TemplateEngine templateEngine(ITemplateResolver iTemplateResolver)
	{
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(iTemplateResolver);

		return templateEngine;
	}

	//模板解析器 未知原因以完毕 可能是idea或者jvm错误导致的null 暂未解决
	//感觉像是这个类不被加载了 同时有一个问题 debug模式中 时不时会出现方法断点后程序卡死 这个是不是和null有关
	@Bean
	public ITemplateResolver iTemplateResolver()
	{
		System.out.println(ContextLoader.getCurrentWebApplicationContext().getServletContext());
		ServletContextTemplateResolver iTemplateResolver =
				new ServletContextTemplateResolver
						(ContextLoader.getCurrentWebApplicationContext().getServletContext());

		iTemplateResolver.setCharacterEncoding("UTF-8");
		iTemplateResolver.setPrefix("/WEB-INF/");
		iTemplateResolver.setSuffix(".html");
		iTemplateResolver.setTemplateMode(TemplateMode.HTML);

		return iTemplateResolver;
	}

	@Bean
   	public MessageSource messageSource()
	{
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

		messageSource.setBasename("classpath:messages");
		messageSource.setCacheSeconds(10);
		messageSource.setDefaultEncoding("utf-8");

		return messageSource;
	}
}

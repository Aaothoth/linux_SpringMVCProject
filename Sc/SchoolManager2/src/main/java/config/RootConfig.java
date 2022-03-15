package config;

import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "java",
			   excludeFilters = {@Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})
@Import({JpaConfig.class, ServiceConfig.class, RedisConfig.class, ActiveMQConfig.class, RabbitMQConfig.class,
	     ListenWebAppConfig.class})
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
public class RootConfig
{
	@Bean
	public HandlerMapping handlerMapping()
	{
		SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
		Properties properties = new Properties();

		properties.setProperty("/manager.service", "httpInvokerServiceExporter");
		handlerMapping.setMappings(properties);

		return handlerMapping;
	}
}

package config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig
{
    @Bean(name = "rabbitConnectionFactory")
    public CachingConnectionFactory connectionFactory()
    {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public RabbitAdmin rabbitAdmin()
    {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean(name = "rabbitMQTemplate")
    public RabbitTemplate template()
    {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue queue()
    {
        return new Queue("tempQueue", true, false, false);
    }

    @Bean
    public DirectExchange directExchange()
    {
        return new DirectExchange("tempExchange", true, false);
    }

    @Bean
    public Binding directBinding()
    {
        return BindingBuilder.bind(queue())
                .to(directExchange())
                .with("temp");
    }

}

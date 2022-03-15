package config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import services.activeMQListener.TempMessageListener;


@Configuration
@EnableJms
@ComponentScan(basePackages = {"services.activeMQListener"})
public class ActiveMQConfig
{
    @Bean(name = "activeMQConnectionFactory")
    public ActiveMQConnectionFactory connectionFactory()
    {
        ActiveMQConnectionFactory connectionFactory =  new ActiveMQConnectionFactory();

        connectionFactory.setBrokerURL("tcp://localhost:61616");
        connectionFactory.setUserName("admin");
        connectionFactory.setPassword("admin");

        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate()
    {
        JmsTemplate jmsTemplate = new JmsTemplate();

        jmsTemplate.setDefaultDestinationName("tempQueue");
        jmsTemplate.setConnectionFactory(connectionFactory());

        return jmsTemplate;
    }

    @Bean(name = "jmsListenerContainerFactory")
    public DefaultJmsListenerContainerFactory
    listenerContainer(ActiveMQConnectionFactory connectionFactory,
                      TempMessageListener messageListener)
    {
        DefaultJmsListenerContainerFactory listenerContainer = new DefaultJmsListenerContainerFactory();
        listenerContainer.setConnectionFactory(connectionFactory);

        return listenerContainer;
    }

    @Bean
    public MappingJackson2MessageConverter messageConverter()
    {
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setTargetType(MessageType.OBJECT);

        return messageConverter;
    }
}

package services.activeMQListener;

import model.Classroom;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

@Component
public class TempMessageListener
{
    @JmsListener(destination = "tempQueue")
    public void temp(Message message)
    {
        if (this.isTextMessage(message))
        {
            try
            {
                System.out.println(this.getTextMessage(message).getText());
            }
            catch (JMSException e)
            {
            }
        }
        else
        {
            System.out.println("tempCuowu");
        }
    }

    public boolean isTextMessage(Message message)
    {
        if (message instanceof TextMessage)
        {
            return true;
        }

        return false;
    }

    public TextMessage getTextMessage(Message message)
    {
        return ((TextMessage) message);
    }
}


package services.activeMQListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Classroom;
import org.apache.activemq.command.ActiveMQBytesMessage;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

@Component
public class ClassroomMessageListener
{
    @JmsListener(destination = "objectQueue")
    public void classroomMessage(Message message)
    {
        if (this.isObjectMessage(message))
        {
            try
            {
                ObjectMessage objectMessage = (ObjectMessage) message;
                System.out.println(((ObjectMessage) message).getObject());
            }
            catch (JMSException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            ObjectMapper objectMapper = new ObjectMapper();
        }
    }


    public boolean isObjectMessage(Message message)
    {
        if (message instanceof ObjectMessage)
        {
            return true;
        }

        return false;
    }

    public ObjectMessage getObjectMessage(Message message)
    {
        return ((ActiveMQObjectMessage) message);
    }

    public Classroom getClassroom(Message message) throws JMSException
    {
        return (Classroom) this.getObjectMessage(message).getObject();
    }
}

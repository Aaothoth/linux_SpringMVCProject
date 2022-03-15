package controller;

import dao.ClassroomRepository;
import dao.TeacherRepository;
import dao.UserRepository;
import model.Classroom;
import model.Teacher;
import model.User;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;

import org.springframework.jms.core.JmsOperations;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import services.exception.ClassroomNotFoundException;
import services.exception.UserNotFoundException;

import javax.annotation.Resource;
import java.net.URI;

@RestController
public class TempController
{
    @Resource
    private UserRepository userRepository;

    @Resource
    private ClassroomRepository classroomRepository;

    @Resource
    private TeacherRepository teacherRepository;

    @Resource
    private StopWatch stopWatch;

    @Autowired
    @Qualifier("jmsTemplate")
    private JmsOperations jmsTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @RequestMapping(value = "/user/{id}",
                    method = RequestMethod.GET)
    public User user(@PathVariable int id)
    {
        this.stopWatch.start("开始查询user");
        User user = this.userRepository.findByUserNumber(id);

        if (user == null)
        {
            throw new UserNotFoundException(id);
        }
        this.stopWatch.stop();

        return user;
    }

    @RequestMapping(value = "/classroom/{id}",
                    method = RequestMethod.GET)
    public Classroom classroom(@PathVariable int id)
    {
        Classroom classroom = this.classroomRepository.findByClassroomNumber(id);

        //this.jmsTemplate.convertAndSend("objectQueue", classroom);
        this.rabbitTemplate.convertAndSend("temp", "你好");

        System.out.println((String) this.rabbitTemplate.receiveAndConvert("tempQueue"));

        if (Classroom.isNull(classroom))
        {
            throw new ClassroomNotFoundException(id);
        }

        return classroom;
    }


    @RequestMapping(value = "/postTemp",
                    method = RequestMethod.POST)
    public ResponseEntity<Classroom> postUser(@RequestBody Classroom classroom,
                                              UriComponentsBuilder uriComponentsBuilder)
    {
        Classroom savedClassroom = this.classroomRepository.save(classroom);
        URI uri = uriComponentsBuilder
                    .path("/classroom/")
                    .path(String.valueOf(savedClassroom.getClassroomNumber()))
                    .build().toUri();

        return ResponseEntity
                .created(uri)
                .body(savedClassroom);
    }

    @RequestMapping(value = "/teacher/{id}",
                    method = RequestMethod.GET)
    public Teacher teacher(@PathVariable int id)
    {
        return this.teacherRepository.findByTeacherNumber(id);
    }

    @RequestMapping(value = "/putClassroom",
                    method = RequestMethod.PUT)
    public void putClassroom(@RequestBody Classroom classroom)
    {
        this.classroomRepository.updateByClassroomNumber(classroom.getClassroomNumber(),
                classroom.getClassroomName());
    }

    @RequestMapping(value = "/classroom/delete/{classroomNumber}",
                    method = RequestMethod.DELETE)
    public void deleteClassroom(@PathVariable int classroomNumber)
    {
        this.classroomRepository.deleteById(classroomNumber);
    }
}

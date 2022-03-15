package services.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import services.exception.ClassroomNotFoundException;
import services.exception.UserNotFoundException;
import services.util.Error;

@ControllerAdvice
public class TempControllerExceptionHandler
{
    //TODO 那么 这个方法并没有指定返回的数据类型
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public @ResponseBody Error userNotFound(UserNotFoundException e)
    {
        return new Error(404, e.getUserId() + "未找到");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ClassroomNotFoundException.class)
    public @ResponseBody Error classroomNotFound(ClassroomNotFoundException e)
    {
        return new Error(404, e.getClassroomId() + "未找到");
    }
}


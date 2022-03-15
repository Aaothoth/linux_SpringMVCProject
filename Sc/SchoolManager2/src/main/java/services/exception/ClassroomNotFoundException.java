package services.exception;

public class ClassroomNotFoundException extends RuntimeException
{
    private int classroomId;

    public ClassroomNotFoundException(int classroomId)
    {
        this.classroomId = classroomId;
    }

    public void setClassroomId(int classroomId)
    {
        this.classroomId = classroomId;
    }

    public int getClassroomId()
    {
        return classroomId;
    }

    @Override
    public String toString()
    {
        return "ClassroomNotFoundException{" +
                "classroomId=" + classroomId +
                '}';
    }
}

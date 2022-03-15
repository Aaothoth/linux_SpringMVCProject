package model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "classroom")//教室
public class Classroom implements Serializable
{
    @Id
    @Column(name = "classroomNumber")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classroomNumber;

    @Column(name = "classroomName")
    private String classroomName;

    public static Classroom createClassroom(String classroomName)
    {
        Classroom classroom = new Classroom();

        classroom.classroomName = classroomName;

        return classroom;
    }


    public void setClassroomNumber(int classroomNumber)
    {
        this.classroomNumber = classroomNumber;
    }

    public void setClassroomName(String classroomName)
    {
        this.classroomName = classroomName;
    }

    public String getClassroomName()
    {
        return this.classroomName;
    }

    public int getClassroomNumber()
    {
        return this.classroomNumber;
    }

    public String toString()
    {
        return "classroomName : " + this.classroomName +
               " classroomNumber : " + this.classroomNumber;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }

        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        Classroom classroom = (Classroom) o;

        return classroomNumber == classroom.classroomNumber &&
                Objects.equals(classroomName, classroom.classroomName);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(classroomNumber, classroomName);
    }

    public static boolean isNull(Classroom classroom)
    {
        return classroom == null ? true : false;
    }
}

package model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Teacher")
public class Teacher implements Serializable
{
    @Id
    @Column(name = "teacherNumber")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teacherNumber;

    @Column(name = "teacherName", length = 20)
    private String teacherName;

    @OneToOne
    @JoinColumn(name = "collegeNumber")
    private College collegeNumber;

    public static Teacher createTeacher(String teacherName, College collegeNumber)
    {
        Teacher teacher = new Teacher();

        teacher.teacherName = teacherName;
        teacher.collegeNumber = collegeNumber;

        return teacher;
    }

    public void setCollegeNumber(College collegeNumber)
    {
        this.collegeNumber = collegeNumber;
    }

    public void setTeacherName(String teacherName)
    {
        this.teacherName = teacherName;
    }

    public void setTeacherNumber(int teacherNumber)
    {
        this.teacherNumber = teacherNumber;
    }

    public int getTeacherNumber()
    {
        return teacherNumber;
    }

    public College getCollegeNumber()
    {
        return collegeNumber;
    }

    public String getTeacherName()
    {
        return teacherName;
    }

    @Override
    public String toString()
    {
        return "Teacher{" +
                "teacherNumber=" + teacherNumber +
                ", teacherName='" + teacherName + '\'' +
                ", collegeNumber=" + collegeNumber +
                '}';
    }
}

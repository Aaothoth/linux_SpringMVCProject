package model;


import javax.persistence.*;

@Entity
@Table(name = "TeacherCourse")//任课表
public class TeacherCourse
{
    @Id
    @Column(name = "teacherCourseNumber")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teacherCourseNumber;

    @OneToOne
    @JoinColumn(name = "teacherNumber")
    private Teacher teacherNumber;

    @OneToOne
    @JoinColumn(name = "courseNumber")
    private Course courseNumber;


    public static TeacherCourse createTeacherCourse(Teacher teacherNumber, Course courseNumber)
    {
        TeacherCourse teacherCourse = new TeacherCourse();

        teacherCourse.teacherNumber = teacherNumber;
        teacherCourse.courseNumber = courseNumber;

        return teacherCourse;
    }


    public void setTeacherNumber(Teacher teacherNumber)
    {
        this.teacherNumber = teacherNumber;
    }

    public void setTeacherCourseNumber(int teacherCourseNumber)
    {
        this.teacherCourseNumber = teacherCourseNumber;
    }

    public void setCourseNumber(Course courseNumber)
    {
        this.courseNumber = courseNumber;
    }


    public Course getCourseNumber()
    {
        return courseNumber;
    }

    public int getTeacherCourseNumber()
    {
        return teacherCourseNumber;
    }

    public Teacher getTeacherNumber()
    {
        return teacherNumber;
    }

    @Override
    public String toString()
    {
        return "TeacherCourse{" +
                "teacherCourseNumber=" + teacherCourseNumber +
                ", teacherNumber=" + teacherNumber +
                ", courseNumber=" + courseNumber +
                '}';
    }
}

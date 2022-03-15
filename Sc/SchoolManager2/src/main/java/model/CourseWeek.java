package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courseWeek")
public class CourseWeek
{
    @Id
    @Column(name = "courseWeekNumber")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseWeekNumber;

    @OneToOne
    @JoinColumn(name = "courseDay1")
    private CourseDay courseDay1;

    @OneToOne
    @JoinColumn(name = "courseDay2")
    private CourseDay courseDay2;

    @OneToOne
    @JoinColumn(name = "courseDay3")
    private CourseDay courseDay3;

    @OneToOne
    @JoinColumn(name = "courseDay4")
    private CourseDay courseDay4;

    @OneToOne
    @JoinColumn(name = "courseDay5")
    private CourseDay courseDay5;

    public static CourseWeek createCourseWeek(CourseDay courseDay1, CourseDay courseDay2, CourseDay courseDay3,
                                              CourseDay courseDay4, CourseDay courseDay5)
    {
        CourseWeek courseWeek = new CourseWeek();

        courseWeek.courseDay1 = courseDay1;
        courseWeek.courseDay2 = courseDay2;
        courseWeek.courseDay3 = courseDay3;
        courseWeek.courseDay4 = courseDay4;
        courseWeek.courseDay5 = courseDay5;

        return courseWeek;
    }

    public static CourseWeek createCourseWeek(List list)
    {
        CourseWeek courseWeek = new CourseWeek();

        courseWeek.courseDay1 = (CourseDay) list.toArray()[0];
        courseWeek.courseDay2 = (CourseDay) list.toArray()[1];
        courseWeek.courseDay3 = (CourseDay) list.toArray()[2];
        courseWeek.courseDay4 = (CourseDay) list.toArray()[3];
        courseWeek.courseDay5 = (CourseDay) list.toArray()[4];

        return courseWeek;
    }


    public void setCourseDay1(CourseDay courseDay1)
    {
        this.courseDay1 = courseDay1;
    }

    public void setCourseDay2(CourseDay courseDay2)
    {
        this.courseDay2 = courseDay2;
    }

    public void setCourseDay3(CourseDay courseDay3)
    {
        this.courseDay3 = courseDay3;
    }

    public void setCourseDay4(CourseDay courseDay4)
    {
        this.courseDay4 = courseDay4;
    }

    public void setCourseDay5(CourseDay courseDay5)
    {
        this.courseDay5 = courseDay5;
    }

    public void setCourseWeekNumber(int courseWeekNumber)
    {
        this.courseWeekNumber = courseWeekNumber;
    }


    public CourseDay getCourseDay1()
    {
        return courseDay1;
    }

    public CourseDay getCourseDay2()
    {
        return courseDay2;
    }

    public CourseDay getCourseDay3()
    {
        return courseDay3;
    }

    public CourseDay getCourseDay4()
    {
        return courseDay4;
    }

    public CourseDay getCourseDay5()
    {
        return courseDay5;
    }

    public int getCourseWeekNumber()
    {
        return courseWeekNumber;
    }

    @Override
    public String toString()
    {
        return "CourseWeek{" +
                "courseWeekNumber=" + courseWeekNumber +
                ", courseDay1=" + courseDay1 +
                ", courseDay2=" + courseDay2 +
                ", courseDay3=" + courseDay3 +
                ", courseDay4=" + courseDay4 +
                ", courseDay5=" + courseDay5 +
                '}';
    }
}

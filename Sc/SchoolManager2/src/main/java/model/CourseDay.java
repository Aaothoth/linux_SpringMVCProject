package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courseDay")
public class CourseDay
{
    @Id
    @Column(name = "dayNumber")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dayNumber;

    @OneToOne
    @JoinColumn(name = "quartersOneTwo")
    private TeacherCourse quartersOneTwo;                       //一二节课

    @OneToOne
    @JoinColumn(name = "quartersThreeFour")
    private TeacherCourse quartersThreeFour;                    //三四节课

    @OneToOne
    @JoinColumn(name = "quartersFiveSix")
    private TeacherCourse quartersFiveSix;                      //五六节课

    @OneToOne
    @JoinColumn(name = "quartersSevenEight")
    private TeacherCourse quartersSevenEight;                   //七八节课

    public static CourseDay createCourseDay(TeacherCourse quartersOneTwo, TeacherCourse quartersThreeFour,
                                            TeacherCourse quartersFiveSix, TeacherCourse quartersSevenEight)
    {
        CourseDay courseDay = new CourseDay();

        courseDay.quartersOneTwo = quartersOneTwo;
        courseDay.quartersThreeFour = quartersThreeFour;
        courseDay.quartersFiveSix = quartersFiveSix;
        courseDay.quartersSevenEight = quartersSevenEight;

        return courseDay;
    }

    public static CourseDay createCourseDay(List list)
    {
        CourseDay courseDay = new CourseDay();

        courseDay.quartersOneTwo = (TeacherCourse) list.toArray()[0];
        courseDay.quartersThreeFour = (TeacherCourse) list.toArray()[1];
        courseDay.quartersFiveSix = (TeacherCourse) list.toArray()[2];
        courseDay.quartersSevenEight = (TeacherCourse) list.toArray()[3];

        return courseDay;
    }

    public void setDayNumber(int dayNumber)
    {
        this.dayNumber = dayNumber;
    }

    public void setQuartersFiveSix(TeacherCourse quartersFiveSix)
    {
        this.quartersFiveSix = quartersFiveSix;
    }

    public void setQuartersOneTwo(TeacherCourse quartersOneTwo)
    {
        this.quartersOneTwo = quartersOneTwo;
    }

    public void setQuartersSevenEight(TeacherCourse quartersSevenEight)
    {
        this.quartersSevenEight = quartersSevenEight;
    }

    public void setQuartersThreeFour(TeacherCourse quartersThreeFour)
    {
        this.quartersThreeFour = quartersThreeFour;
    }


    public int getDayNumber()
    {
        return dayNumber;
    }

    public TeacherCourse getQuartersFiveSix()
    {
        return quartersFiveSix;
    }

    public TeacherCourse getQuartersOneTwo()
    {
        return quartersOneTwo;
    }

    public TeacherCourse getQuartersSevenEight()
    {
        return quartersSevenEight;
    }

    public TeacherCourse getQuartersThreeFour()
    {
        return quartersThreeFour;
    }

    @Override
    public String toString()
    {
        return "CourseDay{" +
                "dayNumber=" + dayNumber +
                ", quartersOneTwo=" + quartersOneTwo +
                ", quartersThreeFour=" + quartersThreeFour +
                ", quartersFiveSix=" + quartersFiveSix +
                ", quartersSevenEight=" + quartersSevenEight +
                '}';
    }
}

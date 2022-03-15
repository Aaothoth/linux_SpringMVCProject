package model;


import javax.persistence.*;

@Entity
@Table(name = "Course")//课程
public class Course
{
    @Id
    @Column(name = "courseNumber")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseNumber;

    @Column(name = "courseName")
    private String courseName;

    @OneToOne
    @JoinColumn(name = "collegeNumber")
    private College collegeNumber;

    public static Course createCourse(String courseName, College collegeNumber)
    {
        Course course = new Course();

        course.courseName = courseName;
        course.collegeNumber = collegeNumber;

        return course;
    }

    public void setCollegeNumber(College collegeNumber)
    {
        this.collegeNumber = collegeNumber;
    }

    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }

    public void setCourseNumber(int courseNumber)
    {
        this.courseNumber = courseNumber;
    }

    public College getCollegeNumber()
    {
        return collegeNumber;
    }

    public int getCourseNumber()
    {
        return courseNumber;
    }

    public String getCourseName()
    {
        return courseName;
    }

    @Override
    public String toString()
    {
        return "Course{" +
                "courseNumber=" + courseNumber +
                ", courseName='" + courseName + '\'' +
                ", collegeNumber=" + collegeNumber +
                '}';
    }
}

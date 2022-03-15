package model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "College")//学院
public class College implements Serializable
{
    @Id
    @Column(name = "collegeNumber")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int collegeNumber;

    @Column(name = "collegeName", length = 20)
    private String collegeName;

    public static College createCollege(String collegeName)
    {
        College college = new College();

        college.collegeName = collegeName;

        return college;
    }

    public int getCollegeNumber()
    {
        return collegeNumber;
    }

    public String getCollegeName()
    {
        return collegeName;
    }

    public void setCollegeNumber(int collegeNumber)
    {
        this.collegeNumber = collegeNumber;
    }

    public void setCollegeName(String collegeName)
    {
        this.collegeName = collegeName;
    }

    @Override
    public String toString()
    {
        return "College{" +
                "collegeNumber=" + collegeNumber +
                ", collegeName='" + collegeName + '\'' +
                '}';
    }
}

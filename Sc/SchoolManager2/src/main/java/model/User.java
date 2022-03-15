package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable
{
    @Id
    @Column(name = "userNumber")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userNumber;

    @Column(name = "password")
    private String password = "123456";

    @Column(name = "competence")
    private String competence = "teacher";

    @OneToOne
    @JoinColumn(name = "teacher")
    private Teacher teacher;

    public User()
    {
    }

    public static User createUser(Teacher teacher)
    {
        User user = new User();

        user.teacher = teacher;

        return user;
    }

    public void setCompetence(String competence)
    {
        this.competence = competence;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setTeacher(Teacher teacher)
    {
        this.teacher = teacher;
    }

    public void setUserNumber(int userNumber)
    {
        this.userNumber = userNumber;
    }


    public String getPassword()
    {
        return password;
    }

    public int getUserNumber()
    {
        return userNumber;
    }

    public String getCompetence()
    {
        return competence;
    }

    public Teacher getTeacher()
    {
        return teacher;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "userNumber=" + userNumber +
                ", password='" + password + '\'' +
                ", teacher=" + teacher +
                ", competence='" + competence + '\'' +
                '}';
    }

    public static class Builder
    {
        private int userNumber;
        private String password = "123456";
        private String competence = "teacher";
        private Teacher teacher;

        public Builder()
        {
        }

        public Builder userNumber(int userNumber)
        {
            this.userNumber = userNumber;
            return this;
        }

        public Builder password(String password)
        {
            this.password = password;
            return this;
        }

        public Builder competence(String competence)
        {
            this.competence = competence;
            return this;
        }

        public Builder teacher(Teacher teacher)
        {
            this.teacher = teacher;
            return this;
        }

        public User build()
        {
            return new User(this);
        }
    }

    private User(Builder builder)
    {
        this.teacher = builder.teacher;
        this.competence = builder.competence;
        this.password = builder.password;
        this.userNumber = builder.userNumber;
    }
}

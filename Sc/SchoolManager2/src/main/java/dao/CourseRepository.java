package dao;

import model.College;
import model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer>
{
    public Course findByCourseNumber(int courseNumber);
    public List<Course> findByCollegeNumber(College collegeNumber);
}

package dao;

import model.CourseDay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseDayRepository extends JpaRepository<CourseDay, Integer>
{
}

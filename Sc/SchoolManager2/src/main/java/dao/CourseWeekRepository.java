package dao;

import model.CourseWeek;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseWeekRepository extends JpaRepository<CourseWeek, Integer>
{
}

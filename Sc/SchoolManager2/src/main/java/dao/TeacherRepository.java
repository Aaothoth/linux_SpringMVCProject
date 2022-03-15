package dao;

import model.College;
import model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Integer>
{
    public Teacher findByTeacherNumber(int teacherNumber);
    public List<Teacher> findByCollegeNumber(College collegeNumber);
}

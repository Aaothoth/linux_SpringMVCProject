package dao;

import model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ClassroomRepository extends JpaRepository<Classroom, Integer>
{
    Classroom findByClassroomNumber(int id);

    @Modifying
    @Transactional
    @Query("update Classroom c set c.classroomName = ?2 where c.classroomNumber = ?1")
    void updateByClassroomNumber(int number, String className);
}

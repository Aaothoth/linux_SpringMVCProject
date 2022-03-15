package dao;

import model.College;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegeRepository extends JpaRepository<College, Integer>
{
    public College findByCollegeNumber(int number);
}

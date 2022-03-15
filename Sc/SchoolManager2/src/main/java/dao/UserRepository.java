package dao;

import model.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>
{
    User findByUserNumber(int number);

    List<User> findUserByOrderByUserNumberDesc();

    @Modifying//该注解以开启spring-data的delete和update功能
    @Transactional
    @Query("update User u set u.password = :password where u.userNumber = :userNumber")
    int updateUserToUserNumberByPassword(@Param("userNumber")int userNumber,
                                         @Param("password")String password);
}

package springbootapplication.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import springbootapplication.model.User;


public interface UserRepository extends JpaRepository <User, Integer> {

    @Query ("select u from User u where u.email = :email")
    User findByEmail(@Param("email")String email);
}

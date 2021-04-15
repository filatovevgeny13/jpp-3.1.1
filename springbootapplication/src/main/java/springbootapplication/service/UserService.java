package springbootapplication.service;

import springbootapplication.model.User;
import java.util.List;

public interface UserService {
    void save(User user);

    void delete(int id);

    void update(User user);

    void encodePassword(User user);

    void setActiveAndRoles(User user, String Active, String isAdmin, String isUser);

    List<User> getAllUsers();

    User findById(int id);

    User findByEmail(String email);
}

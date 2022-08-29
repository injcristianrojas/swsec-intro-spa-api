package cl.injcristianrojas.swsecintro.service;

import cl.injcristianrojas.swsecintro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserService implements UserServiceInterface {

    @Autowired
    private UserServiceJDBC userService;

    public List<User> findUsersByType(String user_type) {
        return userService.findUsersByType(user_type);
    }

    public List<User> authenticateUserList(String username, String password) {
        return userService.authenticateUserList(username, password);
    }

    public User authenticateUser(String username, String password) {
        return userService.authenticateUser(username, password);
    }

    public User findByUsername(String username) {
        return userService.findByUsername(username);
    }

}

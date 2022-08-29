package cl.injcristianrojas.swsecintro.service;

import cl.injcristianrojas.swsecintro.model.User;

import java.util.List;

public interface UserServiceInterface {

    List<User> findUsersByType(String user_type);
    List<User> authenticateUserList(String username, String password);
    User authenticateUser(String username, String password);
    User findByUsername(String username);
}

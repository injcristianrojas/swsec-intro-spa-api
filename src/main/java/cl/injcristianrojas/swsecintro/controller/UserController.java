package cl.injcristianrojas.swsecintro.controller;

import cl.injcristianrojas.swsecintro.model.User;
import cl.injcristianrojas.swsecintro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/v1/users/type/{user_type}")
    public List<User> getAllUsers(@PathVariable String user_type) {
        return userService.findUsersByType(user_type);
    }
}

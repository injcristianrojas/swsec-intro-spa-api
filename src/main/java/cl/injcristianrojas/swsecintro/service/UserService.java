package cl.injcristianrojas.swsecintro.service;

import cl.injcristianrojas.swsecintro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<User> findUsersByType(String user_type) {
        return new ArrayList<>(jdbcTemplate.query("SELECT * FROM users where user_type = " + user_type, (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("username"), rs.getInt("user_type"))));
    }

    public User authenticateUser(String username, String password) {
        return jdbcTemplate.queryForObject("SELECT * FROM users where username = '" + username + "' AND password = '" + password + "'", (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("username"), rs.getInt("user_type")));
    }

    public User findByUsername(String username) {
        return jdbcTemplate.queryForObject("SELECT * FROM users where username = '" + username + "'", (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("username"), rs.getInt("user_type")));
    }


}

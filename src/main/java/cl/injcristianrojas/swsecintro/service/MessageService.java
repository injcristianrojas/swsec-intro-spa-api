package cl.injcristianrojas.swsecintro.service;

import cl.injcristianrojas.swsecintro.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MessageService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Message> findAllMessages() {
        return new ArrayList<>(jdbcTemplate.query("SELECT * FROM messages", (rs, rowNum) -> new Message(rs.getLong("id"), rs.getString("message"))));
    }

    public Map<String,Object> save(Message message) {
        jdbcTemplate.batchUpdate("INSERT INTO messages(message) VALUES ('" + message.getMessage() + "')");
        Map<String,Object> map = new HashMap<>();
        map.put("status", "OK");
        return map;
    }
}

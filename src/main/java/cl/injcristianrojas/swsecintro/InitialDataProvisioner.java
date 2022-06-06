package cl.injcristianrojas.swsecintro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class InitialDataProvisioner implements ApplicationListener<ContextRefreshedEvent> {

    final Logger logger = LoggerFactory.getLogger(InitialDataProvisioner.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        populateDatabase();
    }

    private void populateDatabase() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS messages;");
        jdbcTemplate.execute("DROP TABLE IF EXISTS users;");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS messages (id INTEGER PRIMARY KEY NOT NULL, message TEXT NOT NULL );");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY NOT NULL, username VARCHAR(10) NOT NULL, password VARCHAR(30) NOT NULL, user_type INTEGER NOT NULL);");
        jdbcTemplate.execute("INSERT INTO messages(message) VALUES ('Bienvenidos al foro de Fans de las Aves Chilenas. Soy el Administrador.')");
        jdbcTemplate.execute("INSERT INTO messages(message) VALUES ('Se informa que la API se encuentra deshabilitada hasta nuevo aviso.')");
        jdbcTemplate.execute("INSERT INTO users(username,password,user_type) VALUES ('admin', '123', 1)");
        jdbcTemplate.execute("INSERT INTO users(username,password,user_type) VALUES ('zorzal', 'fio', 2)");
        jdbcTemplate.execute("INSERT INTO users(username,password,user_type) VALUES ('chincol', 'fiofio', 2)");
        logger.info("Database populated.");
    }
}

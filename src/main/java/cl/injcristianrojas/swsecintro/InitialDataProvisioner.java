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
        jdbcTemplate.execute("INSERT INTO messages(message) VALUES ('Bienvenidos al foro de Fans de las Aves Chilenas. Soy el Administrador.')");
        jdbcTemplate.execute("INSERT INTO messages(message) VALUES ('Se informa que la API se encuentra deshabilitada hasta nuevo aviso.')");
        logger.info("Database populated.");
    }
}

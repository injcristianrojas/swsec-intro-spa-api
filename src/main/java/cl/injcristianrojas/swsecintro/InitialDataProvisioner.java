package cl.injcristianrojas.swsecintro;

import cl.injcristianrojas.swsecintro.controller.TestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@Component
public class InitialDataProvisioner implements ApplicationListener<ContextRefreshedEvent> {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        populateDatabase();
    }

    private void populateDatabase() {
        jdbcTemplate.execute("INSERT INTO mensajes(mensaje) VALUES ('Bienvenidos al foro de Fans de las Aves Chilenas. Soy el Administrador.')");
        jdbcTemplate.execute("INSERT INTO mensajes(mensaje) VALUES ('Se informa que la API se encuentra deshabilitada hasta nuevo aviso.')");
        logger.info("Database populated.");
    }
}

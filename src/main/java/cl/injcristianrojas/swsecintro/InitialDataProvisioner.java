package cl.injcristianrojas.swsecintro;

import cl.injcristianrojas.swsecintro.rest.controller.TestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@Component
public class InitialDataProvisioner implements ApplicationListener<ContextRefreshedEvent> {

    public static final String SQLITE_URL = "jdbc:sqlite:swsecdemo.sqlite";

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        populateDatabase();
    }

    private void populateDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            String messageTable = "CREATE TABLE IF NOT EXISTS 'mensajes' ('id' INTEGER PRIMARY KEY NOT NULL, 'mensaje' TEXT NOT NULL );";
            String userTable = "CREATE TABLE IF NOT EXISTS 'usuarios' ('id' INTEGER PRIMARY KEY NOT NULL, 'username' VARCHAR(10) NOT NULL, 'password' VARCHAR(10) NOT NULL ,'type' INTEGER);";
            Connection conn = DriverManager.getConnection(SQLITE_URL);
            Statement stmt = conn.createStatement();
            stmt.setQueryTimeout(30);
            stmt.executeUpdate(userTable);
            stmt.executeUpdate(messageTable);
            stmt.executeUpdate("DELETE FROM usuarios");
            stmt.executeUpdate("DELETE FROM mensajes");
            stmt.executeUpdate("INSERT INTO usuarios(username, password, type) VALUES ('admin', 'admin', '1')");
            stmt.executeUpdate("INSERT INTO usuarios(username, password, type) VALUES ('jperez', '123', '2')");
            stmt.executeUpdate(
                    "INSERT into mensajes(mensaje) VALUES ('Bienvenidos al foro de Fans de las Aves Chilenas. Soy el Administrador.')");
            stmt.executeUpdate(
                    "INSERT into mensajes(mensaje) VALUES ('Se informa que la API se encuentra deshabilitada hasta nuevo aviso.')");
                    logger.info("Database created.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

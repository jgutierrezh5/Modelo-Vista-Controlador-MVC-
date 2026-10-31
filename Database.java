package config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Conexión a la base de datos (equivalente a config/database.php).
 * Lee la configuración desde src/main/resources/db.properties.
 */
public final class Database {

    private static final Properties CONFIG = cargarConfiguracion();

    private Database() {
    }

    private static Properties cargarConfiguracion() {
        Properties props = new Properties();
        try (InputStream in = Database.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (in == null) {
                throw new IllegalStateException("No se encontró db.properties en src/main/resources.");
            }
            props.load(in);
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo leer db.properties.", e);
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("No se encontró el driver de MySQL (mysql-connector-j).", e);
        }
        return props;
    }

    /** Abre una nueva conexión. Quien la usa debe cerrarla (try-with-resources). */
    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(
                CONFIG.getProperty("db.url"),
                CONFIG.getProperty("db.user"),
                CONFIG.getProperty("db.password", ""));
    }
}

package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final Logger logger = LogManager.getLogger(DBConnection.class);

    private static final String DB_URL = "jdbc:h2:./data/farmaciaDB";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName("org.h2.Driver");
            logger.info("Driver H2 cargado correctamente.");
        } catch (ClassNotFoundException e) {
            logger.error("No se pudo cargar el driver de H2", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        logger.info("Conexión a la base H2 establecida.");
        return conn;
    }
}

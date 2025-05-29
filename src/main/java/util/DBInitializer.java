package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Statement;

public class DBInitializer {

    private static final Logger logger = LogManager.getLogger(DBInitializer.class);

    public static void inicializar() {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            String sqlCliente = """
                    CREATE TABLE IF NOT EXISTS cliente (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        nombre VARCHAR(100) NOT NULL,
                        email VARCHAR(100),
                        telefono VARCHAR(20)
                    );
                    """;

            String sqlPedido = """
                    CREATE TABLE IF NOT EXISTS pedido (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        fecha DATE NOT NULL,
                        monto_total DOUBLE NOT NULL,
                        cliente_id INT NOT NULL,
                        FOREIGN KEY (cliente_id) REFERENCES cliente(id)
                    );
                    """;

            stmt.execute(sqlCliente);
            stmt.execute(sqlPedido);

            logger.info("Tablas 'cliente' y 'pedido' creadas o verificadas correctamente.");

        } catch (Exception e) {
            logger.error("Error al crear las tablas en la base de datos", e);
        }
    }
}

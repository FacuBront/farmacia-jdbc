package dao.impl;

import dao.PedidoDAO;
import model.Pedido;
import util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAOImpl extends AbstractDAO<Pedido> implements PedidoDAO {

    @Override
    public void guardar(Pedido pedido) {
        String sql = "INSERT INTO pedido (fecha, monto_total, cliente_id) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDate(1, Date.valueOf(pedido.getFecha()));
            stmt.setDouble(2, pedido.getMontoTotal());
            stmt.setInt(3, pedido.getClienteId());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    pedido.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al guardar pedido: " + e.getMessage());
        }
    }

    @Override
    public Pedido buscarPorId(int id) {
        String sql = "SELECT * FROM pedido WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Pedido(
                            rs.getInt("id"),
                            rs.getDate("fecha").toLocalDate(),
                            rs.getDouble("monto_total"),
                            rs.getInt("cliente_id")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar pedido: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Pedido> listarTodos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                pedidos.add(new Pedido(
                        rs.getInt("id"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getDouble("monto_total"),
                        rs.getInt("cliente_id")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error al listar pedidos: " + e.getMessage());
        }

        return pedidos;
    }

    @Override
    public List<Pedido> listarPorCliente(int clienteId) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido WHERE cliente_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, clienteId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    pedidos.add(new Pedido(
                            rs.getInt("id"),
                            rs.getDate("fecha").toLocalDate(),
                            rs.getDouble("monto_total"),
                            rs.getInt("cliente_id")
                    ));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al listar pedidos por cliente: " + e.getMessage());
        }

        return pedidos;
    }

    @Override
    public void actualizar(Pedido pedido) {
        String sql = "UPDATE pedido SET fecha = ?, monto_total = ?, cliente_id = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(pedido.getFecha()));
            stmt.setDouble(2, pedido.getMontoTotal());
            stmt.setInt(3, pedido.getClienteId());
            stmt.setInt(4, pedido.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar pedido: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM pedido WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al eliminar pedido: " + e.getMessage());
        }
    }
}

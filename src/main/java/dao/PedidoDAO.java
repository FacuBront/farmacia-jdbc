package dao;

import model.Cliente;
import model.Pedido;

import java.util.List;

public interface PedidoDAO extends GenericDAO<Pedido>{
    void guardar(Pedido pedido);
    Pedido buscarPorId(int id);
    List<Pedido> listarTodos();
    List<Pedido> listarPorCliente(int clienteId);
    void actualizar(Pedido pedido);
    void eliminar(int id);
}

package dao;

import model.Cliente;

import java.util.List;

public interface ClienteDAO extends GenericDAO<Cliente> {
    void guardar(Cliente cliente);
    Cliente buscarPorId(int id);
    List<Cliente> listarTodos();
    void actualizar(Cliente cliente);
    void eliminar(int id);
}

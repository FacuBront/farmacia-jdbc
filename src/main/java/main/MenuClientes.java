package main;

import dao.ClienteDAO;
import dao.impl.ClienteDAOImpl;
import model.Cliente;

import java.util.List;
import java.util.Scanner;

public class MenuClientes {

    private static final ClienteDAO clienteDAO = new ClienteDAOImpl();

    public static void iniciar(Scanner scanner) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- Gestión de Clientes ---");
            System.out.println("1. Crear Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Buscar Cliente por ID");
            System.out.println("4. Actualizar Cliente");
            System.out.println("5. Eliminar Cliente");
            System.out.println("0. Volver");

            int opcion = leerEntero(scanner, "Opción: ");

            switch (opcion) {
                case 1 -> crearCliente(scanner);
                case 2 -> listarClientes();
                case 3 -> buscarCliente(scanner);
                case 4 -> actualizarCliente(scanner);
                case 5 -> eliminarCliente(scanner);
                case 0 -> volver = true;
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static void crearCliente(Scanner sc) {
        String nombre = leerTexto(sc, "Nombre: ");
        String email = leerTexto(sc, "Email: ");
        String telefono = leerTexto(sc, "Teléfono: ");
        Cliente c = new Cliente(nombre, email, telefono);
        clienteDAO.guardar(c);
        System.out.println("Cliente creado con ID: " + c.getId());
    }

    private static void listarClientes() {
        List<Cliente> lista = clienteDAO.listarTodos();
        lista.forEach(System.out::println);
    }

    private static void buscarCliente(Scanner sc) {
        int id = leerEntero(sc, "ID del cliente: ");
        Cliente c = clienteDAO.buscarPorId(id);
        if (c != null) System.out.println(c);
        else System.out.println("No encontrado.");
    }

    private static void actualizarCliente(Scanner sc) {
        int id = leerEntero(sc, "ID del cliente: ");
        Cliente c = clienteDAO.buscarPorId(id);
        if (c == null) {
            System.out.println("No encontrado.");
            return;
        }
        System.out.println("Actualizar datos (Enter para mantener):");
        String nombre = leerTextoOpcional(sc, "Nombre (" + c.getNombre() + "): ");
        String email = leerTextoOpcional(sc, "Email (" + c.getEmail() + "): ");
        String tel = leerTextoOpcional(sc, "Teléfono (" + c.getTelefono() + "): ");

        if (!nombre.isEmpty()) c.setNombre(nombre);
        if (!email.isEmpty()) c.setEmail(email);
        if (!tel.isEmpty()) c.setTelefono(tel);

        clienteDAO.actualizar(c);
        System.out.println("Cliente actualizado.");
    }

    private static void eliminarCliente(Scanner sc) {
        int id = leerEntero(sc, "ID del cliente: ");
        clienteDAO.eliminar(id);
        System.out.println("Cliente eliminado (si existía).");
    }

    private static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
            }
        }
    }

    private static String leerTexto(Scanner sc, String mensaje) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = sc.nextLine().trim();
        } while (texto.isEmpty());
        return texto;
    }

    private static String leerTextoOpcional(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }
}

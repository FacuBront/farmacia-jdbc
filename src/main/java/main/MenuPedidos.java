package main;

import dao.ClienteDAO;
import dao.PedidoDAO;
import dao.impl.ClienteDAOImpl;
import dao.impl.PedidoDAOImpl;
import model.Cliente;
import model.Pedido;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuPedidos {

    private static final PedidoDAO pedidoDAO = new PedidoDAOImpl();
    private static final ClienteDAO clienteDAO = new ClienteDAOImpl();

    public static void iniciar(Scanner scanner) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- Gestión de Pedidos ---");
            System.out.println("1. Crear Pedido");
            System.out.println("2. Listar Pedidos");
            System.out.println("3. Buscar Pedido por ID");
            System.out.println("4. Listar Pedidos por Cliente");
            System.out.println("5. Actualizar Pedido");
            System.out.println("6. Eliminar Pedido");
            System.out.println("0. Volver");

            int opcion = leerEntero(scanner, "Opción: ");

            switch (opcion) {
                case 1 -> crearPedido(scanner);
                case 2 -> listarPedidos();
                case 3 -> buscarPedido(scanner);
                case 4 -> listarPedidosPorCliente(scanner);
                case 5 -> actualizarPedido(scanner);
                case 6 -> eliminarPedido(scanner);
                case 0 -> volver = true;
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static void crearPedido(Scanner sc) {
        int clienteId = leerEntero(sc, "ID del cliente: ");
        Cliente cliente = clienteDAO.buscarPorId(clienteId);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        LocalDate fecha = leerFecha(sc, "Fecha (YYYY-MM-DD): ");
        double monto = leerDouble(sc, "Monto total: ");

        Pedido pedido = new Pedido(fecha, monto, clienteId);
        pedidoDAO.guardar(pedido);
        System.out.println("Pedido creado con ID: " + pedido.getId());
    }

    private static void listarPedidos() {
        List<Pedido> lista = pedidoDAO.listarTodos();
        lista.forEach(System.out::println);
    }

    private static void buscarPedido(Scanner sc) {
        int id = leerEntero(sc, "ID del pedido: ");
        Pedido p = pedidoDAO.buscarPorId(id);
        if (p != null) System.out.println(p);
        else System.out.println("No encontrado.");
    }

    private static void listarPedidosPorCliente(Scanner sc) {
        int clienteId = leerEntero(sc, "ID del cliente: ");
        List<Pedido> pedidos = pedidoDAO.listarPorCliente(clienteId);
        if (pedidos.isEmpty()) System.out.println("No hay pedidos para ese cliente.");
        else pedidos.forEach(System.out::println);
    }

    private static void actualizarPedido(Scanner sc) {
        int id = leerEntero(sc, "ID del pedido: ");
        Pedido p = pedidoDAO.buscarPorId(id);
        if (p == null) {
            System.out.println("No encontrado.");
            return;
        }

        System.out.println("Actualizar datos (Enter para mantener):");
        String fechaStr = leerTextoOpcional(sc, "Fecha (" + p.getFecha() + ") YYYY-MM-DD: ");
        if (!fechaStr.isEmpty()) {
            try {
                p.setFecha(LocalDate.parse(fechaStr));
            } catch (Exception e) {
                System.out.println("Fecha inválida.");
            }
        }

        String montoStr = leerTextoOpcional(sc, "Monto total (" + p.getMontoTotal() + "): ");
        if (!montoStr.isEmpty()) {
            try {
                p.setMontoTotal(Double.parseDouble(montoStr));
            } catch (Exception e) {
                System.out.println("Monto inválido.");
            }
        }

        String clienteIdStr = leerTextoOpcional(sc, "Cliente ID (" + p.getClienteId() + "): ");
        if (!clienteIdStr.isEmpty()) {
            try {
                int nuevoId = Integer.parseInt(clienteIdStr);
                if (clienteDAO.buscarPorId(nuevoId) != null) {
                    p.setClienteId(nuevoId);
                } else {
                    System.out.println("Cliente no encontrado.");
                }
            } catch (Exception e) {
                System.out.println("ID inválido.");
            }
        }

        pedidoDAO.actualizar(p);
        System.out.println("Pedido actualizado.");
    }

    private static void eliminarPedido(Scanner sc) {
        int id = leerEntero(sc, "ID del pedido: ");
        pedidoDAO.eliminar(id);
        System.out.println("Pedido eliminado (si existía).");
    }

    // Utilidades de lectura
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

    private static double leerDouble(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número decimal válido.");
            }
        }
    }

    private static LocalDate leerFecha(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String input = sc.nextLine();
            try {
                return LocalDate.parse(input);
            } catch (Exception e) {
                System.out.println("Formato inválido. Use YYYY-MM-DD.");
            }
        }
    }

    private static String leerTextoOpcional(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }
}

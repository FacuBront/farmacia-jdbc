package main;

import java.util.Scanner;

public class MenuPrincipal {

    private static final Scanner scanner = new Scanner(System.in);

    public static void iniciar() {
        boolean salir = false;
        System.out.println("=== Sistema de Gestión de Farmacia ===");

        while (!salir) {
            System.out.println("\nMenú Principal:");
            System.out.println("1. Gestionar Clientes");
            System.out.println("2. Gestionar Pedidos");
            System.out.println("0. Salir");

            int opcion = leerEntero("Ingrese una opción: ");

            switch (opcion) {
                case 1 -> MenuClientes.iniciar(scanner);
                case 2 -> MenuPedidos.iniciar(scanner);
                case 0 -> {
                    salir = true;
                    System.out.println("¡Gracias por usar el sistema!");
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
            }
        }
    }
}

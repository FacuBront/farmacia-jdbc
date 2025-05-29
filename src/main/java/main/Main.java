package main;

import util.DBInitializer;

public class Main {
    public static void main(String[] args) {
        DBInitializer.inicializar();
        MenuPrincipal.iniciar();
    }
}

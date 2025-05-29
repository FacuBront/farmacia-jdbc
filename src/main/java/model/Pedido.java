package model;

import java.time.LocalDate;

/**
 * Representa un pedido realizado por un cliente.
 */
public class Pedido {
    private int id;
    private LocalDate fecha;
    private double montoTotal;
    private int clienteId;

    public Pedido() {
    }

    public Pedido(int id, LocalDate fecha, double montoTotal, int clienteId) {
        this.id = id;
        this.fecha = fecha;
        this.montoTotal = montoTotal;
        this.clienteId = clienteId;
    }

    public Pedido(LocalDate fecha, double montoTotal, int clienteId) {
        this.fecha = fecha;
        this.montoTotal = montoTotal;
        this.clienteId = clienteId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula.");
        }
        this.fecha = fecha;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        if (montoTotal < 0) {
            throw new IllegalArgumentException("El monto total no puede ser negativo.");
        }
        this.montoTotal = montoTotal;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", montoTotal=" + montoTotal +
                ", clienteId=" + clienteId +
                '}';
    }
}

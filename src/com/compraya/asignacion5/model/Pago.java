package com.compraya.asignacion5.model;

import java.time.LocalDateTime;

public class Pago {
    private int id;
    private double monto;
    private String metodoPago;
    private String estado;
    private LocalDateTime fechaPago;

    public Pago() {
        this.estado = "PENDIENTE";
    }

    public Pago(int id, double monto, String metodoPago) {
        this.id = id;
        setMonto(monto);
        this.metodoPago = metodoPago;
        this.estado = "PENDIENTE";
    }

    public boolean procesarPago() {
        System.out.printf("[PAGO ENCAPSULADO] Procesando pago de $%.2f vía Pasarela (%s)...\n", monto, metodoPago);
        this.estado = "APROBADO";
        this.fechaPago = LocalDateTime.now();
        System.out.println("[PAGO ENCAPSULADO] Transacción aprobada. ID TXN: TXN-" + id);
        return true;
    }

    public boolean verificarPago() {
        return "APROBADO".equalsIgnoreCase(this.estado);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("[VALIDACIÓN ENCAPSULADA] El monto a pagar debe ser mayor a cero.");
        }
        this.monto = monto;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    @Override
    public String toString() {
        return String.format("Pago[ID: %d | Monto: $%.2f | Método: %s | Estado: %s]", id, monto, metodoPago, estado);
    }
}

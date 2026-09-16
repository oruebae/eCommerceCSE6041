package com.compraya.model;

import java.time.LocalDateTime;

/**
 * Gestiona el procesamiento y la verificación del pago de un pedido.
 */
public class Pago {
    private int id;
    private double monto;
    private String metodoPago; // E.g., "TARJETA_CREDITO", "PSE", "PAYPAL"
    private String estado;     // E.g., "PENDIENTE", "APROBADO", "RECHAZADO"
    private LocalDateTime fechaPago;

    public Pago() {
        this.estado = "PENDIENTE";
    }

    public Pago(int id, double monto, String metodoPago) {
        this.id = id;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.estado = "PENDIENTE";
    }

    /**
     * Procesa la transacción simulada de pago.
     * 
     * @return true si el pago fue aprobado exitosamente.
     */
    public boolean procesarPago() {
        System.out.printf("[PAGO] Procesando pago de $%.2f vía Pasarela Externa (%s)...\n", monto, metodoPago);
        // Simulación exitosa de pasarela
        this.estado = "APROBADO";
        this.fechaPago = LocalDateTime.now();
        System.out.println("[PAGO] Transacción aprobada exitosamente. ID de Transacción: TXN-" + id);
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
        return String.format("Pago[ID: %d | Monto: $%.2f | Método: %s | Estado: %s]",
                id, monto, metodoPago, estado);
    }
}

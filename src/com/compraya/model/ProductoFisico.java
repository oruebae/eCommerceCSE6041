package com.compraya.model;

/**
 * Subclase ProductoFisico que extiende de Producto (Herencia).
 * Especializa la funcionalidad para bienes tangibles que requieren dimensiones, peso
 * y cálculo de logística de despacho físico.
 */
public class ProductoFisico extends Producto {
    private double pesoKg;
    private String dimensiones; // Ej: "30x20x15 cm"
    private double costoEnvioBase;

    public ProductoFisico() {
        super();
        this.costoEnvioBase = 12000.0;
    }

    /**
     * Constructor completo para ProductoFisico.
     */
    public ProductoFisico(int id, String nombre, String descripcion, double precio, int stock,
                          Categoria categoria, double pesoKg, String dimensiones, double costoEnvioBase) {
        super(id, nombre, descripcion, precio, stock, categoria);
        this.pesoKg = pesoKg;
        this.dimensiones = dimensiones;
        this.costoEnvioBase = costoEnvioBase;
    }

    /**
     * Calcula el costo estimado de envío en función del peso en Kg y el destino.
     * 
     * @param departamento Departamento de envío en Colombia.
     * @return Valor del flete de envío.
     */
    public double calcularCostoEnvio(String departamento) {
        double recargoPeso = (pesoKg > 2.0) ? (pesoKg - 2.0) * 3000.0 : 0.0;
        double recargoRegion = 0.0;
        if (departamento != null && (departamento.equalsIgnoreCase("San Andrés") || departamento.equalsIgnoreCase("Amazonas"))) {
            recargoRegion = 15000.0;
        }
        return costoEnvioBase + recargoPeso + recargoRegion;
    }

    @Override
    public String obtenerDetallesEspecificos() {
        return String.format("Físico [Peso: %.2f kg | Dim: %s | Flete Base: $%.2f]", pesoKg, dimensiones, costoEnvioBase);
    }

    @Override
    public boolean requiereEnvioFisico() {
        return true;
    }

    // --- Getters y Setters ---

    public double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public double getCostoEnvioBase() {
        return costoEnvioBase;
    }

    public void setCostoEnvioBase(double costoEnvioBase) {
        this.costoEnvioBase = costoEnvioBase;
    }
}

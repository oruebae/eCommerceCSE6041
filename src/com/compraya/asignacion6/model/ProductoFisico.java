package com.compraya.asignacion6.model;

public class ProductoFisico extends Producto {
    private double pesoKg;
    private String dimensiones;
    private double costoEnvioBase;

    public ProductoFisico() {
        super();
        this.costoEnvioBase = 12000.0;
    }

    public ProductoFisico(int id, String nombre, String descripcion, double precio, int stock,
                          Categoria categoria, double pesoKg, String dimensiones, double costoEnvioBase) {
        super(id, nombre, descripcion, precio, stock, categoria);
        this.pesoKg = pesoKg;
        this.dimensiones = dimensiones;
        this.costoEnvioBase = costoEnvioBase;
    }

    public double calcularCostoEnvio(String departamento) {
        double recargoPeso = (pesoKg > 2.0) ? (pesoKg - 2.0) * 3000.0 : 0.0;
        double recargoRegion = 0.0;
        if (departamento != null && (departamento.equalsIgnoreCase("San Andrés") || departamento.equalsIgnoreCase("Amazonas"))) {
            recargoRegion = 15000.0;
        }
        return costoEnvioBase + recargoPeso + recargoRegion;
    }

    @Override
    public void mostrarDetalle() {
        System.out.printf("ProductoFisico[ID: %d | %s | $%.2f | Stock: %d | Peso: %.2f kg | Dim: %s]\n",
                getId(), getNombre(), getPrecio(), getStock(), pesoKg, dimensiones);
    }

    @Override
    public boolean requiereEnvioFisico() { return true; }

    @Override
    public String obtenerDetallesEspecificos() {
        return String.format("Físico [Peso: %.2f kg | Dim: %s]", pesoKg, dimensiones);
    }

    public double getPesoKg() { return pesoKg; }
    public void setPesoKg(double pesoKg) { this.pesoKg = pesoKg; }
    public String getDimensiones() { return dimensiones; }
    public void setDimensiones(String dimensiones) { this.dimensiones = dimensiones; }
    public double getCostoEnvioBase() { return costoEnvioBase; }
    public void setCostoEnvioBase(double costoEnvioBase) { this.costoEnvioBase = costoEnvioBase; }
}

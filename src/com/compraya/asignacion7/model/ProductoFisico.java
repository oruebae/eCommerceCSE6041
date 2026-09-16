package com.compraya.asignacion7.model;

public class ProductoFisico extends Item {
    private double pesoKg;
    private String dimensiones;
    private double costoEnvioBase;

    public ProductoFisico() { super(); }

    public ProductoFisico(int id, String nombre, String descripcion, double precio, int stock,
                          Categoria categoria, double pesoKg, String dimensiones, double costoEnvioBase) {
        super(id, nombre, descripcion, precio, stock, categoria);
        this.pesoKg = pesoKg;
        this.dimensiones = dimensiones;
        this.costoEnvioBase = costoEnvioBase;
    }

    @Override
    public void mostrarDetalle() {
        System.out.printf("ProductoFisico[ID: %d | %s | $%.2f | Stock: %d | Peso: %.2f kg]\n",
                getId(), getNombre(), getPrecio(), getStock(), pesoKg);
    }

    @Override
    public boolean requiereEnvioFisico() { return true; }

    @Override
    public String obtenerDetallesEspecificos() {
        return String.format("Físico [Peso: %.2f kg | Dim: %s]", pesoKg, dimensiones);
    }

    public double getPesoKg() { return pesoKg; }
    public String getDimensiones() { return dimensiones; }
    public double getCostoEnvioBase() { return costoEnvioBase; }
}

package com.compraya.asignacion5.model;

/**
 * Subclase ProductoFisico (Asignación 5) que extiende Producto e implementa la interfaz Despachable.
 */
public class ProductoFisico extends Producto implements Despachable {
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
        setPesoKg(pesoKg);
        this.dimensiones = dimensiones;
        setCostoEnvioBase(costoEnvioBase);
    }

    @Override
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
        System.out.println("-------------------------------------------------------");
        System.out.println(" 📦 FICHA TÉCNICA DE PRODUCTO FÍSICO (ITEM)            ");
        System.out.println("-------------------------------------------------------");
        System.out.println("ID:            " + getId());
        System.out.println("Nombre:        " + getNombre());
        System.out.println("Descripción:   " + getDescripcion());
        System.out.printf("Precio:        $%.2f\n", getPrecio());
        System.out.println("Stock:         " + getStock() + " unidades");
        System.out.println("Categoría:     " + (getCategoria() != null ? getCategoria().getNombre() : "N/A"));
        System.out.printf("Peso Físico:   %.2f kg\n", pesoKg);
        System.out.println("Dimensiones:   " + dimensiones);
        System.out.printf("Flete Base:    $%.2f\n", costoEnvioBase);
        System.out.printf("Flete Bogotá:  $%.2f\n", calcularCostoEnvio("Cundinamarca"));
    }

    @Override
    public boolean requiereEnvioFisico() {
        return true;
    }

    @Override
    public String obtenerDetallesEspecificos() {
        return String.format("Físico [Peso: %.2f kg | Dim: %s | Flete Base: $%.2f]", pesoKg, dimensiones, costoEnvioBase);
    }

    // --- Getters y Setters Encapsulados ---

    @Override
    public double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(double pesoKg) {
        if (pesoKg <= 0) {
            throw new IllegalArgumentException("[VALIDACIÓN ENCAPSULADA] El peso debe ser mayor a 0 kg.");
        }
        this.pesoKg = pesoKg;
    }

    @Override
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
        if (costoEnvioBase < 0) {
            throw new IllegalArgumentException("[VALIDACIÓN ENCAPSULADA] El costo de envío base no puede ser negativo.");
        }
        this.costoEnvioBase = costoEnvioBase;
    }
}

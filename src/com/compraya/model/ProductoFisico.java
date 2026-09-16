package com.compraya.model;

/**
 * Subclase ProductoFisico que extiende de Producto (Herencia).
 * Sobrescribe mostrarDetalle() para imprimir especificaciones de peso, dimensiones y fletes (Sobreescritura - Overriding).
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
     * Sobreescritura del método mostrarDetalle() para bienes físicos (Overriding).
     */
    @Override
    public void mostrarDetalle() {
        System.out.println("-------------------------------------------------------");
        System.out.println(" 📦 FICHA TÉCNICA DE PRODUCTO FÍSICO                   ");
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
        System.out.println("Logística:     Requiere transporte terrestre/aéreo");
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

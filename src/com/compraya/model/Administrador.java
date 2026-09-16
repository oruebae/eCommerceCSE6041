package com.compraya.model;

/**
 * Clase Administrador que extiende de Usuario (Herencia).
 * Especializa las funcionalidades para usuarios de tipo gestor, incluyendo control de inventario,
 * establecimiento de promociones comerciales y generación de reportes consolidado de ventas.
 */
public class Administrador extends Usuario {
    private String nivelAcceso;
    private String departamento;

    public Administrador() {
        super();
        this.nivelAcceso = "SUPERADMIN";
        this.departamento = "OPERACIONES";
    }

    public Administrador(int id, String nombre, String email, String password, String nivelAcceso, String departamento) {
        super(id, nombre, email, password);
        this.nivelAcceso = nivelAcceso;
        this.departamento = departamento;
    }

    // --- Métodos de Especialización Requeridos ---

    /**
     * Establece y aplica una promoción de descuento especial a un producto del catálogo.
     * 
     * @param producto           Producto al cual aplicar el descuento.
     * @param porcentajeDescuento Porcentaje de rebaja.
     */
    public void establecerPromocion(Producto producto, double porcentajeDescuento) {
        if (producto != null) {
            double precioAnterior = producto.getPrecio();
            producto.aplicarDescuento(porcentajeDescuento);
            System.out.printf("[ADMIN %s - %s] Promoción aplicada a '%s': %.1f%% de descuento. (Precio anterior: $%.2f -> Nuevo: $%.2f)\n",
                    getNombre(), departamento, producto.getNombre(), porcentajeDescuento, precioAnterior, producto.getPrecio());
        }
    }

    public void gestionarInventario(Producto producto, int nuevoStock) {
        if (producto != null) {
            producto.setStock(nuevoStock);
            System.out.printf("[ADMIN %s] Stock actualizado para '%s': %d unidades.\n",
                    getNombre(), producto.getNombre(), nuevoStock);
        }
    }

    public void generarReporte(double totalVentas, int totalPedidos) {
        generarReporteVentas(totalVentas, totalPedidos);
    }

    public void generarReporteVentas(double totalVentas, int totalPedidos) {
        System.out.println("==========================================");
        System.out.println("     REPORTE DE VENTAS COMPRAYA (ASIG. 3) ");
        System.out.println("==========================================");
        System.out.printf("Admin Responsable: %s (%s - %s)\n", getNombre(), nivelAcceso, departamento);
        System.out.printf("Total Pedidos Procesados: %d\n", totalPedidos);
        System.out.printf("Ingresos Totales Acumulados: $%.2f\n", totalVentas);
        System.out.println("==========================================");
    }

    // --- Getters y Setters ---

    public String getNivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(String nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return String.format("Administrador[ID: %d | Nombre: %s | NivelAcceso: %s | Dpto: %s]",
                getId(), getNombre(), nivelAcceso, departamento);
    }
}

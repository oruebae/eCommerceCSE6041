package com.compraya.asignacion5.model;

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

    public void establecerPromocion(Item item, double porcentajeDescuento) {
        if (item != null) {
            double precioAnterior = item.getPrecio();
            item.aplicarDescuento(porcentajeDescuento);
            System.out.printf("[ADMIN %s - %s] Promoción aplicada a '%s': %.1f%% descuento. ($%.2f -> $%.2f)\n",
                    getNombre(), departamento, item.getNombre(), porcentajeDescuento, precioAnterior, item.getPrecio());
        }
    }

    public void gestionarInventario(Item item, int nuevoStock) {
        if (item != null) {
            item.setStock(nuevoStock);
            System.out.printf("[ADMIN %s] Stock actualizado para '%s': %d unidades.\n",
                    getNombre(), item.getNombre(), nuevoStock);
        }
    }

    public void generarReporteVentas(double totalVentas, int totalPedidos) {
        System.out.println("==========================================");
        System.out.println("     REPORTE DE VENTAS COMPRAYA (ASIG. 5) ");
        System.out.println("==========================================");
        System.out.printf("Admin Responsable: %s (%s - %s)\n", getNombre(), nivelAcceso, departamento);
        System.out.printf("Total Pedidos Procesados: %d\n", totalPedidos);
        System.out.printf("Ingresos Totales Acumulados: $%.2f\n", totalVentas);
        System.out.println("==========================================");
    }

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

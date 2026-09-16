package com.compraya.asignacion7.factory;

import com.compraya.asignacion7.model.*;

import java.util.Map;

/**
 * Patrón Factory - FabricaEntidades (Asignación 7).
 * Proporciona métodos de fábrica desacoplados para la creación dinámica de Productos y Usuarios.
 */
public class FabricaEntidades {

    /**
     * Fábrica de Creación de Productos (Físico vs Digital).
     * 
     * @param tipo        "FISICO" o "DIGITAL".
     * @param id          Identificador único.
     * @param nombre      Nombre comercial.
     * @param descripcion Descripción.
     * @param precio      Precio unitario.
     * @param stock       Cantidad disponible.
     * @param categoria   Categoría asociada.
     * @param params      Mapa con parámetros específicos (peso, dimensiones, formato, url, etc.).
     * @return Instancia concreta de Item (ProductoFisico o ProductoDigital).
     */
    public static Item crearProducto(String tipo, int id, String nombre, String descripcion,
                                     double precio, int stock, Categoria categoria, Map<String, Object> params) {
        if (tipo == null) {
            throw new IllegalArgumentException("[FABRICA] El tipo de producto no puede ser nulo.");
        }

        switch (tipo.toUpperCase()) {
            case "FISICO":
                double peso = params != null && params.containsKey("pesoKg") ? (double) params.get("pesoKg") : 1.0;
                String dimensiones = params != null && params.containsKey("dimensiones") ? (String) params.get("dimensiones") : "10x10x10 cm";
                double flete = params != null && params.containsKey("costoEnvioBase") ? (double) params.get("costoEnvioBase") : 10000.0;
                System.out.printf("[FABRICA FACTORY] Instanciando ProductoFisico: '%s' ($%.2f)\n", nombre, precio);
                return new ProductoFisico(id, nombre, descripcion, precio, stock, categoria, peso, dimensiones, flete);

            case "DIGITAL":
                String formato = params != null && params.containsKey("formatoArchivo") ? (String) params.get("formatoArchivo") : "ZIP/PDF";
                double tamano = params != null && params.containsKey("tamanoMB") ? (double) params.get("tamanoMB") : 50.0;
                String url = params != null && params.containsKey("urlDescarga") ? (String) params.get("urlDescarga") : "https://cdn.compraya.com/download";
                String licencia = params != null && params.containsKey("licencia") ? (String) params.get("licencia") : "LIC-STANDARD";
                System.out.printf("[FABRICA FACTORY] Instanciando ProductoDigital: '%s' ($%.2f)\n", nombre, precio);
                return new ProductoDigital(id, nombre, descripcion, precio, stock, categoria, formato, tamano, url, licencia);

            default:
                throw new IllegalArgumentException("[FABRICA] Tipo de producto no reconocido: " + tipo);
        }
    }

    /**
     * Fábrica de Creación de Usuarios (Cliente vs Administrador).
     * 
     * @param tipo   "CLIENTE" o "ADMINISTRADOR".
     * @param id     Identificador de usuario.
     * @param nombre Nombre completo.
     * @param email  Correo electrónico.
     * @param pass   Contraseña.
     * @param params Parámetros adicionales (nivelAcceso, departamento, etc.).
     * @return Instancia concreta de Usuario (Cliente o Administrador).
     */
    public static Usuario crearUsuario(String tipo, int id, String nombre, String email,
                                       String pass, Map<String, Object> params) {
        if (tipo == null) {
            throw new IllegalArgumentException("[FABRICA] El tipo de usuario no puede ser nulo.");
        }

        switch (tipo.toUpperCase()) {
            case "CLIENTE":
                System.out.printf("[FABRICA FACTORY] Instanciando Cliente: '%s' (%s)\n", nombre, email);
                return new Cliente(id, nombre, email, pass);

            case "ADMINISTRADOR":
            case "ADMIN":
                String nivel = params != null && params.containsKey("nivelAcceso") ? (String) params.get("nivelAcceso") : "SUPERADMIN";
                String dpto = params != null && params.containsKey("departamento") ? (String) params.get("departamento") : "GENERAL";
                System.out.printf("[FABRICA FACTORY] Instanciando Administrador: '%s' (%s - %s)\n", nombre, nivel, dpto);
                return new Administrador(id, nombre, email, pass, nivel, dpto);

            default:
                throw new IllegalArgumentException("[FABRICA] Tipo de usuario no reconocido: " + tipo);
        }
    }
}

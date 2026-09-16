package com.compraya.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Clase Usuario que define el comportamiento y datos generales de autenticación
 * y perfil de un usuario en la plataforma CompraYa.
 * Sirve como clase base para roles como Cliente y Administrador.
 */
public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String password;
    private LocalDateTime fechaRegistro;
    private boolean activo;

    /**
     * Constructor por defecto.
     */
    public Usuario() {
        this.fechaRegistro = LocalDateTime.now();
        this.activo = true;
    }

    /**
     * Constructor para inicializar objetos de la clase Usuario.
     * 
     * @param id       Identificador único del usuario.
     * @param nombre   Nombre completo del usuario.
     * @param email    Correo electrónico (utilizado para autenticación).
     * @param password Contraseña de acceso.
     */
    public Usuario(int id, String nombre, String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.fechaRegistro = LocalDateTime.now();
        this.activo = true;
    }

    // --- Métodos de Gestión y Autenticación ---

    /**
     * Valida las credenciales proporcionadas para iniciar sesión.
     * 
     * @param passContraseña a verificar.
     * @return true si la contraseña coincide y la cuenta está activa.
     */
    public boolean iniciarSesion(String pass) {
        if (!activo) {
            System.out.println("No se puede iniciar sesión: La cuenta de usuario está inactiva.");
            return false;
        }
        return this.password != null && this.password.equals(pass);
    }

    /**
     * Actualiza la información básica del perfil del usuario.
     * 
     * @param nuevoNombre Nuevo nombre completo.
     * @param nuevoEmail  Nuevo correo electrónico.
     */
    public void actualizarPerfil(String nuevoNombre, String nuevoEmail) {
        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
            this.nombre = nuevoNombre;
        }
        if (nuevoEmail != null && !nuevoEmail.trim().isEmpty()) {
            this.email = nuevoEmail;
        }
    }

    /**
     * Método destructor/limpieza lógica para cerrar la sesión y liberar recursos o marcar la cuenta inactiva.
     */
    public void destruirSesion() {
        System.out.println("Destruyendo sesión activa para el usuario ID " + id + " (" + email + ")...");
        // Limpieza de datos en memoria y desactivación de sesión
        this.activo = false;
    }

    // --- Getters y Setters ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Usuario[ID: %d | Nombre: %s | Email: %s | Activo: %b]",
                id, nombre, email, activo);
    }
}

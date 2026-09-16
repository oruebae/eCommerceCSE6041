package com.compraya.asignacion8.model;

/**
 * Clase abstracta Usuario para la Asignación 8.
 * Incluye validaciones de correo electrónico y formato de datos.
 */
public abstract class Usuario {
    private final int id;
    private String nombre;
    private String email;
    private String password;

    public Usuario(int id, String nombre, String email, String password) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID de usuario debe ser un entero positivo.");
        }
        setNombre(nombre);
        setEmail(email);
        setPassword(password);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("El formato del correo electrónico '" + email + "' no es válido.");
        }
        this.email = email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres.");
        }
        this.password = password;
    }

    public abstract String getTipoUsuario();

    @Override
    public String toString() {
        return String.format("%s [ID: %d | %s | %s]", getTipoUsuario(), id, nombre, email);
    }
}

package com.compraya.asignacion5.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Clase Usuario (Asignación 5).
 * Aplica Encapsulamiento Estricto con atributos privados y validaciones robustas en Mutadores (Setters).
 */
public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String password;
    private LocalDateTime fechaRegistro;
    private boolean activo;

    // Expresión regular RFC 5322 para validar correos electrónicos
    private static final Pattern PATRON_EMAIL = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public Usuario() {
        this.fechaRegistro = LocalDateTime.now();
        this.activo = true;
    }

    public Usuario(int id, String nombre, String email, String password) {
        this.id = id;
        setNombre(nombre);
        setEmail(email);
        setPassword(password);
        this.fechaRegistro = LocalDateTime.now();
        this.activo = true;
    }

    // --- Métodos de Negocio ---

    public boolean iniciarSesion(String pass) {
        if (!activo) {
            System.out.println("No se puede iniciar sesión: La cuenta está inactiva.");
            return false;
        }
        return this.password != null && this.password.equals(pass);
    }

    public void actualizarPerfil(String nuevoNombre, String nuevoEmail) {
        setNombre(nuevoNombre);
        setEmail(nuevoEmail);
    }

    public void destruirSesion() {
        System.out.println("Destruyendo sesión activa para el usuario ID " + id + " (" + email + ")...");
        this.activo = false;
    }

    // --- Getters y Setters Encapsulados con Validaciones ---

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
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("[VALIDACIÓN ENCAPSULADA] El nombre de usuario no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !PATRON_EMAIL.matcher(email).matches()) {
            throw new IllegalArgumentException(String.format("[VALIDACIÓN ENCAPSULADA] Formato de correo electrónico inválido: '%s'. Debe contener '@' y dominio válido.", email));
        }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.trim().length() < 6) {
            throw new IllegalArgumentException("[VALIDACIÓN ENCAPSULADA] La contraseña debe tener al menos 6 caracteres de longitud.");
        }
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
        return String.format("Usuario[ID: %d | Nombre: %s | Email: %s | Activo: %b]", id, nombre, email, activo);
    }
}

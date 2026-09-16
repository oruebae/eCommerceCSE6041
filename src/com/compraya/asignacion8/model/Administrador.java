package com.compraya.asignacion8.model;

/**
 * Clase derivada Administrador para la Asignación 8.
 */
public class Administrador extends Usuario {
    private String nivelAcceso;

    public Administrador(int id, String nombre, String email, String password, String nivelAcceso) {
        super(id, nombre, email, password);
        setNivelAcceso(nivelAcceso);
    }

    public String getNivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(String nivelAcceso) {
        if (nivelAcceso == null || nivelAcceso.trim().isEmpty()) {
            this.nivelAcceso = "ADMIN";
        } else {
            this.nivelAcceso = nivelAcceso.trim().toUpperCase();
        }
    }

    @Override
    public String getTipoUsuario() {
        return "Administrador";
    }
}

package com.compraya.asignacion6.model;

public class Administrador extends Usuario {
    private String nivelAcceso;

    public Administrador() {
        super();
        this.nivelAcceso = "SUPERADMIN";
    }

    public Administrador(int id, String nombre, String email, String password, String nivelAcceso) {
        super(id, nombre, email, password);
        this.nivelAcceso = nivelAcceso;
    }

    public String getNivelAcceso() { return nivelAcceso; }
    public void setNivelAcceso(String nivelAcceso) { this.nivelAcceso = nivelAcceso; }
}

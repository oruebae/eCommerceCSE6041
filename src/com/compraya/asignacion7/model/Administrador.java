package com.compraya.asignacion7.model;

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

    public String getNivelAcceso() { return nivelAcceso; }
    public String getDepartamento() { return departamento; }
}

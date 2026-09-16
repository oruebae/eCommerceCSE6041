package com.compraya.asignacion7.model;

import java.time.LocalDateTime;

public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String password;
    private LocalDateTime fechaRegistro;
    private boolean activo;

    public Usuario() {
        this.fechaRegistro = LocalDateTime.now();
        this.activo = true;
    }

    public Usuario(int id, String nombre, String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.fechaRegistro = LocalDateTime.now();
        this.activo = true;
    }

    public boolean iniciarSesion(String pass) {
        return activo && password != null && password.equals(pass);
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public boolean isActivo() { return activo; }
}

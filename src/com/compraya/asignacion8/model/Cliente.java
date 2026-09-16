package com.compraya.asignacion8.model;

/**
 * Clase derivada Cliente para la Asignación 8.
 */
public class Cliente extends Usuario {
    private String direccionEnvio;

    public Cliente(int id, String nombre, String email, String password, String direccionEnvio) {
        super(id, nombre, email, password);
        setDireccionEnvio(direccionEnvio);
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio != null ? direccionEnvio : "Dirección no registrada";
    }

    @Override
    public String getTipoUsuario() {
        return "Cliente";
    }
}

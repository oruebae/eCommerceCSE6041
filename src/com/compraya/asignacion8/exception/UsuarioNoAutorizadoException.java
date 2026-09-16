package com.compraya.asignacion8.exception;

/**
 * Excepción lanzada cuando un usuario intenta realizar una operación para la cual no posee privilegios.
 */
public class UsuarioNoAutorizadoException extends EcommerceException {

    private final String usuarioEmail;
    private final String operacionSolicitada;

    public UsuarioNoAutorizadoException(String usuarioEmail, String operacionSolicitada) {
        super(String.format("El usuario '%s' no está autorizado para realizar la operación '%s'", 
                usuarioEmail, operacionSolicitada), "ERR_USUARIO_NO_AUTORIZADO");
        this.usuarioEmail = usuarioEmail;
        this.operacionSolicitada = operacionSolicitada;
    }

    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    public String getOperacionSolicitada() {
        return operacionSolicitada;
    }
}

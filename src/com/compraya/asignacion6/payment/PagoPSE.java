package com.compraya.asignacion6.payment;

/**
 * Clase Concreta PagoPSE (Asignación 6).
 * Implementa ProcesoPago para pagos mediante débito directo bancario PSE en Colombia.
 */
public class PagoPSE implements ProcesoPago {
    private String bancoOrigen;
    private String tipoCliente; // "PERSONA_NATURAL" o "PERSONA_JURIDICA"
    private String identificacionCliente;
    private String estadoTransaccion;
    private double montoProcesado;

    public PagoPSE(String bancoOrigen, String tipoCliente, String identificacionCliente) {
        this.bancoOrigen = bancoOrigen;
        this.tipoCliente = tipoCliente;
        this.identificacionCliente = identificacionCliente;
        this.estadoTransaccion = "NO_INICIADO";
    }

    @Override
    public boolean iniciarPago(double monto, String referencia) {
        this.montoProcesado = monto;
        System.out.printf("[PAGO PSE] Conectando con portal bancario %s (%s - CC/NIT: %s) por $%.2f | Ref: '%s'...\n",
                bancoOrigen, tipoCliente, identificacionCliente, monto, referencia);
        this.estadoTransaccion = "INICIADO";
        return true;
    }

    @Override
    public boolean verificarPago(String idTransaccion) {
        System.out.printf("[PAGO PSE] Verificando débito inmediato en cuenta del banco %s para TXN: %s...\n", bancoOrigen, idTransaccion);
        this.estadoTransaccion = "VERIFICADO";
        return true;
    }

    @Override
    public boolean confirmarPago(String idTransaccion) {
        if ("VERIFICADO".equals(estadoTransaccion) || "INICIADO".equals(estadoTransaccion)) {
            this.estadoTransaccion = "APROBADO_Y_LIQUIDADO";
            System.out.printf("[PAGO PSE] ¡Débito bancario en línea PSE confirmado desde %s! TXN: %s por $%.2f\n",
                    bancoOrigen, idTransaccion, montoProcesado);
            return true;
        }
        return false;
    }

    @Override
    public String getNombreMetodo() {
        return "PSE Débito Bancario en Línea (" + bancoOrigen + ")";
    }
}

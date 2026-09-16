package com.compraya.asignacion8.test;

import com.compraya.asignacion8.exception.PagoFallidoException;
import com.compraya.asignacion8.payment.PagoPayPal;
import com.compraya.asignacion8.payment.PagoTarjeta;

public class PagoTest {

    public static void ejecutarPruebas() {
        System.out.println("\n--- [SUITE] Pruebas Unitarias para Pasarelas de Pago ---");

        // 1. Pago con Tarjeta Exitoso
        PagoTarjeta tarjetaValida = new PagoTarjeta("4532111122223333", "Alberto López", 5000000.0);
        TestFramework.assertTrue(tarjetaValida.procesarPago(1500000.0, "REF-TEST-101"), 
                "Pago con tarjeta aprobado por dentro del límite de crédito");

        // 2. Excepción Pago con Tarjeta por Exceder Límite (PagoFallidoException)
        TestFramework.assertThrows(PagoFallidoException.class, () -> {
            tarjetaValida.procesarPago(6000000.0, "REF-TEST-102"); // Excede límite de 5.000.000
        }, "Debe lanzar PagoFallidoException cuando el monto supera el límite de crédito");

        // 3. Pago PayPal Exitoso
        PagoPayPal paypalValido = new PagoPayPal("cliente@paypal.com", true);
        TestFramework.assertTrue(paypalValido.procesarPago(250000.0, "REF-PAYPAL-201"), 
                "Pago PayPal aprobado con saldo disponible");

        // 4. Excepción Pago PayPal por Saldo Insuficiente (PagoFallidoException)
        PagoPayPal paypalSinSaldo = new PagoPayPal("sin_saldo@paypal.com", false);
        TestFramework.assertThrows(PagoFallidoException.class, () -> {
            paypalSinSaldo.procesarPago(100000.0, "REF-PAYPAL-202");
        }, "Debe lanzar PagoFallidoException cuando la cuenta PayPal no posee saldo suficiente");

        // 5. Excepción por Monto Negativo o Cero (PagoFallidoException)
        TestFramework.assertThrows(PagoFallidoException.class, () -> {
            tarjetaValida.procesarPago(-500.0, "REF-ERR");
        }, "Debe lanzar PagoFallidoException si se intenta procesar un monto negativo");
    }
}

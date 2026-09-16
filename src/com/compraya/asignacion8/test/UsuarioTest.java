package com.compraya.asignacion8.test;

import com.compraya.asignacion8.model.Administrador;
import com.compraya.asignacion8.model.Cliente;

public class UsuarioTest {

    public static void ejecutarPruebas() {
        System.out.println("\n--- [SUITE] Pruebas Unitarias para Usuario ---");

        // 1. Creación exitosa de Cliente
        Cliente cliente = new Cliente(1, "Alberto López", "alberto@example.com", "SecurePass123", "Carrera 7 #71-21");
        TestFramework.assertEquals("Alberto López", cliente.getNombre(), "Nombre del cliente correcto");
        TestFramework.assertEquals("alberto@example.com", cliente.getEmail(), "Email del cliente correcto");

        // 2. Creación exitosa de Administrador
        Administrador admin = new Administrador(2, "Super Admin", "admin@compraya.com", "AdminPass999", "SUPERADMIN");
        TestFramework.assertEquals("SUPERADMIN", admin.getNivelAcceso(), "Nivel de acceso admin correcto");

        // 3. Excepción por Formato de Email Inválido
        TestFramework.assertThrows(IllegalArgumentException.class, () -> {
            new Cliente(3, "Usuario Invalido", "correo_sin_arroba.com", "Password123", "Calle 1");
        }, "Debe lanzar IllegalArgumentException para un email sin formato válido");

        // 4. Excepción por Contraseña Corta
        TestFramework.assertThrows(IllegalArgumentException.class, () -> {
            new Cliente(4, "Usuario Pass Corta", "user@test.com", "123", "Calle 1");
        }, "Debe lanzar IllegalArgumentException si la contraseña tiene menos de 6 caracteres");
    }
}

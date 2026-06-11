package com.hector.papeleriasaas.utils;

import javax.swing.*;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class MessageUtils {

    public static void informationMessage(String message, JComponent content) {
        JOptionPane.showMessageDialog(content, message, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void warningMessage(String message, JComponent content) {
        JOptionPane.showMessageDialog(content, message, "Advertencia", JOptionPane.WARNING_MESSAGE);
    }

    public static void errorMessage(String message, JComponent content) {
        JOptionPane.showMessageDialog(content, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static String debtPaymentMessage(String message, JComponent content) {
        return JOptionPane.showInputDialog(content, message, "Pago Deudas", JOptionPane.PLAIN_MESSAGE);
    }

    public static String userMessage(Throwable throwable) {
        Throwable cause = rootCause(throwable);
        String message = cause.getMessage();

        if (cause instanceof NumberFormatException) {
            return "Ingrese un valor numérico válido.";
        }

        if (cause instanceof SQLException sqlException) {
            return databaseMessage(sqlException);
        }

        if (message == null || message.isBlank()) {
            return "No fue posible completar la operación. Intente nuevamente.";
        }

        return message;
    }

    private static Throwable rootCause(Throwable throwable) {
        Throwable cause = throwable;

        if (cause instanceof ExecutionException && cause.getCause() != null) {
            cause = cause.getCause();
        }

        while (cause.getCause() != null) {
            cause = cause.getCause();
        }

        return cause;
    }

    private static String databaseMessage(SQLException exception) {
        String message = exception.getMessage();
        String normalized = message == null ? "" : message.toLowerCase();

        if (normalized.contains("stock suficiente")) {
            return "No hay stock suficiente para registrar la venta. Actualice el inventario y vuelva a intentarlo.";
        }

        if (normalized.contains("producto no existe")) {
            return "El producto seleccionado ya no existe en el inventario.";
        }

        if (normalized.contains("cliente no existe")) {
            return "El cliente seleccionado ya no existe.";
        }

        if (normalized.contains("proveedor no existe")) {
            return "El proveedor seleccionado ya no existe.";
        }

        if (normalized.contains("venta no existe")) {
            return "La venta seleccionada ya no existe.";
        }

        if (normalized.contains("compra no existe")) {
            return "La compra seleccionada ya no existe.";
        }

        if ("23000".equals(exception.getSQLState())) {
            if (normalized.contains("duplicate")) {
                return "Ya existe un registro con esa identificación.";
            }

            if (normalized.contains("foreign key")) {
                return "No se puede completar la operación porque el registro está relacionado con otros datos.";
            }

            if (normalized.contains("check constraint")) {
                return "Revise cantidades y valores: no se permiten números negativos ni cantidades en cero.";
            }
        }

        if (normalized.contains("communications link failure") || normalized.contains("connection")) {
            return "No se pudo conectar con la base de datos. Verifique que MySQL esté iniciado.";
        }

        return "No fue posible guardar la información en la base de datos. Revise los datos e intente nuevamente.";
    }

}

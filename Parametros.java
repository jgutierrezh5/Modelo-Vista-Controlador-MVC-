package controlador;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/** Utilidades compartidas por los controladores para leer y validar formularios. */
final class Parametros {

    private Parametros() {
    }

    /** Devuelve el parámetro sin espacios al inicio ni al final, o "" si no viene. */
    static String texto(HttpServletRequest req, String nombre) {
        String valor = req.getParameter(nombre);
        return valor == null ? "" : valor.trim();
    }

    /** Verdadero si el texto no está vacío y cabe en la columna de la base de datos. */
    static boolean textoValido(String valor, int longitudMaxima) {
        return !valor.isEmpty() && valor.length() <= longitudMaxima;
    }

    /** Devuelve el parámetro como entero, o null si no es un número entero válido. */
    static Integer entero(HttpServletRequest req, String nombre) {
        try {
            return Integer.valueOf(texto(req, nombre));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /** Devuelve el parámetro como decimal, o null si no es un número válido. */
    static BigDecimal decimal(HttpServletRequest req, String nombre) {
        try {
            return new BigDecimal(texto(req, nombre));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /** Redirige al módulo indicado, agregando un código de error si lo hay. */
    static void redirigir(HttpServletRequest req, HttpServletResponse resp, String ruta, String error)
            throws IOException {
        String destino = req.getContextPath() + ruta;
        if (error != null) {
            destino += "?error=" + URLEncoder.encode(error, StandardCharsets.UTF_8);
        }
        resp.sendRedirect(destino);
    }
}

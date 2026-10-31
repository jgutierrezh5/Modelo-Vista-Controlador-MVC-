package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import modelo.Marca;
import modelo.MarcaDAO;

/** Controlador de marcas (equivalente a controlador/MarcaController.php). */
@WebServlet("/marcas")
public class MarcaServlet extends HttpServlet {

    private final MarcaDAO marcaDAO = new MarcaDAO();

    /** GET /marcas: muestra el formulario y el listado. */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            req.setAttribute("marcas", marcaDAO.listar());
        } catch (SQLException e) {
            throw new ServletException("No se pudieron cargar las marcas.", e);
        }
        req.getRequestDispatcher("/WEB-INF/vista/marcas/index.jsp").forward(req, resp);
    }

    /** POST /marcas: accion=guardar o accion=eliminar. */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String error = null;
        try {
            switch (Parametros.texto(req, "accion")) {
                case "guardar" -> error = guardar(req);
                case "eliminar" -> error = eliminar(req);
                default -> { }
            }
        } catch (SQLException e) {
            throw new ServletException("Error al acceder a la base de datos.", e);
        }
        Parametros.redirigir(req, resp, "/marcas", error);
    }

    private String guardar(HttpServletRequest req) throws SQLException {
        String nombre = Parametros.texto(req, "nombre");
        String pais = Parametros.texto(req, "pais");

        if (!Parametros.textoValido(nombre, 100) || !Parametros.textoValido(pais, 80)) {
            return "datos_invalidos";
        }
        marcaDAO.guardar(new Marca(nombre, pais));
        return null;
    }

    private String eliminar(HttpServletRequest req) throws SQLException {
        Integer id = Parametros.entero(req, "id");
        if (id != null && !marcaDAO.eliminar(id)) {
            return "marca_en_uso";
        }
        return null;
    }
}

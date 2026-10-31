package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;
import modelo.Cliente;
import modelo.ClienteDAO;

/** Controlador de clientes (equivalente a controlador/ClienteController.php). */
@WebServlet("/clientes")
public class ClienteServlet extends HttpServlet {

    private static final Pattern CORREO = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");

    private final ClienteDAO clienteDAO = new ClienteDAO();

    /** GET /clientes: muestra el formulario y el listado. */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            req.setAttribute("clientes", clienteDAO.listar());
        } catch (SQLException e) {
            throw new ServletException("No se pudieron cargar los clientes.", e);
        }
        req.getRequestDispatcher("/WEB-INF/vista/clientes/index.jsp").forward(req, resp);
    }

    /** POST /clientes: accion=guardar o accion=eliminar. */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String error = null;
        try {
            switch (Parametros.texto(req, "accion")) {
                case "guardar" -> error = guardar(req);
                case "eliminar" -> eliminar(req);
                default -> { }
            }
        } catch (SQLException e) {
            throw new ServletException("Error al acceder a la base de datos.", e);
        }
        Parametros.redirigir(req, resp, "/clientes", error);
    }

    private String guardar(HttpServletRequest req) throws SQLException {
        String nombre = Parametros.texto(req, "nombre");
        String telefono = Parametros.texto(req, "telefono");
        String correo = Parametros.texto(req, "correo");

        if (!Parametros.textoValido(nombre, 120)
                || !Parametros.textoValido(telefono, 30)
                || !Parametros.textoValido(correo, 120)
                || !CORREO.matcher(correo).matches()) {
            return "datos_invalidos";
        }
        clienteDAO.guardar(new Cliente(nombre, telefono, correo));
        return null;
    }

    private void eliminar(HttpServletRequest req) throws SQLException {
        Integer id = Parametros.entero(req, "id");
        if (id != null) {
            clienteDAO.eliminar(id);
        }
    }
}

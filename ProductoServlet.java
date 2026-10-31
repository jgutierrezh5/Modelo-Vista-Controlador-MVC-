package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import modelo.MarcaDAO;
import modelo.Producto;
import modelo.ProductoDAO;

/** Controlador de productos (equivalente a controlador/ProductoController.php). */
@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {

    /** Máximo que admite la columna DECIMAL(10,2). */
    private static final BigDecimal PRECIO_MAXIMO = new BigDecimal("99999999.99");

    private final ProductoDAO productoDAO = new ProductoDAO();
    private final MarcaDAO marcaDAO = new MarcaDAO();

    /** GET /productos: muestra el formulario y el listado. */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            req.setAttribute("productos", productoDAO.listar());
            req.setAttribute("marcas", marcaDAO.listar());
        } catch (SQLException e) {
            throw new ServletException("No se pudieron cargar los productos.", e);
        }
        req.getRequestDispatcher("/WEB-INF/vista/productos/index.jsp").forward(req, resp);
    }

    /** POST /productos: accion=guardar o accion=eliminar. */
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
        Parametros.redirigir(req, resp, "/productos", error);
    }

    private String guardar(HttpServletRequest req) throws SQLException {
        String nombre = Parametros.texto(req, "nombre");
        BigDecimal precio = Parametros.decimal(req, "precio");
        Integer stock = Parametros.entero(req, "stock");
        Integer marcaId = Parametros.entero(req, "marca_id");

        if (!Parametros.textoValido(nombre, 120)
                || precio == null || precio.signum() < 0 || precio.compareTo(PRECIO_MAXIMO) > 0
                || stock == null || stock < 0
                || marcaId == null) {
            return "datos_invalidos";
        }

        precio = precio.setScale(2, RoundingMode.HALF_UP);
        if (!productoDAO.guardar(new Producto(nombre, precio, stock, marcaId))) {
            return "marca_inexistente";
        }
        return null;
    }

    private void eliminar(HttpServletRequest req) throws SQLException {
        Integer id = Parametros.entero(req, "id");
        if (id != null) {
            productoDAO.eliminar(id);
        }
    }
}

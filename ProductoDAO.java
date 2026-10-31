package modelo;

import config.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/** Operaciones de base de datos para productos (equivalente a modelo/Producto.php). */
public class ProductoDAO {

    public List<Producto> listar() throws SQLException {
        String sql = "SELECT p.id, p.nombre, p.precio, p.stock, p.marca_id, m.nombre AS marca_nombre "
                   + "FROM productos p "
                   + "INNER JOIN marcas m ON p.marca_id = m.id "
                   + "ORDER BY p.id DESC";
        List<Producto> productos = new ArrayList<>();

        try (Connection conn = Database.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                productos.add(new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getBigDecimal("precio"),
                        rs.getInt("stock"),
                        rs.getInt("marca_id"),
                        rs.getString("marca_nombre")));
            }
        }
        return productos;
    }

    /**
     * Guarda un producto.
     * @return false si la marca indicada no existe.
     */
    public boolean guardar(Producto producto) throws SQLException {
        String sql = "INSERT INTO productos (nombre, precio, stock, marca_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setBigDecimal(2, producto.getPrecio());
            stmt.setInt(3, producto.getStock());
            stmt.setInt(4, producto.getMarcaId());
            stmt.executeUpdate();
            return true;
        } catch (SQLIntegrityConstraintViolationException e) {
            return false;
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM productos WHERE id = ?";

        try (Connection conn = Database.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

package modelo;

import config.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/** Operaciones de base de datos para marcas (equivalente a modelo/Marca.php). */
public class MarcaDAO {

    public List<Marca> listar() throws SQLException {
        String sql = "SELECT id, nombre, pais FROM marcas ORDER BY id DESC";
        List<Marca> marcas = new ArrayList<>();

        try (Connection conn = Database.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                marcas.add(new Marca(rs.getInt("id"), rs.getString("nombre"), rs.getString("pais")));
            }
        }
        return marcas;
    }

    public void guardar(Marca marca) throws SQLException {
        String sql = "INSERT INTO marcas (nombre, pais) VALUES (?, ?)";

        try (Connection conn = Database.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, marca.getNombre());
            stmt.setString(2, marca.getPais());
            stmt.executeUpdate();
        }
    }

    /**
     * Elimina una marca.
     * @return false si la marca tiene productos asociados y no se puede eliminar.
     */
    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM marcas WHERE id = ?";

        try (Connection conn = Database.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLIntegrityConstraintViolationException e) {
            return false;
        }
    }
}

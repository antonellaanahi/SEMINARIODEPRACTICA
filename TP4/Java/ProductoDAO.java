import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO extends AbstractDAO<Producto> {

    @Override
    public void insertar(Producto producto) {
        String query = "INSERT INTO inventarios (producto, cantidad, fecha_vencimiento, proveedor_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, producto.getNombre());
            ps.setInt(2, producto.getCantidad());
            ps.setString(3, producto.getFechaVencimiento());
            if (producto.getProveedorId() == 0) ps.setNull(4, Types.INTEGER);
            else ps.setInt(4, producto.getProveedorId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar producto: " + e.getMessage());
        }
    }

    @Override
    public Producto obtener(int id) {
        String query = "SELECT * FROM inventarios WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Producto p = new Producto(rs.getString("producto"), rs.getInt("cantidad"),
                    rs.getString("fecha_vencimiento"), rs.getInt("proveedor_id"));
                p.setId(rs.getInt("id"));
                return p;
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener producto: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Producto> obtenerTodos() {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM inventarios";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto(rs.getString("producto"), rs.getInt("cantidad"),
                    rs.getString("fecha_vencimiento"), rs.getInt("proveedor_id"));
                p.setId(rs.getInt("id"));
                productos.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener productos: " + e.getMessage());
        }
        return productos;
    }

    @Override
    public void actualizar(Producto producto) {
        String query = "UPDATE inventarios SET producto=?, cantidad=?, fecha_vencimiento=?, proveedor_id=? WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, producto.getNombre());
            ps.setInt(2, producto.getCantidad());
            ps.setString(3, producto.getFechaVencimiento());
            ps.setInt(4, producto.getProveedorId());
            ps.setInt(5, producto.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String query = "DELETE FROM inventarios WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
        }
    }
}

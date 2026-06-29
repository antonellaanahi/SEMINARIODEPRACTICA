import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO extends AbstractDAO<Factura> {

    @Override
    public void insertar(Factura factura) {
        String query = "INSERT INTO facturas (paciente_id, fecha, total, estado) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, factura.getPacienteId());
            ps.setString(2, factura.getFecha());
            ps.setDouble(3, factura.getTotal());
            ps.setString(4, factura.getEstado());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar factura: " + e.getMessage());
        }
    }

    @Override
    public Factura obtener(int id) {
        String query = "SELECT * FROM facturas WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Factura f = new Factura(rs.getInt("paciente_id"), rs.getString("fecha"),
                    rs.getDouble("total"), rs.getString("estado"));
                f.setId(rs.getInt("id"));
                return f;
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener factura: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Factura> obtenerTodos() {
        List<Factura> facturas = new ArrayList<>();
        String query = "SELECT * FROM facturas";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Factura f = new Factura(rs.getInt("paciente_id"), rs.getString("fecha"),
                    rs.getDouble("total"), rs.getString("estado"));
                f.setId(rs.getInt("id"));
                facturas.add(f);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener facturas: " + e.getMessage());
        }
        return facturas;
    }

    @Override
    public void actualizar(Factura factura) {
        String query = "UPDATE facturas SET paciente_id=?, fecha=?, total=?, estado=? WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, factura.getPacienteId());
            ps.setString(2, factura.getFecha());
            ps.setDouble(3, factura.getTotal());
            ps.setString(4, factura.getEstado());
            ps.setInt(5, factura.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar factura: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String query = "DELETE FROM facturas WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar factura: " + e.getMessage());
        }
    }
}

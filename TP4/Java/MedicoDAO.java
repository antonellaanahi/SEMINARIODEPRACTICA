import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO extends AbstractDAO<Medico> {

    @Override
    public void insertar(Medico medico) {
        String query = "INSERT INTO medicos (nombre, apellido, especialidad, telefono, correo_electronico) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, medico.getNombre());
            ps.setString(2, medico.getApellido());
            ps.setString(3, medico.getEspecialidad());
            ps.setString(4, medico.getTelefono());
            ps.setString(5, medico.getCorreo());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar medico: " + e.getMessage());
        }
    }

    @Override
    public Medico obtener(int id) {
        String query = "SELECT * FROM medicos WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Medico m = new Medico(
                    rs.getString("nombre"), rs.getString("apellido"),
                    rs.getString("especialidad"), rs.getString("telefono"),
                    rs.getString("correo_electronico")
                );
                m.setId(rs.getInt("id"));
                return m;
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener medico: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Medico> obtenerTodos() {
        List<Medico> medicos = new ArrayList<>();
        String query = "SELECT * FROM medicos";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Medico m = new Medico(
                    rs.getString("nombre"), rs.getString("apellido"),
                    rs.getString("especialidad"), rs.getString("telefono"),
                    rs.getString("correo_electronico")
                );
                m.setId(rs.getInt("id"));
                medicos.add(m);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener medicos: " + e.getMessage());
        }
        return medicos;
    }

    @Override
    public void actualizar(Medico medico) {
        String query = "UPDATE medicos SET nombre=?, apellido=?, especialidad=?, telefono=?, correo_electronico=? WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, medico.getNombre());
            ps.setString(2, medico.getApellido());
            ps.setString(3, medico.getEspecialidad());
            ps.setString(4, medico.getTelefono());
            ps.setString(5, medico.getCorreo());
            ps.setInt(6, medico.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar medico: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String query = "DELETE FROM medicos WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar medico: " + e.getMessage());
        }
    }
}

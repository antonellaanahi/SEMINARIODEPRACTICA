import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO extends AbstractDAO<Paciente> {

    @Override
    public void insertar(Paciente paciente) {
        String query = "INSERT INTO pacientes (nombre, apellido, fecha_nacimiento, direccion, telefono, correo_electronico) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellido());
            ps.setString(3, paciente.getFechaNacimiento());
            ps.setString(4, paciente.getDireccion());
            ps.setString(5, paciente.getTelefono());
            ps.setString(6, paciente.getCorreo());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar paciente: " + e.getMessage());
        }
    }

    @Override
    public Paciente obtener(int id) {
        String query = "SELECT * FROM pacientes WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Paciente p = new Paciente(
                    rs.getString("nombre"), rs.getString("apellido"),
                    rs.getString("fecha_nacimiento"), rs.getString("direccion"),
                    rs.getString("telefono"), rs.getString("correo_electronico")
                );
                p.setId(rs.getInt("id"));
                return p;
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener paciente: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Paciente> obtenerTodos() {
        List<Paciente> pacientes = new ArrayList<>();
        String query = "SELECT * FROM pacientes";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Paciente p = new Paciente(
                    rs.getString("nombre"), rs.getString("apellido"),
                    rs.getString("fecha_nacimiento"), rs.getString("direccion"),
                    rs.getString("telefono"), rs.getString("correo_electronico")
                );
                p.setId(rs.getInt("id"));
                pacientes.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener pacientes: " + e.getMessage());
        }
        return pacientes;
    }

    @Override
    public void actualizar(Paciente paciente) {
        String query = "UPDATE pacientes SET nombre=?, apellido=?, fecha_nacimiento=?, direccion=?, telefono=?, correo_electronico=? WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellido());
            ps.setString(3, paciente.getFechaNacimiento());
            ps.setString(4, paciente.getDireccion());
            ps.setString(5, paciente.getTelefono());
            ps.setString(6, paciente.getCorreo());
            ps.setInt(7, paciente.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar paciente: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String query = "DELETE FROM pacientes WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar paciente: " + e.getMessage());
        }
    }
}

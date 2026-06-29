import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO extends AbstractDAO<Cita> {

    @Override
    public void insertar(Cita cita) {
        String query = "INSERT INTO citas (paciente_id, medico_id, fecha_hora, estado) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, cita.getPacienteId());
            ps.setInt(2, cita.getMedicoId());
            ps.setString(3, cita.getFechaHora());
            ps.setString(4, cita.getEstado());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar cita: " + e.getMessage());
        }
    }

    @Override
    public Cita obtener(int id) {
        String query = "SELECT * FROM citas WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Cita c = new Cita(rs.getInt("paciente_id"), rs.getInt("medico_id"),
                    rs.getString("fecha_hora"), rs.getString("estado"));
                c.setId(rs.getInt("id"));
                return c;
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener cita: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Cita> obtenerTodos() {
        List<Cita> citas = new ArrayList<>();
        String query = "SELECT c.*, CONCAT(p.nombre,' ',p.apellido) AS paciente, CONCAT(m.nombre,' ',m.apellido) AS medico " +
                       "FROM citas c JOIN pacientes p ON c.paciente_id=p.id JOIN medicos m ON c.medico_id=m.id";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cita c = new Cita(rs.getInt("paciente_id"), rs.getInt("medico_id"),
                    rs.getString("fecha_hora"), rs.getString("estado"));
                c.setId(rs.getInt("id"));
                citas.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener citas: " + e.getMessage());
        }
        return citas;
    }

    @Override
    public void actualizar(Cita cita) {
        String query = "UPDATE citas SET paciente_id=?, medico_id=?, fecha_hora=?, estado=? WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, cita.getPacienteId());
            ps.setInt(2, cita.getMedicoId());
            ps.setString(3, cita.getFechaHora());
            ps.setString(4, cita.getEstado());
            ps.setInt(5, cita.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar cita: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String query = "DELETE FROM citas WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar cita: " + e.getMessage());
        }
    }
}

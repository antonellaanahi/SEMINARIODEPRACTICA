import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractDAO<T> implements DAO<T> {
    protected Connection connection;

    public AbstractDAO() {
        try {
            this.connection = ConexionDB.getConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

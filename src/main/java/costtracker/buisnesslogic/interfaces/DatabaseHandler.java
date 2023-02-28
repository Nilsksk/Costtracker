package costtracker.buisnesslogic.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface DatabaseHandler<T> {

    T getById(int id) throws SQLException;

    List<T> getAll() throws SQLException;

    boolean deleteById(int id) throws SQLException;

    boolean update(T object) throws SQLException;

    boolean create(T object) throws SQLException;

}

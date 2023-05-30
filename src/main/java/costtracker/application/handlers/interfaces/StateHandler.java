package costtracker.application.handlers.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface StateHandler<T> {

    boolean enable(int id) throws SQLException;

    boolean disable(int id) throws SQLException;

    List<T> getEnabled() throws SQLException;

    List<T> getDisabled() throws SQLException;

}

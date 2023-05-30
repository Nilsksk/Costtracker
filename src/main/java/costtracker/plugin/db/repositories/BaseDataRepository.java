package costtracker.plugin.db.repositories;

import java.sql.SQLException;
import java.util.List;

public interface BaseDataRepository<T> extends Repository<T> {
	public boolean enable(int id) throws SQLException;
	
	public boolean disable(int id) throws SQLException;
	
	public List<T> getEnabled() throws SQLException;

	public List<T> getDisabled() throws SQLException;
}

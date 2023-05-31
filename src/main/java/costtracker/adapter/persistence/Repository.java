package costtracker.adapter.persistence;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {
	
	public T get(int id) throws SQLException;
	
	public boolean update(T entity) throws SQLException;
	
	public boolean insert(T entity) throws SQLException;
	
	public boolean delete(int id) throws SQLException;
	
	public List<T> getAll() throws SQLException;
	
}

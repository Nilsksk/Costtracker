package costtracker.db.repositories;

import java.sql.SQLException;

public interface Repository<T> {
	
	public T select(int id) throws SQLException;
	
	public boolean update(T entity) throws SQLException;
	
	public boolean insert(T entity) throws SQLException;
	
	public boolean delete(int id) throws SQLException;
	
	public boolean delete(T entity) throws SQLException;
}

package costtracker.db.repositories;

public interface Repository<T> {
	
	public T select(int id);
	
	public boolean update(T entity);
	
	public boolean insert(T entity);
	
	public boolean delete(int id);
	
	public boolean delete(T entity);
}

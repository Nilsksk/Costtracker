package costtracker.db.repositories;

import java.sql.Connection;

public abstract class RepositoryBase {
	
	private Connection connection;
	
	public RepositoryBase(Connection connection) {
		this.connection = connection;
	}
}

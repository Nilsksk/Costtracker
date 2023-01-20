package costtracker.db.repositories;

import java.sql.Connection;

import costtracker.db.entities.CompanyEntity;

public class CompanyRepository extends RepositoryBase implements Repository<CompanyEntity> {

	public CompanyRepository(Connection connection) {
		super(connection);
	}

	@Override
	public CompanyEntity select(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(CompanyEntity entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(CompanyEntity entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(CompanyEntity entity) {
		// TODO Auto-generated method stub
		return false;
	}

}

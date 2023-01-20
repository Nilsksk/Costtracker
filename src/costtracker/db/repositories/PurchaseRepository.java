package costtracker.db.repositories;

import java.sql.Connection;

import costtracker.db.entities.PurchaseEntity;

public class PurchaseRepository extends RepositoryBase implements Repository<PurchaseEntity> {

	public PurchaseRepository(Connection connection) {
		super(connection);
	}

	@Override
	public PurchaseEntity select(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(PurchaseEntity entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(PurchaseEntity entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(PurchaseEntity entity) {
		// TODO Auto-generated method stub
		return false;
	}

}

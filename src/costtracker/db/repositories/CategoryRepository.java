package costtracker.db.repositories;

import java.sql.Connection;

import costtracker.db.entities.CategoryEntity;

public class CategoryRepository extends RepositoryBase implements Repository<CategoryEntity> {

	public CategoryRepository(Connection connection) {
		super(connection);
	}

	@Override
	public CategoryEntity select(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(CategoryEntity entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(CategoryEntity entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(CategoryEntity entity) {
		// TODO Auto-generated method stub
		return false;
	}

}

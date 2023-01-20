package costtracker.db.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import costtracker.db.entities.CategoryEntity;

public class CategoryRepository extends RepositoryBase implements Repository<CategoryEntity> {

	public CategoryRepository(Connection connection) {
		super(connection);
	}

	@Override
	public CategoryEntity select(int id) throws SQLException {
		CategoryEntity entity = null;
		String sql = "select * from category where id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);
		
		var result = stmt.executeQuery();
		if(result.next()) {
			entity = new CategoryEntity(result.getInt("id"), result.getString("name"));
		}
			
		return entity;
	}

	@Override
	public boolean update(CategoryEntity entity) throws SQLException {
		String sql = "update category set name = ? where id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, entity.getName());
		stmt.setInt(2, entity.getId());
		
		var result = stmt.executeUpdate();
		
		return result == 1;
	}

	@Override
	public boolean insert(CategoryEntity entity) throws SQLException {
		String sql = "insert into category(name) values (?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, entity.getName());
		
		var result = stmt.executeUpdate();
		
		return result == 1;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		String sql = "delete from category where id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);
		
		var result = stmt.executeUpdate();
		
		return result == 1;
	}

	@Override
	public boolean delete(CategoryEntity entity) throws SQLException {
		return this.delete(entity.getId());
	}

}

package costtracker.plugin.db.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import costtracker.adapter.entities.CategoryEntity;
import costtracker.adapter.persistence.BaseDataRepository;

public class CategoryRepository extends RepositoryBase implements BaseDataRepository<CategoryEntity> {

	public CategoryRepository(Connection connection) {
		super(connection);
	}

	@Override
	public CategoryEntity get(int id) throws SQLException {
		CategoryEntity entity = null;
		String sql = "select * from category where id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);

		ResultSet result = stmt.executeQuery();
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
		
		int result = stmt.executeUpdate();
		
		return result == 1;
	}

	@Override
	public boolean insert(CategoryEntity entity) throws SQLException {
		String sql = "insert into category(name, isenabled) values (?, true)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, entity.getName());
		
		int result = stmt.executeUpdate();
		
		return result == 1;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		String sql = "delete from category where id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);
		
		int result = stmt.executeUpdate();
		
		return result == 1;
	}

	@Override
	public List<CategoryEntity> getAll() throws SQLException {
		String sql = "select id, name from category";
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		ResultSet result = stmt.executeQuery();
		
		List<CategoryEntity> categories = new ArrayList<CategoryEntity>();
		
		while(result.next()) {
			categories.add(new CategoryEntity(result.getInt("id"), result.getString("name")));
		}
		
		return categories;
	}

	@Override
	public boolean enable(int id) throws SQLException {
		String sql = "update category set isenabled = true where id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);
		
		int result = stmt.executeUpdate();
		
		return result == 1;
	}

	@Override
	public boolean disable(int id) throws SQLException {
		String sql = "update category set isenabled = false where id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);
		
		int result = stmt.executeUpdate();
		
		return result == 1;
	}

	@Override
	public List<CategoryEntity> getEnabled() throws SQLException {
		String sql = "select id, name from category where isenabled = true";
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		ResultSet result = stmt.executeQuery();
		
		List<CategoryEntity> categories = new ArrayList<CategoryEntity>();
		
		while(result.next()) {
			categories.add(new CategoryEntity(result.getInt("id"), result.getString("name")));
		}
		
		return categories;
	}

	@Override
	public List<CategoryEntity> getDisabled() throws SQLException {
		String sql = "select id, name from category where isenabled = false";
		PreparedStatement stmt = connection.prepareStatement(sql);

		ResultSet result = stmt.executeQuery();

		List<CategoryEntity> categories = new ArrayList<CategoryEntity>();

		while(result.next()) {
			categories.add(new CategoryEntity(result.getInt("id"), result.getString("name")));
		}

		return categories;
	}
}

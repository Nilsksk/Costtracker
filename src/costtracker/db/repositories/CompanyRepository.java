package costtracker.db.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import costtracker.db.entities.CompanyEntity;

public class CompanyRepository extends RepositoryBase implements Repository<CompanyEntity> {

	public CompanyRepository(Connection connection) {
		super(connection);
	}

	@Override
	public CompanyEntity select(int id) throws SQLException {
		// TODO Auto-generated method stub
		CompanyEntity entity = null;
		String sql = "select * from company where id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);
		
		var result = stmt.executeQuery();
		if(result.next()) {
			entity = new CompanyEntity(result.getInt("id"), result.getString("name"), result.getString("location"));
		}
			
		return entity;
	}

	@Override
	public boolean update(CompanyEntity entity) throws SQLException {
		String sql = "update company set name = ?, location = ? where id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, entity.getName());
		stmt.setString(2, entity.getLocation());
		stmt.setInt(3, entity.getId());
		
		var result = stmt.executeUpdate();
		
		return result == 1;
	}

	@Override
	public boolean insert(CompanyEntity entity) throws SQLException {
		String sql = "insert into company(name, location) values (?, ?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, entity.getName());
		stmt.setString(2, entity.getLocation());
		
		var result = stmt.executeUpdate();
		
		return result == 1;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		String sql = "delete from company where id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);
		
		var result = stmt.executeUpdate();
		
		return result == 1;
	}

	@Override
	public boolean delete(CompanyEntity entity) throws SQLException {
		return this.delete(entity.getId());
	}

}

package costtracker.db.repositories;

import costtracker.db.entities.CompanyEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyRepository extends RepositoryBase implements BaseDataRepository<CompanyEntity> {

	public CompanyRepository(Connection connection) {
		super(connection);
	}

	@Override
	public CompanyEntity get(int id) throws SQLException {
		// TODO Auto-generated method stub
		CompanyEntity entity = null;
		String sql = "select * from company where id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);
		
		ResultSet result = stmt.executeQuery();
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
		
		int result = stmt.executeUpdate();
		
		return result == 1;
	}

	@Override
	public boolean insert(CompanyEntity entity) throws SQLException {
		String sql = "insert into company(name, location, isenabled) values (?, ?, true)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, entity.getName());
		stmt.setString(2, entity.getLocation());
		
		int result = stmt.executeUpdate();
		
		return result == 1;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		String sql = "delete from company where id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);
		
		int result = stmt.executeUpdate();
		
		return result == 1;
	}

	@Override
	public List<CompanyEntity> getAll() throws SQLException {
		String sql = "select id, name, location from company";
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		ResultSet result = stmt.executeQuery();
		
		List<CompanyEntity> companies = new ArrayList<CompanyEntity>();
		
		while(result.next()) {
			companies.add(new CompanyEntity(result.getInt("id"), result.getString("name"), result.getString("location")));
		}
		
		return companies;
	}

	@Override
	public boolean enable(int id) throws SQLException {
		String sql = "update company set isenabled = true where id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);
		
		int result = stmt.executeUpdate();
		
		return result == 1;
	}

	@Override
	public boolean disable(int id) throws SQLException {
		String sql = "update company set isenabled = false where id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);
		
		int result = stmt.executeUpdate();
		
		return result == 1;
	}

	@Override
	public List<CompanyEntity> getEnabled() throws SQLException {
		String sql = "select id, name, location from company where isenabled = true";
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		ResultSet result = stmt.executeQuery();
		
		List<CompanyEntity> companies = new ArrayList<CompanyEntity>();
		
		while(result.next()) {
			companies.add(new CompanyEntity(result.getInt("id"), result.getString("name"), result.getString("location")));
		}
		
		return companies;
	}

}

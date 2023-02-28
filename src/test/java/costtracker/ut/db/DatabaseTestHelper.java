package costtracker.ut.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTestHelper {
	
	private Connection connection;
	
	public DatabaseTestHelper(Connection connection) {
		this.connection = connection;
	}
	
	int createCompany(String name, String location) throws SQLException {
		int id = 0;
		String sql = "Insert into company" + "(name, location, isenabled)" + "values (?, ?, true)";
		PreparedStatement stmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, name);
		stmt.setString(2, location);
		stmt.executeUpdate();
		ResultSet generatedKeys = stmt.getGeneratedKeys();
		if(generatedKeys.next()) {
			id = generatedKeys.getInt(1);
		}
		return id;
	}
	
	int createCategory(String name) throws SQLException {
		int id = 0;
		String sql = "Insert into category" + "(name, isenabled)" + "values (?,true)";
		PreparedStatement stmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, name);
		stmt.executeUpdate();
		ResultSet generatedKeys = stmt.getGeneratedKeys();
		if(generatedKeys.next()) {
			id = generatedKeys.getInt(1);
		}
		return id;
	}
	
	int createPurchase(String name, String description, Date date, double price, int categoryid, int companyid) throws SQLException {
		int id = 0;
		String sql = "Insert into purchase" + "(name, description, date, price, category, company)" + "values (?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, name);
		stmt.setString(2, description);
		stmt.setDate(3, date);
		stmt.setDouble(4, price);
		stmt.setLong(5, categoryid);
		stmt.setLong(6, companyid);
		stmt.executeUpdate();
		ResultSet generatedKeys = stmt.getGeneratedKeys();
		if(generatedKeys.next()) {
			id = generatedKeys.getInt(1);
		}
		return id;
	}
	
}

package test.db;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import costtracker.db.entities.CompanyEntity;
import costtracker.db.repositories.CompanyRepository;

class CompanyRepositoryUt {
	
	private static DatabaseTestHelper helper;
	private static Connection connection;

	private int id;
	private String name;
	private String location;

	@BeforeEach
	void setUp() throws Exception {
		connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "test");
		helper = new DatabaseTestHelper(connection);
	}

	@AfterEach
	void tearDown() throws Exception {
		connection.close();
		helper = null;
	}

	@Test
	void testPurchaseInsert() throws SQLException {
		// Arrange
		CompanyRepository repository = new CompanyRepository(connection);
		CompanyEntity entity = new CompanyEntity(name, location);

		// Act
		var ret = repository.insert(entity);

		// Assert
		assertEquals(true, ret);
	}

	@Test
	void testPurchaseUpdate() throws SQLException {
		// Arrange
		id = helper.createCompany(name, location);
		CompanyRepository repository = new CompanyRepository(connection);
		CompanyEntity entity = new CompanyEntity(name, location);
		entity.setLocation("Karlsruhe");

		// Act
		var ret = repository.update(entity);

		// Assert
		assertEquals(true, ret);
	}

	@Test
	void testPurchaseDeleteEntity() throws SQLException {
		//Arrange
		id = helper.createCompany(name, location);
		CompanyRepository repository = new CompanyRepository(connection);
		CompanyEntity entity = new CompanyEntity(name, location);
		entity.setId(id);

		//Act
		var ret = repository.delete(entity);

		//Assert
		assertEquals(true, ret);
	}

	void testPurchaseDeleteId() throws SQLException {
		//Arrange
		id = helper.createCompany(name, location);
		CompanyRepository repository = new CompanyRepository(connection);

		//Act
		var ret = repository.delete(id);

		//Assert
		assertEquals(true, ret);
	}

	@Test
	void testPurchaseSelect() throws SQLException {
		//Arrange
		id = helper.createCompany(name, location);
		CompanyRepository repository = new CompanyRepository(connection);
		
		//Act
		var entity = repository.select(id);
		
		//Assert
		assertEquals(id, entity.getId());
		assertEquals(name, entity.getName());
		assertEquals(location, entity.getLocation());
	}

}

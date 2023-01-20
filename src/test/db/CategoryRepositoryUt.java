package test.db;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import costtracker.db.entities.CategoryEntity;
import costtracker.db.repositories.CategoryRepository;

class CategoryRepositoryUt {

	private static DatabaseTestHelper helper;
	private static Connection connection;

	private int id;
	private String name = "Essen";

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
		CategoryRepository repository = new CategoryRepository(connection);
		CategoryEntity entity = new CategoryEntity(name);

		// Act
		var ret = repository.insert(entity);

		// Assert
		assertEquals(true, ret);
	}

	@Test
	void testPurchaseUpdate() throws SQLException {
		// Arrange
		id = helper.createCategory(name);
		CategoryRepository repository = new CategoryRepository(connection);
		CategoryEntity entity = new CategoryEntity(name);
		entity.setName("Trinken");

		// Act
		var ret = repository.update(entity);

		// Assert
		assertEquals(true, ret);
	}

	@Test
	void testPurchaseDeleteEntity() throws SQLException {
		//Arrange
		id = helper.createCategory(name);
		CategoryRepository repository = new CategoryRepository(connection);
		CategoryEntity entity = new CategoryEntity(name);
		entity.setId(id);

		//Act
		var ret = repository.delete(entity);

		//Assert
		assertEquals(true, ret);
	}

	void testPurchaseDeleteId() throws SQLException {
		//Arrange
		id = helper.createCategory(name);
		CategoryRepository repository = new CategoryRepository(connection);

		//Act
		var ret = repository.delete(id);

		//Assert
		assertEquals(true, ret);
	}

	@Test
	void testPurchaseSelect() throws SQLException {
		//Arrange
		id = helper.createCategory(name);
		CategoryRepository repository = new CategoryRepository(connection);
		
		//Act
		var entity = repository.select(id);
		
		//Assert
		assertEquals(id, entity.getId());
		assertEquals(name, entity.getName());
	}


}

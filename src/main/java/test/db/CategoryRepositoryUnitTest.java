package test.db;

import costtracker.db.entities.CategoryEntity;
import costtracker.db.repositories.CategoryRepository;
import org.junit.jupiter.api.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryRepositoryUnitTest {

	private static DatabaseTestHelper helper;
	private static Connection connection;

	private int id;
	private String name = "Essen";

	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		connection = DriverManager.getConnection("jdbc:h2:~/testdb", "sa", "test");
		Path path = Paths.get("CreateDatabase.sql");
		String sql = Files.readString(path);
		Statement stmt = connection.createStatement();
		stmt.execute(sql);
		connection.close();
	}
	
	@BeforeEach
	public void setUp() throws Exception {
		connection = DriverManager.getConnection("jdbc:h2:~/testdb", "sa", "test");
		helper = new DatabaseTestHelper(connection);
	}

	@AfterEach
	public void tearDown() throws Exception {
		connection.close();
		helper = null;
		id = 0;
	}

	@Test
	public void testCategoryInsert() throws SQLException {
		// Arrange
		CategoryRepository repository = new CategoryRepository(connection);
		CategoryEntity entity = new CategoryEntity(name);

		// Act
		boolean ret = repository.insert(entity);

		// Assert
		assertEquals(true, ret);
	}

	@Test
	public void testCategoryUpdate() throws SQLException {
		// Arrange
		id = helper.createCategory(name);
		CategoryRepository repository = new CategoryRepository(connection);
		CategoryEntity entity = new CategoryEntity(id, name);
		entity.setName("Trinken");

		// Act
		boolean ret = repository.update(entity);

		// Assert
		assertEquals(true, ret);
	}

	@Test
	public void testCategoryDeleteEntity() throws SQLException {
		//Arrange
		id = helper.createCategory(name);
		CategoryRepository repository = new CategoryRepository(connection);
		CategoryEntity entity = new CategoryEntity(id, name);

		//Act
		boolean ret = repository.delete(entity);

		//Assert
		assertEquals(true, ret);
	}

	@Test
	public void testCategoryDeleteId() throws SQLException {
		//Arrange
		id = helper.createCategory(name);
		CategoryRepository repository = new CategoryRepository(connection);

		//Act
		boolean ret = repository.delete(id);

		//Assert
		assertEquals(true, ret);
	}

	@Test
	public void testCategorySelect() throws SQLException {
		//Arrange
		id = helper.createCategory(name);
		CategoryRepository repository = new CategoryRepository(connection);
		
		//Act
		CategoryEntity entity = repository.select(id);
		
		//Assert
		assertEquals(id, entity.getId());
		assertEquals(name, entity.getName());
	}
}

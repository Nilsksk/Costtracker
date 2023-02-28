package costtracker.ut.db;

import costtracker.db.entities.CompanyEntity;
import costtracker.db.repositories.CompanyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CompanyRepositoryUnitTest {
	
	private static DatabaseTestHelper helper;
	private static Connection connection;

	private int id;
	private String name = "Subway";
	private String location = "Landau";

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		connection = DriverManager.getConnection("jdbc:h2:~/testdb", "sa", "test");
		Path path = Paths.get("CreateDatabase.sql");
		String sql = Files.readString(path);
		Statement stmt = connection.createStatement();
		stmt.execute(sql);
		connection.close();
	}
	
	@BeforeEach
	void setUp() throws Exception {
		connection = DriverManager.getConnection("jdbc:h2:~/testdb", "sa", "test");
		helper = new DatabaseTestHelper(connection);
	}

	@AfterEach
	void tearDown() throws Exception {
		connection.close();
		helper = null;
		id = 0;
	}

	@Test
	void testCompanyInsert() throws SQLException {
		// Arrange
		CompanyRepository repository = new CompanyRepository(connection);
		CompanyEntity entity = new CompanyEntity(name, location);

		// Act
		boolean ret = repository.insert(entity);

		// Assert
		assertEquals(true, ret);
	}
	
	@Test
	void testCompanyInsertNoLocation() throws SQLException {
		// Arrange
		CompanyRepository repository = new CompanyRepository(connection);
		CompanyEntity entity = new CompanyEntity(name, location);
		
		// Act
		boolean ret = repository.insert(entity);
		
		// Assert
		assertEquals(true, ret);
	}

	@Test
	void testCompanyUpdate() throws SQLException {
		// Arrange
		id = helper.createCompany(name, location);
		CompanyRepository repository = new CompanyRepository(connection);
		CompanyEntity entity = new CompanyEntity(id, name, location);
		entity.setLocation("Karlsruhe");

		// Act
		boolean ret = repository.update(entity);

		// Assert
		assertEquals(true, ret);
	}
	
	@Test
	void testCompanyUpdateNoLocation() throws SQLException {
		// Arrange
		id = helper.createCompany(name, location);
		CompanyRepository repository = new CompanyRepository(connection);
		CompanyEntity entity = new CompanyEntity(id, name);
		entity.setName("McDonalds");
		
		// Act
		boolean ret = repository.update(entity);
		
		// Assert
		assertEquals(true, ret);
	}

	@Test
	void testCompanyDeleteId() throws SQLException {
		//Arrange
		id = helper.createCompany(name, location);
		CompanyRepository repository = new CompanyRepository(connection);

		//Act
		boolean ret = repository.delete(id);

		//Assert
		assertEquals(true, ret);
	}

	@Test
	void testCompanySelect() throws SQLException {
		//Arrange
		id = helper.createCompany(name, location);
		CompanyRepository repository = new CompanyRepository(connection);
		
		//Act
		CompanyEntity entity = repository.get(id);
		
		//Assert
		assertEquals(id, entity.getId());
		assertEquals(name, entity.getName());
		assertEquals(location, entity.getLocation());
	}
	
	@Test
	void testCompanyGetAll() throws SQLException {
		helper.createCompany(name, location);
		helper.createCompany(name + "2", location);

		CompanyRepository repository = new CompanyRepository(connection);

		List<CompanyEntity> entities = repository.getAll();

		assertTrue(entities.size() >= 2);
	}
	
	void testEnable() throws SQLException{
		id = helper.createCompany(name, location);
		CompanyRepository repository = new CompanyRepository(connection);
		repository.disable(id);
		
		repository.enable(id);
		
		List<CompanyEntity> entities = repository.getEnabled();
		
		assertTrue(entities.stream().anyMatch(c -> c.getId() == id));
		
	}
	
	void testDisable() throws SQLException{
		id = helper.createCategory(name);
		CompanyRepository repository = new CompanyRepository(connection);

		repository.disable(id);
		
		List<CompanyEntity> entities = repository.getEnabled();
		
		assertTrue(entities.stream().noneMatch(c -> c.getId() == id));
		
	}

}

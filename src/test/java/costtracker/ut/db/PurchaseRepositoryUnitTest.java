package costtracker.ut.db;

import costtracker.db.entities.CategoryEntity;
import costtracker.db.entities.CompanyEntity;
import costtracker.db.entities.PurchaseEntity;
import costtracker.db.repositories.PurchaseRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PurchaseRepositoryUnitTest {

	private static Connection connection;

	private int id;
	private String name = "Chicken Terriyaki";
	private String description = "";
	private Date date = Date.valueOf("2022-01-19");
	private double price = 6.98;
	private int companyid;
	private int categoryid;
	private static DatabaseTestHelper helper;

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
		categoryid = helper.createCategory("Essen");
		companyid = helper.createCompany("Subway", "Landau");
	}

	@AfterEach
	void tearDown() throws Exception {
		connection.close();
		helper = null;
		id = 0;
	}

	private CategoryEntity createCategoryEntity() throws SQLException {
		int categoryId = helper.createCategory("Essen");
		CategoryEntity categoryEntity = new CategoryEntity(categoryId, "Essen");
		return categoryEntity;
	}

	private CompanyEntity createCompanyEntity() throws SQLException {
		int companyId = helper.createCompany("Subway", "Landau");
		CompanyEntity companyEntity = new CompanyEntity(companyId, "Subway", "Landau");
		return companyEntity;
	}

	@Test
	void testPurchaseInsert() throws SQLException {
		// Arrange
		PurchaseRepository repository = new PurchaseRepository(connection);
		CategoryEntity categoryEntity = createCategoryEntity();
		CompanyEntity companyEntity = createCompanyEntity();
		PurchaseEntity entity = new PurchaseEntity(companyEntity, categoryEntity, price, name, description, date);

		// Act
		boolean ret = repository.insert(entity);

		// Assert
		assertEquals(true, ret);
	}

	@Test
	void testPurchaseInsertNoCompany() throws SQLException {
		// Arrange
		PurchaseRepository repository = new PurchaseRepository(connection);
		CategoryEntity categoryEntity = createCategoryEntity();
		PurchaseEntity entity = new PurchaseEntity(categoryEntity, price, name, description, date);

		// Act
		boolean ret = repository.insert(entity);

		// Assert
		assertEquals(true, ret);
	}

	@Test
	void testPurchaseInsertNoCompanyNoDescription() throws SQLException {
		// Arrange
		PurchaseRepository repository = new PurchaseRepository(connection);
		CategoryEntity categoryEntity = createCategoryEntity();
		PurchaseEntity entity = new PurchaseEntity(categoryEntity, price, name, date);

		// Act
		boolean ret = repository.insert(entity);

		// Assert
		assertEquals(true, ret);
	}

	@Test
	void testPurchaseInsertNoDescription() throws SQLException {
		// Arrange
		PurchaseRepository repository = new PurchaseRepository(connection);
		CategoryEntity categoryEntity = createCategoryEntity();
		CompanyEntity companyEntity = createCompanyEntity();
		PurchaseEntity entity = new PurchaseEntity(companyEntity, categoryEntity, price, name, date);

		// Act
		boolean ret = repository.insert(entity);

		// Assert
		assertEquals(true, ret);
	}

	@Test
	void testPurchaseUpdate() throws SQLException {
		// Arrange
		id = helper.createPurchase(name, description, date, price, categoryid, companyid);
		PurchaseRepository repository = new PurchaseRepository(connection);
		PurchaseEntity entity = new PurchaseEntity(id, companyid, categoryid, price, name, description, date);
		entity.setName("Chicken Fajita");

		// Act
		boolean ret = repository.update(entity);

		// Assert
		assertEquals(true, ret);
	}

	@Test
	void testPurchaseUpdateNoCompany() throws SQLException {
		// Arrange
		id = helper.createPurchase(name, description, date, price, categoryid, companyid);
		PurchaseRepository repository = new PurchaseRepository(connection);
		PurchaseEntity entity = new PurchaseEntity(categoryid, price, name, description, date);
		entity.setId(id); // TODO
		entity.setName("Chicken Fajita");

		// Act
		boolean ret = repository.update(entity);

		// Assert
		assertEquals(true, ret);
	}

	@Test
	void testPurchaseUpdateNoCompanyNoDescription() throws SQLException {
		// Arrange
		id = helper.createPurchase(name, description, date, price, categoryid, companyid);
		PurchaseRepository repository = new PurchaseRepository(connection);
		PurchaseEntity entity = new PurchaseEntity(id, categoryid, price, name, date);
		entity.setName("Chicken Fajita");

		// Act
		boolean ret = repository.update(entity);

		// Assert
		assertEquals(true, ret);
	}

	@Test
	void testPurchaseUpdateNoDescription() throws SQLException {
		// Arrange
		id = helper.createPurchase(name, description, date, price, categoryid, companyid);
		PurchaseRepository repository = new PurchaseRepository(connection);
		PurchaseEntity entity = new PurchaseEntity(id, companyid, categoryid, price, name, date);
		entity.setName("Chicken Fajita");

		// Act
		boolean ret = repository.update(entity);

		// Assert
		assertEquals(true, ret);
	}

	@Test
	void testPurchaseDeleteId() throws SQLException {
		// Arrange
		id = helper.createPurchase(name, description, date, price, categoryid, companyid);
		PurchaseRepository repository = new PurchaseRepository(connection);

		// Act
		boolean ret = repository.delete(id);

		// Assert
		assertEquals(true, ret);
	}

	@Test
	void testPurchaseSelect() throws SQLException {
		// Arrange
		id = helper.createPurchase(name, description, date, price, categoryid, companyid);
		PurchaseRepository repository = new PurchaseRepository(connection);

		// Act
		PurchaseEntity entity = repository.get(id);

		// Assert
		assertEquals(id, entity.getId());
		assertEquals(name, entity.getName());
		assertEquals(description, entity.getDescription());
		assertEquals(price, entity.getPrice());
		assertEquals(date, entity.getDate());
		assertEquals(categoryid, entity.getCategory().getId());
		assertEquals(companyid, entity.getCompany().getId());
	}

	@Test
	void testPurchaseGetAll() throws SQLException {
		helper.createPurchase(name, description, date, price, categoryid, companyid);
		helper.createPurchase(name + "2", description, date, price, categoryid, companyid);

		PurchaseRepository repository = new PurchaseRepository(connection);

		List<PurchaseEntity> entities = repository.getAll();

		assertTrue(entities.size() >= 2);
	}

	@Test
	void testPurchaseGetAllByTimeFrame() throws SQLException {
		helper.createPurchase(name, description, date, price, categoryid, companyid);
		helper.createPurchase(name + "2", description, date, price, categoryid, companyid);

		PurchaseRepository repository = new PurchaseRepository(connection);

		List<PurchaseEntity> entities = repository.getByTimespan(Date.valueOf(date.toLocalDate().minusDays(1)),
				Date.valueOf(date.toLocalDate().plusDays(1)));

		assertTrue(entities.size() >= 2);
	}

	@Test
	void testPurchaseGetAllByTimeFrameAndCategory() throws SQLException {
		helper.createPurchase(name, description, date, price, categoryid, companyid);
		helper.createPurchase(name + "2", description, date, price, categoryid, companyid);
		
		PurchaseRepository repository = new PurchaseRepository(connection);
		
		List<PurchaseEntity> entities = repository.getByCategoryByTimespan(new CategoryEntity(categoryid),Date.valueOf(date.toLocalDate().minusDays(1)),
				Date.valueOf(date.toLocalDate().plusDays(1)));
		
		assertTrue(entities.size() >= 2);
	}

	@Test
	void testPurchaseGetAllByTimeFrameAndCompany() throws SQLException {
		helper.createPurchase(name, description, date, price, categoryid, companyid);
		helper.createPurchase(name + "2", description, date, price, categoryid, companyid);
		
		PurchaseRepository repository = new PurchaseRepository(connection);
		
		List<PurchaseEntity> entities = repository.getByCompanyByTimespan(new CompanyEntity(companyid),Date.valueOf(date.toLocalDate().minusDays(1)),
				Date.valueOf(date.toLocalDate().plusDays(1)));
		
		assertTrue(entities.size() >= 2);
	}

}

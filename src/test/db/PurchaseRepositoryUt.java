package test.db;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import costtracker.db.entities.CategoryEntity;
import costtracker.db.entities.CompanyEntity;
import costtracker.db.entities.PurchaseEntity;
import costtracker.db.repositories.PurchaseRepository;

class PurchaseRepositoryUt {

	private static Connection connection;

	private int id;
	private String name = "Chicken Terriyaki";
	private String description = "";
	private Date date = Date.valueOf("2022-01-19");
	private double price = 6.98;
	private int companyid;
	private int categoryid;
	private static DatabaseTestHelper helper;

	@BeforeEach
	void setUp() throws Exception {
		connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "test");
		helper = new DatabaseTestHelper(connection);
		categoryid = helper.createCategory("Essen");
		companyid = helper.createCompany("Subway", "Landau");
	}

	@AfterEach
	void tearDown() throws Exception {
		connection.close();
		helper = null;
	}

	private CategoryEntity createCategoryEntity() throws SQLException {
		int categoryId = helper.createCategory("Essen");
		CategoryEntity categoryEntity = new CategoryEntity("Essen");
		categoryEntity.setId(categoryId);
		return categoryEntity;
	}

	private CompanyEntity createCompanyEntity() throws SQLException {
		int companyId = helper.createCompany("Subway", "Landau");
		CompanyEntity companyEntity = new CompanyEntity("Subway", "Landau");
		companyEntity.setId(companyId);
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
		var ret = repository.insert(entity);

		// Assert
		assertEquals(true, ret);
	}

	@Test
	void testPurchaseUpdate() throws SQLException {
		// Arrange
		id = helper.createPurchase(name, description, date, price, categoryid, companyid);
		PurchaseRepository repository = new PurchaseRepository(connection);
		PurchaseEntity entity = new PurchaseEntity(companyid, categoryid, price, name, description, date);
		entity.setName("Chicken Fajita");

		// Act
		var ret = repository.update(entity);

		// Assert
		assertEquals(true, ret);
	}

	@Test
	void testPurchaseDeleteEntity() throws SQLException {
		//Arrange
		id = helper.createPurchase(name, description, date, price, categoryid, companyid);
		PurchaseRepository repository = new PurchaseRepository(connection);
		PurchaseEntity entity = new PurchaseEntity(companyid, categoryid, price, name, description, date);

		//Act
		var ret = repository.delete(entity);

		//Assert
		assertEquals(true, ret);
	}

	void testPurchaseDeleteId() throws SQLException {
		//Arrange
		id = helper.createPurchase(name, description, date, price, categoryid, companyid);
		PurchaseRepository repository = new PurchaseRepository(connection);

		//Act
		var ret = repository.delete(id);

		//Assert
		assertEquals(true, ret);
	}

	@Test
	void testPurchaseSelect() throws SQLException {
		//Arrange
		id = helper.createPurchase(name, description, date, price, categoryid, companyid);
		PurchaseRepository repository = new PurchaseRepository(connection);
		
		//Act
		var entity = repository.select(id);
		
		//Assert
		assertEquals(id, entity.getId());
		assertEquals(name, entity.getName());
		assertEquals(description, entity.getDescription());
		assertEquals(price, entity.getPrice());
		assertEquals(date, entity.getDate());
		assertEquals(categoryid, entity.getCategory().getId());
		assertEquals(companyid, entity.getCompany().getId());
	}

}

package costtracker.ut.businessobjects;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;
import costtracker.businessobjects.Purchase;
import costtracker.db.entities.CategoryEntity;
import costtracker.db.entities.CompanyEntity;
import costtracker.db.entities.PurchaseEntity;

class PurchaseUnitTest {

	@Test
	void testFromEntity() {
		int id = 1;
		String name = "name";
		String description = "description";
		double price = 4.0;
		int year = 2023;
		int month = 2;
		int day = 27;
		String dateString = year + "-" + month + "-" + day;
		Date date = Date.valueOf(dateString);
		int compId = 1;
		String compName = "comp";
		String compLocation = "location";
		CompanyEntity company = new CompanyEntity(compId, compName, compLocation);
		int catId = 1;
		String catName = "cat";
		CategoryEntity category = new CategoryEntity(catId, catName);
		PurchaseEntity entity = new PurchaseEntity(id, company, category, price, name, description, date);
		Purchase purchase = Purchase.fromEntity(entity);

		assertEquals(id, purchase.getId());
		assertEquals(name, purchase.getName());
		assertEquals(price, purchase.getPrice());
		assertEquals(description, purchase.getDescription());
		LocalDate locDate = LocalDate.of(year, month, day);
		assertEquals(locDate.getDayOfMonth(), purchase.getDate().getDayOfMonth());
		assertEquals(locDate.getMonth(), purchase.getDate().getMonth());
		assertEquals(locDate.getYear(), purchase.getDate().getYear());
		assertEquals(compId, purchase.getCompany().getId());
		assertEquals(compName, purchase.getCompany().getName());
		assertEquals(compLocation, purchase.getCompany().getLocation());
		assertEquals(catId, purchase.getCategory().getId());
		assertEquals(catName, purchase.getCategory().getName());
	}

	@Test
	void testFromEntityNoCompany() {

		PurchaseEntity entity = new PurchaseEntity(1, null, new CategoryEntity(1), 0.0, "", "",
				Date.valueOf("2023-02-27"));
		Purchase purchase = Purchase.fromEntity(entity);

		assertEquals(null, purchase.getCompany());
	}

	@Test
	void testToEntity() {
		int id = 1;
		String name = "name";
		String description = "description";
		double price = 4.0;
		int year = 2023;
		int month = 2;
		int day = 27;
		LocalDate date = LocalDate.of(year, month, day);
		int compId = 1;
		String compName = "comp";
		String compLocation = "location";
		Company company = new Company(compId, compName, compLocation);
		int catId = 1;
		String catName = "cat";
		Category category = new Category(catId, catName);
		Purchase purchase = new Purchase(id, name, description, date, price, company, category);
		PurchaseEntity entity = purchase.toEntity();

		assertEquals(id, entity.getId());
		assertEquals(name, entity.getName());
		assertEquals(price, entity.getPrice());
		assertEquals(description, entity.getDescription());
		String dateString = year + "-" + month + "-" + day;
		assertEquals(0, entity.getDate().compareTo(Date.valueOf(dateString)));
		assertEquals(compId, entity.getCompany().getId());
		assertEquals(compName, entity.getCompany().getName());
		assertEquals(compLocation, entity.getCompany().getLocation());
		assertEquals(catId, entity.getCategory().getId());
		assertEquals(catName, entity.getCategory().getName());
	}

	@Test
	void testToEntityNoCompany() {
		Purchase purchase = new Purchase(1, "na", "na", LocalDate.now(), 0.0, null, new Category(1, "na"));
		PurchaseEntity entity = purchase.toEntity();

		assertEquals(null, entity.getCompany());
	}

}

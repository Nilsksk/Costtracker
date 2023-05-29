package costtracker.ut.document.elements;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;
import costtracker.businessobjects.IncorrectEntryException;
import costtracker.businessobjects.Purchase;
import costtracker.document.elements.HistoryElement;
import costtracker.document.elements.HistoryElementsCreator;
import costtracker.document.type.ElementType;

class HistoryElementUnitTest {

	
	@Test
	void TestGetHeaderDate() throws IncorrectEntryException {
		LocalDate date = LocalDate.of(2023,1, 21);
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues("purchase", date, 1.0)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.build();
		List<Purchase> purchases = new ArrayList<>();
		purchases.add(purchase);
		HistoryElement element = new HistoryElementsCreator(purchases, ElementType.Date).createHistoryElements().get(0);
	
		var ret = element.getHeader();
		
		assertEquals(date.toString(), ret);
	}
	
	@Test
	void TestGetTypeDate() throws IncorrectEntryException {
		LocalDate date = LocalDate.of(2023,1, 21);
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues("purchase", date, 1.0)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.build();
		List<Purchase> purchases = new ArrayList<>();
		purchases.add(purchase);
		HistoryElement element = new HistoryElementsCreator(purchases, ElementType.Date).createHistoryElements().get(0);
		
		var ret = element.getType();
		
		assertEquals("Date", ret);
	}
	
	@Test
	void TestGetHeaderCategory() throws IncorrectEntryException {
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), 1.0)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.build();
		List<Purchase> purchases = new ArrayList<>();
		purchases.add(purchase);
		HistoryElement element = new HistoryElementsCreator(purchases, ElementType.Category).createHistoryElements().get(0);
		
		var ret = element.getHeader();
		
		assertEquals(category.getName().toString(), ret);
	}

	@Test
	void TestGetTypeCategory() throws IncorrectEntryException {
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), 1.0)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.build();
		List<Purchase> purchases = new ArrayList<>();
		purchases.add(purchase);
		HistoryElement element = new HistoryElementsCreator(purchases, ElementType.Category).createHistoryElements().get(0);
		
		var ret = element.getType();
		
		assertEquals("Category", ret);
	}

	@Test
	void TestGetHeaderCompany() throws IncorrectEntryException {
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		Company company = Company.CompanyBuilder
				.withName("comp")
				.withId(1)
				.build();
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), 1.0)
				.withId(1)
				.withCategory(category)
				.withCompany(company)
				.withDescription("description")
				.build();
		List<Purchase> purchases = new ArrayList<>();
		purchases.add(purchase);
		HistoryElement element = new HistoryElementsCreator(purchases, ElementType.Company).createHistoryElements().get(0);
		
		var ret = element.getHeader();
		
		assertEquals(company.getName().toString(), ret);
	}

	@Test
	void TestGetTypeCompany() throws IncorrectEntryException {
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		Company company = Company.CompanyBuilder
				.withName("comp")
				.withId(1)
				.build();
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), 1.0)
				.withId(1)
				.withCategory(category)
				.withCompany(company)
				.withDescription("description")
				.build();
		List<Purchase> purchases = new ArrayList<>();
		purchases.add(purchase);
		HistoryElement element = new HistoryElementsCreator(purchases, ElementType.Company).createHistoryElements().get(0);
		
		var ret = element.getType();
		
		assertEquals("Company", ret);
	}
	
	@Test
	void TestGetTotal() throws IncorrectEntryException {
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		int day = 21;
		int month = 1;
		int year = 2023;
		LocalDate date = LocalDate.of(year, month, day);
		double price1 = 4.5;
		double price2 = 5.5;
		Purchase purchase1 = Purchase.PurchaseBuilder
				.withValues("purchase", date, price1)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.build();
		Purchase purchase2 =Purchase.PurchaseBuilder
				.withValues("purchase", date, price2)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.build();
		List<Purchase> purchases = new ArrayList<>();
		purchases.add(purchase1);
		purchases.add(purchase2);
		HistoryElement element = new HistoryElementsCreator(purchases, ElementType.Date).createHistoryElements().get(0);

		var ret = element.getTotal();
		
		assertEquals(price1 + price2, ret);
	}

}

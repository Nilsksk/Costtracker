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
import costtracker.document.elements.CompanyHistoryElement;

class CompanyHistoryElementUnitTest {

	@Test
	void TestAddPurchase() throws IncorrectEntryException {
		Company company = Company.CompanyBuilder
				.withName("comp")
				.withId(1)
				.build();
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		CompanyHistoryElement element = new CompanyHistoryElement(company);
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), 1.0)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.withCompany(company)
				.build(); 
		
		element.addPurchase(purchase);
		
		var ret = element.getPurchases();
		
		assertEquals(1, ret.size());
	}

	@Test
	void TestAddPurchases() throws IncorrectEntryException {
		Company company = Company.CompanyBuilder
				.withName("comp")
				.withId(1)
				.build();
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		CompanyHistoryElement element = new CompanyHistoryElement(company);
		Purchase purchase1 = Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), 1.0)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.withCompany(company)
				.build();
		Purchase purchase2 = Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), 1.0)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.withCompany(company)
				.build();
		
		List<Purchase> purchases = new ArrayList<Purchase>();
		purchases.add(purchase1);
		purchases.add(purchase2);
		
		element.addPurchases(purchases);
		
		var ret = element.getPurchases();
		
		assertEquals(2, ret.size());
	}
	
	@Test
	void TestGetHeader() throws IncorrectEntryException {
		String header = "head";
		Company company = Company.CompanyBuilder
				.withName(header)
				.withId(1)
				.build();
		CompanyHistoryElement element = new CompanyHistoryElement(company);
	
		var ret = element.getHeader();
		
		assertEquals(header, ret);
	}
	
	@Test
	void TestGetTotal() throws IncorrectEntryException {
		Company company = Company.CompanyBuilder
				.withName("comp")
				.withId(1)
				.build();
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		CompanyHistoryElement element = new CompanyHistoryElement(company);
		double price1 = 4.5;
		double price2 = 5.5;
		Purchase purchase1 =Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), price1)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.withCompany(company)
				.build();
		Purchase purchase2 = Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), price2)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.withCompany(company)
				.build();
		element.addPurchase(purchase1);
		element.addPurchase(purchase2);

		var ret = element.getTotal();
		
		assertEquals(price1 + price2, ret);
	}

}

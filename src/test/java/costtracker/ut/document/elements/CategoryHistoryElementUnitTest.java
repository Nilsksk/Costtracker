package costtracker.ut.document.elements;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import costtracker.businessobjects.Category;
import costtracker.businessobjects.IncorrectEntryException;
import costtracker.businessobjects.Purchase;
import costtracker.document.elements.CategoryHistoryElement;

class CategoryHistoryElementUnitTest {

	@Test
	void TestAddPurchase() throws IncorrectEntryException {
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		CategoryHistoryElement element = new CategoryHistoryElement(category);
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), 1.0)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.build();
		
		element.addPurchase(purchase);
		
		var ret = element.getPurchases();
		
		assertEquals(1, ret.size());
	}

	@Test
	void TestAddPurchases() throws IncorrectEntryException {
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		CategoryHistoryElement element = new CategoryHistoryElement(category);
		Purchase purchase1 = Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), 1.0)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.build();
		Purchase purchase2 =Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), 1.0)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
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
		Category category = Category.CategoryBuilder
				.withName(header)
				.withId(1)
				.build();
		CategoryHistoryElement element = new CategoryHistoryElement(category);
	
		var ret = element.getHeader();
		
		assertEquals(header, ret);
	}
	
	@Test
	void TestGetTotal() throws IncorrectEntryException {
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		CategoryHistoryElement element = new CategoryHistoryElement(category);
		double price1 = 4.5;
		double price2 = 5.5;
		Purchase purchase1 = Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), price1)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.build();
		Purchase purchase2 =Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), price2)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.build(); 
		element.addPurchase(purchase1);
		element.addPurchase(purchase2);

		var ret = element.getTotal();
		
		assertEquals(price1 + price2, ret);
	}

}

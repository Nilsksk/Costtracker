package test.document.elements;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import costtracker.businessobjects.Category;
import costtracker.businessobjects.Purchase;
import costtracker.document.elements.CategoryHistoryElement;

class CategoryHistoryElementUt {

	@Test
	void TestAddPurchase() {
		Category category = new Category(1, "");
		CategoryHistoryElement element = new CategoryHistoryElement(category);
		Purchase purchase = new Purchase(0, "purchase", "description", LocalDate.of(2023,1, 21), 0, null, category);
		
		element.addPurchase(purchase);
		
		var ret = element.getPurchases();
		
		assertEquals(1, ret.size());
	}

	@Test
	void TestAddPurchases() {
		Category category = new Category(1, "");
		CategoryHistoryElement element = new CategoryHistoryElement(category);
		Purchase purchase1 = new Purchase(0, "purchase", "description", LocalDate.of(2023,1, 21), 0, null, category);
		Purchase purchase2 = new Purchase(0, "purchase", "description", LocalDate.of(2023,1, 21), 0, null, category);
		
		List<Purchase> purchases = new ArrayList<Purchase>();
		purchases.add(purchase1);
		purchases.add(purchase2);
		
		element.addPurchases(purchases);
		
		var ret = element.getPurchases();
		
		assertEquals(2, ret.size());
	}
	
	@Test
	void TestGetHeader() {
		String header = "head";
		Category category = new Category(1, header);
		CategoryHistoryElement element = new CategoryHistoryElement(category);
	
		var ret = element.getHeader();
		
		assertEquals(header, ret);
	}
	
	@Test
	void TestGetTotal() {
		Category category = new Category(1, "");
		CategoryHistoryElement element = new CategoryHistoryElement(category);
		double price1 = 4.5;
		double price2 = 5.5;
		Purchase purchase1 = new Purchase(0, "purchase", "description", LocalDate.of(2023,1, 21), price1, null, category);
		Purchase purchase2 = new Purchase(0, "purchase", "description", LocalDate.of(2023,1, 21), price2, null, category);
		element.addPurchase(purchase1);
		element.addPurchase(purchase2);

		var ret = element.getTotal();
		
		assertEquals(price1 + price2, ret);
	}

}

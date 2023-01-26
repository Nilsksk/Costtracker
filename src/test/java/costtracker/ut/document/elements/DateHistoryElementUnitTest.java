package costtracker.ut.document.elements;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import costtracker.businessobjects.Purchase;
import costtracker.document.elements.DateHistoryElement;

class DateHistoryElementUnitTest {

	@Test
	void TestAddPurchase() {
		int day = 21;
		int month = 1;
		int year = 2023;
		LocalDate date = LocalDate.of(year, month, day);
		DateHistoryElement element = new DateHistoryElement(date);
		Purchase purchase = new Purchase(0, "purchase", "description", date, 0, null, null);
		
		element.addPurchase(purchase);
		
		var ret = element.getPurchases();
		
		assertEquals(1, ret.size());
	}

	@Test
	void TestAddPurchases() {
		int day = 21;
		int month = 1;
		int year = 2023;
		LocalDate date = LocalDate.of(year, month, day);
		DateHistoryElement element = new DateHistoryElement(date);
		Purchase purchase1 = new Purchase(0, "purchase", "description", date, 0, null, null);
		Purchase purchase2 = new Purchase(0, "purchase", "description", date, 0, null, null);
		
		List<Purchase> purchases = new ArrayList<Purchase>();
		purchases.add(purchase1);
		purchases.add(purchase2);
		
		element.addPurchases(purchases);
		
		var ret = element.getPurchases();
		
		assertEquals(2, ret.size());
	}
	
	@Test
	void TestGetHeader() {
		int day = 21;
		int month = 1;
		int year = 2023;
		String header = day + "/" + month + "/" + year;
		LocalDate date = LocalDate.of(year, month, day);
		DateHistoryElement element = new DateHistoryElement(date);
	
		var ret = element.getHeader();
		
		assertEquals(header, ret);
	}
	
	@Test
	void TestGetTotal() {
		int day = 21;
		int month = 1;
		int year = 2023;
		LocalDate date = LocalDate.of(year, month, day);
		DateHistoryElement element = new DateHistoryElement(date);
		double price1 = 4.5;
		double price2 = 5.5;
		Purchase purchase1 = new Purchase(0, "purchase", "description", date, price1, null, null);
		Purchase purchase2 = new Purchase(0, "purchase", "description", date, price2, null, null);
		element.addPurchase(purchase1);
		element.addPurchase(purchase2);

		var ret = element.getTotal();
		
		assertEquals(price1 + price2, ret);
	}

}

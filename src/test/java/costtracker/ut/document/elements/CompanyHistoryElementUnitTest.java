package costtracker.ut.document.elements;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import costtracker.businessobjects.Company;
import costtracker.businessobjects.Purchase;
import costtracker.document.elements.CompanyHistoryElement;

class CompanyHistoryElementUnitTest {

	@Test
	void TestAddPurchase() {
		Company company = new Company(1, "","");
		CompanyHistoryElement element = new CompanyHistoryElement(company);
		Purchase purchase = new Purchase(0, "purchase", "description", LocalDate.of(2023,1, 21), 0, company, null);
		
		element.addPurchase(purchase);
		
		var ret = element.getPurchases();
		
		assertEquals(1, ret.size());
	}

	@Test
	void TestAddPurchases() {
		Company company = new Company(1, "","");
		CompanyHistoryElement element = new CompanyHistoryElement(company);
		Purchase purchase1 = new Purchase(0, "purchase", "description", LocalDate.of(2023,1, 21), 0, company, null);
		Purchase purchase2 = new Purchase(0, "purchase", "description", LocalDate.of(2023,1, 21), 0, company, null);
		
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
		Company company = new Company(1, header,"");
		CompanyHistoryElement element = new CompanyHistoryElement(company);
	
		var ret = element.getHeader();
		
		assertEquals(header, ret);
	}
	
	@Test
	void TestGetTotal() {
		Company company = new Company(1, "","");
		CompanyHistoryElement element = new CompanyHistoryElement(company);
		double price1 = 4.5;
		double price2 = 5.5;
		Purchase purchase1 = new Purchase(0, "purchase", "description", LocalDate.of(2023,1, 21), price1, company, null);
		Purchase purchase2 = new Purchase(0, "purchase", "description", LocalDate.of(2023,1, 21), price2, company, null);
		element.addPurchase(purchase1);
		element.addPurchase(purchase2);

		var ret = element.getTotal();
		
		assertEquals(price1 + price2, ret);
	}

}

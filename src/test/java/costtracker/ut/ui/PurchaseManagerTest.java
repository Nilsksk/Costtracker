package costtracker.ut.ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;
import costtracker.businessobjects.Purchase;
import costtracker.ui.PurchaseManager;

public class PurchaseManagerTest {

	PurchaseManager pm = new PurchaseManager();
	
//	@Test
	void test() {
		
		List<Company> companies = new ArrayList<Company>();
		companies.add(new Company(1, "Netto", "Offenbach"));
		companies.add(new Company(2, "Penny", "Offenbach"));
		companies.add(new Company(3, "Globus", "Neustadt"));
		companies.add(new Company(4, "Dogan", "Offenbach"));
		
		List<Category> categories = new ArrayList<Category>();
		categories.add(new Category(1, "Essen"));
		categories.add(new Category(2, "Alkohol"));
		categories.add(new Category(3, "Lego"));
		categories.add(new Category(4, "Lautre"));
		
		pm.setCompanies(companies);
		pm.setCategories(categories);
		
		pm.add();
	}
	
	@Test
	void testEdit() {
		List<Company> companies = new ArrayList<Company>();
		Company com1 = new Company(1, "Netto", "Offenbach");
		Company com2 = new Company(2, "Penny", "Offenbach");
		companies.add(com1);
		companies.add(com2);
		companies.add(new Company(3, "Globus", "Neustadt"));
		companies.add(new Company(4, "Dogan", "Offenbach"));
		
		List<Category> categories = new ArrayList<Category>();
		Category cat1 = new Category(1, "Essen");
		Category cat2 = new Category(2, "Alkohol");
		categories.add(cat1);
		categories.add(cat2);
		categories.add(new Category(3, "Lego"));
		categories.add(new Category(4, "Lautre"));
		
		List<Purchase> purchases = new ArrayList<Purchase>();
        purchases.add(new Purchase(1, "Nutella","750g", LocalDate.of(2022, 2, 1), 4.99, com2, cat1)); 
        purchases.add(new Purchase(2, "Bellheimer","Kasten", LocalDate.of(2022, 2, 1), 14.99, null, cat2)); 
        purchases.add(new Purchase(3, "Oreo Schokolade",null, LocalDate.of(2022, 2, 1), 14.99, null, cat1)); 
		
		pm.setCompanies(companies);
		pm.setCategories(categories);
		pm.setPurchases(purchases);
		pm.edit();
		
	}
}

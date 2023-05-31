package costtracker.ut.ui;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import costtracker.domain.businessobjects.Category;
import costtracker.domain.businessobjects.Company;
import costtracker.domain.businessobjects.IncorrectEntryException;
import costtracker.domain.businessobjects.Purchase;
import costtracker.plugin.ui.PurchaseManager;

public class PurchaseManagerTest {

	PurchaseManager pm = new PurchaseManager();
	
//	@Test
	void test() throws SQLException, IncorrectEntryException {
		
		List<Company> companies = new ArrayList<Company>();
		companies.add(Company.CompanyBuilder.withName("Netto").withLocation("Offenbach").withId(1).build());
		companies.add(Company.CompanyBuilder.withName("Dogan").withLocation("Offenbach").withId(2).build());
		companies.add(Company.CompanyBuilder.withName("Lidl").withLocation("Landau").withId(3).build());
		companies.add(Company.CompanyBuilder.withName("Globus").withLocation("Neustadt").withId(4).build());
		
		List<Category> categories = new ArrayList<Category>();
		categories.add(Category.CategoryBuilder.withName("Essen").withId(1).build());
		categories.add(Category.CategoryBuilder.withName("Alkohol").withId(2).build());
		categories.add(Category.CategoryBuilder.withName("Lego").withId(3).build());
		categories.add(Category.CategoryBuilder.withName("Lautre").withId(4).build());
		
		//pm.setCompanies(companies);
		//pm.setCategories(categories);
		
		pm.add();
	}
	
	@Test
	void testEdit() throws IncorrectEntryException {
		List<Company> companies = new ArrayList<Company>();
		Company com1 = Company.CompanyBuilder.withName("Netto").withLocation("Offenbach").withId(1).build();
		Company com2 = Company.CompanyBuilder.withName("Penny").withLocation("Offenbach").withId(2).build();
		companies.add(com1);
		companies.add(com2);
		companies.add(Company.CompanyBuilder.withName("Globus").withLocation("Neustadt").withId(3).build());
		companies.add(Company.CompanyBuilder.withName("Dogan").withLocation("Offenbach").withId(4).build());
		
		List<Category> categories = new ArrayList<Category>();
		Category cat1 = Category.CategoryBuilder.withName("Essen").withId(1).build();
		Category cat2 = Category.CategoryBuilder.withName("Alkohol").withId(2).build();
		categories.add(cat1);
		categories.add(cat2);
		categories.add(Category.CategoryBuilder.withName("Lego").withId(3).build());
		categories.add(Category.CategoryBuilder.withName("Lautre").withId(4).build());
		
		List<Purchase> purchases = new ArrayList<Purchase>();
		purchases.add(Purchase.PurchaseBuilder.withValues("Nutella", LocalDate.of(2022, 2, 1), 4.99).withDescription("750g").withCompany(com2).withCategory(cat2).withId(1).build());
		purchases.add(Purchase.PurchaseBuilder.withValues("Bellheimer", LocalDate.of(2022, 2, 1), 14.99).withDescription("Kastern").withCategory(cat2).withId(2).build());
		purchases.add(Purchase.PurchaseBuilder.withValues("Oreo Schokolade", LocalDate.of(2022, 2, 1), 14.99).withCategory(cat1).withId(3).build());
		
		//pm.setCompanies(companies);
		//pm.setCategories(categories);
		//pm.setPurchases(purchases);
		//pm.edit();
		
	}
}

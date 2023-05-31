package costtracker.application.converter;

import java.time.LocalDate;
import java.util.HashMap;

import costtracker.domain.businessobjects.Category;
import costtracker.domain.businessobjects.Company;
import costtracker.domain.businessobjects.IncorrectEntryException;
import costtracker.domain.businessobjects.Purchase;

public class PurchaseConverter {
	private String name;
	private String description;
	private double price;
	private Category category;
	private Company company;
	private LocalDate date;

	public Purchase convertFrom(HashMap<String, String> entry) {
		name = entry.get("name");
		description = entry.get("description");
		price = Double.valueOf(entry.get("price"));
		category = getCategoryFrom(entry);
		company = getCompanyFrom(entry);
		date = LocalDate.parse(entry.get("date"));
		return buildPurchase();
	}

	private Purchase buildPurchase() {
		try {
			return Purchase.PurchaseBuilder.withValues(name, date, price).withCategory(category).withCompany(company)
					.withDescription(description).withId(0).build();
		} catch (IncorrectEntryException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	private Company getCompanyFrom(HashMap<String, String> entry) {
		if (entry.get("company") != "") {
			try {
				return Company.CompanyBuilder.withName("dummy").withId(Integer.valueOf(entry.get("company"))).build();
			} catch (NumberFormatException | IncorrectEntryException e) {
				System.err.println("Wrong format for company: id = " + entry.get("company"));
			}
		}
		return null;
	}

	private Category getCategoryFrom(HashMap<String, String> entry) {
		try {
			return Category.CategoryBuilder.withName("dummy").withId(Integer.valueOf(entry.get("category"))).build();
		} catch (NumberFormatException | IncorrectEntryException e) {
			System.err.println("Wrong format for category: id = " + entry.get("category"));
		}
		return null;
	}
}

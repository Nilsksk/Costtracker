package costtracker.document.reader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;
import costtracker.businessobjects.IncorrectEntryException;
import costtracker.businessobjects.Purchase;

public class PurchaseImportFile {

	private ImportFile importFile;

	public PurchaseImportFile(ImportFile importFile) {
		this.importFile = importFile;

	}

	public List<Purchase> getPurchases() {
		List<Purchase> purchases = new ArrayList<>();
		while (importFile.hasNextEntry()) {
			HashMap<String, String> entry = importFile.getNextEntry();
			String name = entry.get("name");
			String description = entry.get("description");
			double price = Double.valueOf(entry.get("price"));
			Category category;
			try {
				category = Category.CategoryBuilder.withName("dummy").withId(Integer.valueOf(entry.get("category")))
						.build();
			} catch (NumberFormatException | IncorrectEntryException e) {
				System.err.println("Wrong format for category: id = " + entry.get("category"));
				continue;
			}
			Company company = null;
			if (entry.get("company") != "") {
				try {
					company = Company.CompanyBuilder.withName("dummy").withId(Integer.valueOf(entry.get("company")))
							.build();
				} catch (NumberFormatException | IncorrectEntryException e) {
					System.err.println("Wrong format for company: id = " + entry.get("company"));
				}
			}
			LocalDate date = LocalDate.parse(entry.get("date"));
			Purchase purchase;
			try {
				purchase = Purchase.PurchaseBuilder.withValues(name, date, price).withCategory(category).withCompany(company).withDescription(description).withId(0).build();
			} catch (IncorrectEntryException e) {
				System.err.println(e.getMessage());
				continue;
			}
			purchases.add(purchase);
		}
		return purchases;
	}
}

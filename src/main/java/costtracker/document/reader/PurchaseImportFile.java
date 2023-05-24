package costtracker.document.reader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;
import costtracker.businessobjects.Purchase;

public class PurchaseImportFile {

	private CSVImportFile importFile;

	public PurchaseImportFile(CSVImportFile importFile) {
		this.importFile = importFile;

	}

	public List<Purchase> getPurchases() {
		List<Purchase> purchases = new ArrayList<>();
		while (importFile.hasNextEntry()) {
			HashMap<String, String> entry = importFile.getNextEntry();
			String name = entry.get("name");
			String description = entry.get("description");
			double price = Double.valueOf(entry.get("price"));
			Category category = new Category(Integer.valueOf(entry.get("category")), "");
			Company company = new Company(Integer.valueOf(entry.get("company")), "", "");
			LocalDate date = LocalDate.parse(entry.get("date"));
			Purchase purchase = new Purchase(0, name, description, date, price, company, category);
			purchases.add(purchase);
		}
		return purchases;
	}
}

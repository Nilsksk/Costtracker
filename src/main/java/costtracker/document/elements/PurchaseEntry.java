package costtracker.document.elements;

import costtracker.businessobjects.Purchase;
import costtracker.document.printer.DocumentPrinter;

/**
 * This class is an internal class. Don't use, it might not work!
 * @author Florian Felix
 *
 */
public class PurchaseEntry {

	private String name;
	private String description;
	private String price;
	private String date;
	private String company;
	private String category;

	public PurchaseEntry(Purchase purchase) {
		super();
		this.name = purchase.getName();
		this.description = purchase.getDescription();
		this.price = String.valueOf(purchase.getPrice());
		this.date = purchase.getDateString();
		this.company = purchase.getCompany() != null ? purchase.getCompany().getName() : "";
		this.category = purchase.getCategory().getName();
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getPrice() {
		return price;
	}

	public String getDate() {
		return date;
	}

	public String getCompany() {
		return company;
	}

	public String getCategory() {
		return category;
	}

	public StringBuilder printWith(DocumentPrinter printer) {
		StringBuilder sb = printer.printEntry(this);
		return sb;
	}

}

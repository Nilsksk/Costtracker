package costtracker.document.elements;

import java.util.ArrayList;
import java.util.List;

import costtracker.businessobjects.Purchase;
import costtracker.document.printer.DocumentPrinter;
import costtracker.document.type.ElementType;

public class HistoryElementImp implements HistoryElement {

	private double total;	
	private ArrayList<PurchaseEntry> purchaseEntries;	
	private String header;
	private ElementType type;

	public HistoryElementImp(String header, List<Purchase> purchases,ElementType type) {
		this.header = header;
		this.type = type;
		purchaseEntries = new ArrayList<PurchaseEntry>();
		for (Purchase purchase : purchases) {
			addPurchase(purchase);
		}
	}
	
	private void addPurchase(Purchase purchase) {
		total += purchase.getPrice();
		PurchaseEntry purchaseEntry = new PurchaseEntry(purchase);
		purchaseEntries.add(purchaseEntry);
	}
	
	@Override
	public double getTotal() {
		return total;
	}
	
	@Override
	public String printWith(DocumentPrinter printer) {
		StringBuilder sb = printer.printElement(this);
		for (PurchaseEntry purchaseEntry : purchaseEntries) {
			sb.append(purchaseEntry.printWith(printer));
		}
		return sb.toString();
	}
	
	@Override
	public String getHeader() {
		return this.header;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return this.type.name();
	}
}

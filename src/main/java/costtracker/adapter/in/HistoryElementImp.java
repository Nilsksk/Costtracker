package costtracker.adapter.in;

import java.util.ArrayList;
import java.util.List;

import costtracker.application.in.ElementType;
import costtracker.domain.businessobjects.Purchase;

/**
 * This class is an internal class. Don't use, it might not work!
 * 
 * @author Florian Felix
 *
 */
public class HistoryElementImp implements HistoryElement {

	private double total;
	private ArrayList<PurchaseEntry> purchaseEntries;
	private String header;
	private ElementType type;

	public HistoryElementImp(String header, List<Purchase> purchases, ElementType type) {
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
		StringBuilder sb = new StringBuilder();
		sb.append(printer.printElement(this));
		sb.append(printer.printPurchaseEntryStart());
		for (PurchaseEntry purchaseEntry : purchaseEntries) {
			sb.append(purchaseEntry.printWith(printer));
			if (!isLastPurchaseEntry(purchaseEntry))
				sb.append(printer.printPurchaseEntrySeperator());
		}
		sb.append(printer.printPurchaseEntryEnd());
		return sb.toString();
	}

	private boolean isLastPurchaseEntry(PurchaseEntry purchaseEntry) {
		int lastIndex = purchaseEntries.size() - 1;
		PurchaseEntry lastEntry = purchaseEntries.get(lastIndex);
		return purchaseEntry.equals(lastEntry);
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

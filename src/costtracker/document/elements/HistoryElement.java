package costtracker.document.elements;

import java.util.ArrayList;
import java.util.List;

import costtracker.businessobjects.Purchase;

public interface HistoryElement {
	ArrayList<Purchase> getPurchases();
	void addPurchase(Purchase purchase);
	void addPurchases(List<Purchase> purchases);
	double getTotal();	
	String getHeader();
}

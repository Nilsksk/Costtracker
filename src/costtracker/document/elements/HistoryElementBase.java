package costtracker.document.elements;

import java.util.ArrayList;
import java.util.List;

import costtracker.businessobjects.Purchase;

public abstract class HistoryElementBase implements HistoryElement {

	protected ArrayList<Purchase> purchases;
	
	@Override
	public void addPurchase(Purchase purchase) {
		this.purchases.add(purchase);
	}

	@Override
	public void addPurchases(List<Purchase> purchases) {
		this.purchases.addAll(purchases);
	}

	@Override
	public ArrayList<Purchase> getPurchases() {
		return this.purchases;
	}

	@Override
	public double getTotal() {
		return this.purchases.stream().mapToDouble(Purchase::getPrice).sum();
	}
}

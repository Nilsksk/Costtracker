package costtracker.plugin.ui;

import costtracker.domain.businessobjects.Purchase;

public class PurchaseModel {

	private Purchase purchase;
	private int position;
	
	public PurchaseModel(int position, Purchase purchase) {
		this.position = position;
		this.purchase = purchase;
	}
	
	public Purchase getPurchase() {
		return purchase;
	}
	
	public int getPostion() {
		return position;
	}
}

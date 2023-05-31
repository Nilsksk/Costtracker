package costtracker.plugin.ui;

import java.util.ArrayList;
import java.util.List;

import costtracker.domain.businessobjects.Purchase;

public class PurchaseModelFactory {
	
	private List<PurchaseModel> purchaseModels;
	
	public PurchaseModelFactory() {
		this.purchaseModels = new ArrayList<>();
	}
	
	public List<PurchaseModel> createPurchaseModels(List<Purchase> purchases){
		try {
			for (int i = 1; i <= purchases.size(); i++) {
				PurchaseModel purchaseModel = new PurchaseModel(i, purchases.get(i-1));
				purchaseModels.add(purchaseModel);
			}
			return purchaseModels;			
		}catch(Exception e) {
			purchaseModels.clear();
			return null;
		}
	}
}

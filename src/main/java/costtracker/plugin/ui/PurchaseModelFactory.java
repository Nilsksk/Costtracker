package costtracker.plugin.ui;

import java.util.ArrayList;
import java.util.List;

import costtracker.domain.businessobjects.Purchase;

public class PurchaseModelFactory {
	
	private List<PurchaseModel> purchaseModels = new ArrayList<>();
	
	public List<PurchaseModel> createPurchaseModels(List<Purchase> purchases){
		try {
			purchaseModels.clear();
			return createModels(purchases);			
		}catch(Exception e) {
			purchaseModels.clear();
			return null;
		}
	}
	
	private List<PurchaseModel> createModels(List<Purchase> purchases){
		for (int i = 1; i <= purchases.size(); i++) {
			addPurchaseToList(purchases, i);
		}
		return purchaseModels;
	}
	
	private void addPurchaseToList (List<Purchase> purchases, int i) {
		try {
			PurchaseModel purchaseModel = new PurchaseModel(i, purchases.get(i-1));
			purchaseModels.add(purchaseModel);
		} catch (Exception e) {
			
			DialogueHelper.println("Fehler!");
		}
	}
}

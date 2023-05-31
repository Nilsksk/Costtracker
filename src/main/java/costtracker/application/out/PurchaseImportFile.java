package costtracker.application.out;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import costtracker.application.converter.PurchaseConverter;
import costtracker.domain.businessobjects.Purchase;

public class PurchaseImportFile {

	private ImportFile importFile;

	private List<Purchase> purchases;
	
	public PurchaseImportFile(ImportFile importFile) {
		this.importFile = importFile;
		purchases = new ArrayList<>();
	}

	public List<Purchase> getPurchases() {
		convertToPurchases();		
		return purchases;
	}

	private void convertToPurchases() {
		while (importFile.hasNextEntry()) {
			convertToPurchase(importFile.getNextEntry());			
		}		
	}

	private void convertToPurchase(HashMap<String, String> entry) {
		PurchaseConverter pc = new PurchaseConverter();
		Purchase purchase = pc.convertFrom(entry);
		purchases.add(purchase);	
	}
}

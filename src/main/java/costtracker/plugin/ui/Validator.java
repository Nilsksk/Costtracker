package costtracker.plugin.ui;

import java.time.LocalDate;
import java.util.List;

import costtracker.domain.businessobjects.Category;
import costtracker.domain.businessobjects.Company;
import costtracker.domain.businessobjects.Purchase;

public class Validator {

	public static double checkPrize(Purchase purchase) {
		String price = "Preis";
		String purchasePrice = String.valueOf(purchase.getPrice());
		String newPurchasePrice = DialogueHelper.printChangeDialogueWith(price, purchasePrice);
		boolean priceIsEmpty = newPurchasePrice.isEmpty();
		if (priceIsEmpty) {
			double priceDouble = purchase.getPrice();
			return priceDouble;
		}
		return Double.parseDouble(newPurchasePrice);
	}
	
	public static LocalDate checkDate(Purchase purchase) {
		String date = "Datum";
		String purchaseDate = purchase.getDateString();
		String newPurchaseDate = DialogueHelper.printChangeDialogueWith(date, purchaseDate);
		if (newPurchaseDate.isEmpty()) {
			LocalDate datePurchase = purchase.getDate(); 
			return datePurchase;
		}
		LocalDate newDate = DateConverter.convertDateWithoutDialogue(newPurchaseDate);
		return newDate;
	}
		
	public static int checkCompanyId(Purchase purchase, List<Company> companies) {
		String id = "Firmen-ID";
		String companyId = Integer.toString(purchase.getCompany().getId());
		String newPurchaseCompanyId = DialogueHelper.printChangeDialogueWith(id, companyId);		
		try {
			int idCompany = Integer.parseInt(newPurchaseCompanyId);
			return idCompany;
		}
		catch(NumberFormatException e){
			int idCompany = purchase.getCompany().getId();
			return idCompany;
		}
	}
	
	public static int checkCategoryId(Purchase purchase, List<Category> categories) {
		//String id = "Kategorie-ID";
		String categoryId = Integer.toString(purchase.getCategory().getId());
		String newPurchaseCategory = DialogueHelper.printChangeDialogueWith("Kategorie-ID", categoryId);		
		try {
			int idCategory = Integer.parseInt(newPurchaseCategory); 
			return idCategory;	
		}
		catch(NumberFormatException e){
			int idCategory = purchase.getCategory().getId();
			return idCategory;
		}
	}
}

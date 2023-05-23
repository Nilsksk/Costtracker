package costtracker.ui;

import java.time.LocalDate;
import java.util.List;

import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;
import costtracker.businessobjects.Purchase;

public class Validator {

	public static double checkPrize(Purchase purchase) {
		String newPurchasePrice = null;
		newPurchasePrice = DialogueHelper.changeDialogue("Preis", String.valueOf(purchase.getPrice()));
		//ReSet.setNewDouble(newPurchasePrice, purchase::setPrice);
		if (newPurchasePrice.isEmpty()) {
			return purchase.getPrice();
		}
		return Double.parseDouble(newPurchasePrice);
	}
	
	public static LocalDate checkDate(Purchase purchase) {
		String newPurchaseDate = null;
		newPurchaseDate = DialogueHelper.changeDialogue("Datum", purchase.getDateString());
		if (newPurchaseDate.isEmpty()) {
			return purchase.getDate();
		}
		return LocalDate.parse(newPurchaseDate);
	}
		
	public static int checkCompanyId(Purchase purchase, List<Company> companies) {
		String newPurchaseCompanyId;
		newPurchaseCompanyId = DialogueHelper.changeDialogue("Firmen-ID", Integer.toString(purchase.getCompany().getId()));		
		try {
			return Integer.parseInt(newPurchaseCompanyId);
		}
		catch(NumberFormatException e){
			return purchase.getCompany().getId();
		}
	}
	
	public static int checkCategoryId(Purchase purchase, List<Category> categories) {
		String newPurchaseCategory;	
		newPurchaseCategory = DialogueHelper.changeDialogue("Kategorie-ID", Integer.toString(purchase.getCategory().getId()));		
		try {
			return purchase.getCategory().getId();
		}
		catch(NumberFormatException e){
			return Integer.parseInt(newPurchaseCategory);			
		}
	}
}

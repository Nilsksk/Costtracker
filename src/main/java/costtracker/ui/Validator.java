package costtracker.ui;

import java.time.LocalDate;
import java.util.List;

import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;
import costtracker.businessobjects.Purchase;

public class Validator {

	public static double checkPrize(Purchase purchase) {
		String newPurchasePrice = null;
		try {
			newPurchasePrice = DialogueHelper.changeDialogue(String.valueOf(purchase.getPrice()));
			ReSet.setNewDouble(newPurchasePrice, purchase::setPrice);
		}catch (Exception e) {
			System.out.println("Kein gültiger Pries!");
			checkPrize(purchase);
		}
		return Double.parseDouble(newPurchasePrice);
	}
	
	public static LocalDate checkDate(Purchase purchase) {
		String newPurchaseDate = null;
		try {
			newPurchaseDate = DialogueHelper.changeDialogue(purchase.getDateString());
		}catch (Exception e) {
			System.out.println("Kein gültiges Datum!");
			checkDate(purchase);
		}
		return LocalDate.parse(newPurchaseDate);
	}
		
	public static int checkCompanyId(Purchase purchase, List<Company> companies) {
		int newPurchaseCompanyId = -1;
		try {
			try {
				newPurchaseCompanyId = Integer.parseInt(DialogueHelper.changeDialogue("Firmen-ID: " + Integer.toString(purchase.getCompany().getId())));		
			}catch (Exception e) {			
				newPurchaseCompanyId = Integer.parseInt(DialogueHelper.changeDialogue("Firmen-ID: "));
			}	
		}catch (Exception e) {
			System.out.println("Keine gültige Firmen-ID!");
			checkCompanyId(purchase, companies);
		}
		return newPurchaseCompanyId;
	}
	
	public static int checkCategoryId(Purchase purchase, List<Category> categories) {
		int newPurchaseCategory = -1;	
		try {
			try {
				newPurchaseCategory = Integer.parseInt(DialogueHelper.changeDialogue("Firmen-ID: " + Integer.toString(purchase.getCategory().getId())));	
			}catch (Exception e) {
				newPurchaseCategory = Integer.parseInt(DialogueHelper.changeDialogue("Firmen-ID: "));
			}			
		}catch (Exception e) {
			System.out.println("Keine gültige Firmen-ID!");
			checkCategoryId(purchase, categories);
		}
		return newPurchaseCategory;
	}
}

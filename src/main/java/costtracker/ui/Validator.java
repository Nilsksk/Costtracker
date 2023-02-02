package costtracker.ui;

import java.util.List;

import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;
import costtracker.businessobjects.Purchase;

public class Validator {

	public static String checkPrize(Purchase purchase) {
		String newPurchasePrice = null;
		try {
			newPurchasePrice = DialogueHelper.changeDialogue(String.valueOf(purchase.getPrice()));
			ReSet.setNewDouble(newPurchasePrice, purchase::setPrice);
		}catch (Exception e) {
			System.out.println("Kein gültiger Pries!");
			checkPrize(purchase);
		}
		return newPurchasePrice;
	}
	
	public static String checkDate(Purchase purchase) {
		String newPurchaseDate = null;
		try {
			newPurchaseDate = DialogueHelper.changeDialogue(purchase.getDateString());
			ReSet.setNewDate(newPurchaseDate, purchase::setDate);
		}catch (Exception e) {
			System.out.println("Kein gültiges Datum!");
			checkDate(purchase);
		}
		return newPurchaseDate;
	}
		
	public static String checkCompanyId(Purchase purchase, List<Company> companies) {
		String newPurchaseCompany = null;	
		try {
			try {
				newPurchaseCompany = DialogueHelper.changeDialogue(purchase.getCompany().getName());		
			}catch (Exception e) {			
				newPurchaseCompany = DialogueHelper.changeDialogue(" ");
			}
			ReSet.setNewCompany(newPurchaseCompany, purchase::setCompany, companies);		
		}catch (Exception e) {
			System.out.println("Keine gültige Firmen-ID!");
			checkCompanyId(purchase, companies);
		}
		return newPurchaseCompany;
	}
	
	public static String checkCategoryId(Purchase purchase, List<Category> categories) {
		String newPurchaseCategory = null;	
		try {
			newPurchaseCategory = DialogueHelper.changeDialogue(purchase.getCategory().getName());
			ReSet.setNewCategory(newPurchaseCategory, purchase::setCategory, categories);		
		}catch (Exception e) {
			System.out.println("Keine gültige Kategorie-ID!");
			checkCategoryId(purchase, categories);
		}
		return newPurchaseCategory;
	}
}

package costtracker.ui;

import java.awt.Dialog;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;
import costtracker.businessobjects.Purchase;

public class PurchaseManager implements Editor, Adder {

	List<Company> companies;
	List<Category> categories;
	List<Purchase> purchases;
	
	@Override
	public void add() {
		
		boolean breaking;
		do {
			DialogueHelper.startDialogue("Enter Taste drücken um neuen Einkauf hinzuzufügen");
			String purchaseName = DialogueHelper.inputDialogue("Name");
			double validatedValue = validatedValue();
			LocalDate validatedDate = validateDate();	
			System.out.println();
			DialogueHelper.printCompanies(companies);	
			System.out.println();
			int companyId = DialogueHelper.getIdDialogue("Geben sie die ID der Firma an, bei der ihr Kauf getätigt wurde");
			System.out.println();
			DialogueHelper.printCategories(categories);
			int categoryId = validateCategory();	
			String purchaseDescription = DialogueHelper.inputDialogue("Geben sie optional eine Beschreibung ihres Kaufes an");
			System.out.println();
			
			boolean submit = DialogueHelper.saveData("Zum Speichern ihrer Daten '+' eingeben: ", "+");
			// Speicherung der Daten in Datenbank
			// Bei Erfolg Rückmeldung geben
			
			breaking = breakInput();
		}while( breaking);
	}

	@Override
	public void edit() {
		DialogueHelper.startDialogue("Enter Taste drücken um Liste aller getätigten Einkäufe zu erhalten");
	    DialogueHelper.printPurchases(purchases);
		
	}
	
	private double validatedValue() {
		double doublePurchaseValue = 0;
		do {
			String purchaseValue = DialogueHelper.inputDialogue("Kaufpreis");
			try {
				doublePurchaseValue = Double.parseDouble(purchaseValue);
			}
			catch(Exception e){
				System.out.println("Geben sie einen validen Kaufpreis an!");
			}		
		}while(doublePurchaseValue == 0);
		
		return doublePurchaseValue;
	}
	
	
	private LocalDate validateDate() {
		LocalDate date = null;
		do {
			String purchaseDate = DialogueHelper.inputDialogue("Datum des Kaufs (TT:MM:JJJJ)");	
			String[] dateValues = purchaseDate.split(":");
			try {
				date = LocalDate.of(Integer.parseInt(dateValues[2]), Integer.parseInt(dateValues[1]), Integer.parseInt(dateValues[0]));		
			}
			catch(Exception e) {
				DialogueHelper.println("Geben sie ein valides Datum an!");
			}
		}while(date == null);
		
		return date;
	}

	private boolean breakInput() {
		return DialogueHelper.saveData("Falls sie weitere Einkäufe eintragen wollen erneut '+' drücken: ", "+");
	}
	
	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}
	
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}
	
	private int validateCategory() {
		boolean categoryFound;
		int categoryId;
		do {
			categoryId = DialogueHelper.getIdDialogue("Geben sie die ID der Kategorie an, zu welcher ihr Kauf am besten passt: ");	
			final int categoryIdCopy = categoryId;
			categoryFound = categories.stream().anyMatch(c -> c.getId() == categoryIdCopy);
			if (!categoryFound) {
				System.out.println("Keine gültige ID!");
			}
			
		}while(!categoryFound);
		return categoryId;
	}
}

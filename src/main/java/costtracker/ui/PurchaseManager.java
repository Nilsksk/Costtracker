package costtracker.ui;

import java.awt.Dialog;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import costtracker.buisnesslogic.CategoryHandler;
import costtracker.buisnesslogic.CompanyHandler;
import costtracker.buisnesslogic.PurchaseHandler;
import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;
import costtracker.businessobjects.Purchase;
import costtracker.ui.interfaces.Adder;
import costtracker.ui.interfaces.Editor;
import costtracker.ui.GetBusinessObject;

public class PurchaseManager implements Editor, Adder {

	private PurchaseHandler purchaseHandler;
	private CompanyHandler companyHandler;
	private CategoryHandler categoryHandler;
	boolean submit;
	
	@Override
	public void add() throws SQLException {
		
		boolean breaking;
		boolean created = false;
		do {
			DialogueHelper.startDialogue("Enter Taste drücken um neuen Einkauf hinzuzufügen");
			String purchaseName = DialogueHelper.inputDialogue("Name");
			double validatedValue = validatedValue();
			LocalDate validatedDate = validateDate();	
			System.out.println();
			DialogueHelper.printCompanies(companyHandler.getAll());	
			System.out.println();
			int companyId = DialogueHelper.getIdDialogue("Geben sie die ID der Firma an, bei der ihr Kauf getätigt wurde");
			Company company = GetBusinessObject.getById(companyId, companyHandler.getAll(), Company::getId);
			System.out.println();
			DialogueHelper.printCategories(categoryHandler.getAll());
			int categoryId = validateCategory();
			Category category = GetBusinessObject.getById(categoryId, categoryHandler.getAll(), Category::getId);
			String purchaseDescription = DialogueHelper.inputDialogue("Geben sie optional eine Beschreibung ihres Kaufes an");
			System.out.println();		
			submit = DialogueHelper.submitEntry();
			
			if (submit) {
				created = purchaseHandler.create(new Purchase(0, purchaseName, purchaseDescription, validatedDate, validatedValue, company, category));				
			}
			
			DialogueHelper.validateCreation(created);
			//Testen!
			
			breaking = breakInput();
		}while(breaking);
	}

	@Override
	public void edit() throws SQLException {
		boolean updated = false;
		DialogueHelper.startDialogue("Enter Taste drücken um Liste aller getätigten Einkäufe zu erhalten");
	    DialogueHelper.printPurchases(purchaseHandler.getAll());
	    
		Purchase purchase = getPurchaseToEdit();
		
		String newPurchaseName = DialogueHelper.changeDialogue(purchase.getName());
		ReSet.setNewString(newPurchaseName, purchase::setName);
		double newPurchasePrice = Validator.checkPrize(purchase);
		LocalDate newPurchaseDate = Validator.checkDate(purchase);
		DialogueHelper.printCompanies(companyHandler.getAll());
		int newPurchaseCompany = Validator.checkCompanyId(purchase, companyHandler.getAll());
		Company company = GetBusinessObject.getById(newPurchaseCompany, companyHandler.getAll(), Company::getId);
		DialogueHelper.printCategories(categoryHandler.getAll());
		int newPurchaseCategory = Validator.checkCategoryId(purchase, categoryHandler.getAll());
		Category category = GetBusinessObject.getById(newPurchaseCategory, categoryHandler.getAll(), Category::getId);
		String newPurchaseDescription = DialogueHelper.changeDialogue(purchase.getDescription());
		ReSet.setNewString(newPurchaseDescription, purchase::setDescription);
		submit = DialogueHelper.submitEntry();
		
		if (submit) {
			updated = purchaseHandler.update(new Purchase(0, newPurchaseName, newPurchaseDescription, newPurchaseDate, newPurchasePrice, company, category));
		}
		//Testen!
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
	
	private int validateCategory() throws SQLException {
		Category category;
			int categoryId = DialogueHelper.getIdDialogue("Geben sie die ID der Kategorie an, zu welcher ihr Kauf am besten passt: ");	
			category = GetBusinessObject.getById(categoryId, categoryHandler.getAll(), Category::getId);
			if (category.equals(null)) {
				System.out.println("Keine gültige ID!");
				validateCategory();
			}
		return categoryId;
	}
	
	private Purchase getPurchaseToEdit() throws SQLException {
		Purchase purchase0;
		do {
			int id = DialogueHelper.getIdDialogue("ID der Kategorie auswählen, die Sie bearbeiten möchten: ");
			if (id < 0) {
				DialogueHelper.println("Falsche Eingabe!");
				getPurchaseToEdit();
			}
			purchase0 = GetBusinessObject.getById(id, purchaseHandler.getAll(), Purchase::getId);

		} while (purchase0.equals(null));

		return purchase0;
	}
	
	
}

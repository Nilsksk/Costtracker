package costtracker.ui;

import java.awt.Dialog;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
	
	public PurchaseManager() {
		this.purchaseHandler = new PurchaseHandler();
		this.companyHandler = new CompanyHandler();
		this.categoryHandler = new CategoryHandler();
	}
	@Override
	public void add() throws SQLException {
		
		boolean breaking;
		boolean created = false;
		do {
			DialogueHelper.startDialogue("Enter Taste drücken um neuen Einkauf hinzuzufügen");
			String purchaseName = DialogueHelper.inputDialogue("Name");
			double validatedValue = validatedValue();
			LocalDate validatedDate = DateConverter.convertDate("Datum des Kaufs (TT:MM:JJJJ)");	
			System.out.println();
			DialogueHelper.printCompanies(companyHandler.getAll());	
			System.out.println();
			Company company = validateCompany();
			System.out.println();
			DialogueHelper.printCategories(categoryHandler.getAll());
			Category category = validateCategory();
			String purchaseDescription = DialogueHelper.inputDialogue("Geben sie optional eine Beschreibung ihres Kaufes an");
			System.out.println();		
			submit = DialogueHelper.submitEntry();
			
			if (submit && checkIfAllParametersPresent(purchaseName, validatedDate, validatedValue, company, category)) {
				created = purchaseHandler.create(new Purchase(purchaseName, purchaseDescription, validatedDate, validatedValue, company, category));				
				DialogueHelper.validateCreation(created);
			}			
			else {
				DialogueHelper.print("Unzureichende Eingaben!");
			}
			breaking = breakInput();
		}while(breaking);
	}

	@Override
	public void edit() throws SQLException {
		boolean validInput = true;
		boolean updated = false;
		DialogueHelper.startDialogue("Enter Taste drücken um Liste aller getätigten Einkäufe zu erhalten");
	    DialogueHelper.printPurchases(purchaseHandler.getAll());
	    
	    int input = DialogueHelper.interactQuestion("Wollen sie einen Kauf anpassen (1) oder löschen (2)?");
		Purchase purchase = getPurchaseToEdit();
		
		while(validInput) {
			if (!DialogueHelper.isValidInput(input, 3)) {
				DialogueHelper.println("Falsche Eingabe " + input + "!");
				break;
			}	
			if (input == 1) {
				validInput = false;
				if (purchase == null) {
					DialogueHelper.println("Kein Einkauf unter dieser ID hinterlegt!");
					
				}
				else {
					String newPurchaseName = DialogueHelper.changeDialogue("Name", purchase.getName());
					if (newPurchaseName.isEmpty()) {
						newPurchaseName = purchase.getName();
					}
					double newPurchasePrice = Validator.checkPrize(purchase);
					if (String.valueOf(newPurchasePrice).isEmpty()) {
						newPurchasePrice = purchase.getPrice();
					}
					LocalDate newPurchaseDate = Validator.checkDate(purchase);
					if (newPurchaseDate == null) {
						newPurchaseDate = purchase.getDate();
					}
					DialogueHelper.printCompanies(companyHandler.getAll());
					Company company = validateCompany();
					if (company == null) {
						company = purchase.getCompany();
					}
					DialogueHelper.printCategories(categoryHandler.getAll());
					Category category = validateCategory();
					if (category == null) {
						category = purchase.getCategory();
					}
					String newPurchaseDescription = DialogueHelper.changeDialogue("Beschreibung", purchase.getDescription());
					submit = DialogueHelper.submitEntry();
					
					if (submit) {
						updated = purchaseHandler.update(new Purchase(purchase.getId(), newPurchaseName, newPurchaseDescription, newPurchaseDate, newPurchasePrice, company, category));
						DialogueHelper.validateCreation(updated);
					}	
				}
			}
			else if (input == 2) {
				validInput = false;
				List<Purchase> purchaseToDelete = new ArrayList<Purchase>();
				purchaseToDelete.add(purchase);
				DialogueHelper.println("");
				try {
					DialogueHelper.printPurchases(purchaseToDelete);					
				}
				catch(Exception e) {
					break;
				}
				if (DialogueHelper.validateDeleteOrDeactivation("Erfolgreich gelöscht!")) {
					purchaseHandler.deleteById(purchase.getId());
				}
			}	
		}
	}
	
	private double validatedValue() {
		double doublePurchaseValue;
		
		String purchaseValue = DialogueHelper.inputDialogue("Kaufpreis");
		try {
			doublePurchaseValue = Double.parseDouble(purchaseValue);
		}
		catch(Exception e){
			doublePurchaseValue = 0;
		}		
		return doublePurchaseValue;
	}

	private boolean breakInput() {
		System.out.println();
		return DialogueHelper.saveData("Falls sie weitere Einkäufe eintragen wollen erneut '+' drücken: ", "+");
	}
	
	private Company validateCompany() throws SQLException {
		Company company;
		try {
			int companyId = DialogueHelper.getIntDialogue("Geben sie die ID der Firma an, bei welcher Ihr Kauf geätigt wurde");	
			company = GetBusinessObject.getById(companyId, companyHandler.getAll(), Company::getId);
		}
		catch(Exception e) {
			company = null;
		}
		return company;
	}
	
	private Category validateCategory() throws SQLException {
		Category category;
		try {
			int categoryId = DialogueHelper.getIntDialogue("Geben sie die ID der Kategorie an, zu welcher ihr Kauf am besten passt");	
			category = GetBusinessObject.getById(categoryId, categoryHandler.getAll(), Category::getId);
		}
		catch(Exception e) {
			category = null;
		}
		return category;
	}
	
	private Purchase getPurchaseToEdit() throws SQLException {
		Purchase purchase;
		try {
			int id = DialogueHelper.getIntDialogue("ID der Kategorie auswählen, die Sie bearbeiten möchten");
			purchase = GetBusinessObject.getById(id, purchaseHandler.getAll(), Purchase::getId);

		} 
		catch(Exception e) {
			purchase = null;
		}

		return purchase;
	}
	
	private boolean checkIfAllParametersPresent(String name, LocalDate date, double price, Company company, Category category) {
		if (!name.isEmpty() && date != null && (price == 0 || !String.valueOf(price).isEmpty()) && !company.equals(null) && !category.equals(null)) {
			return true;			
		}
		else {
			return false;
		}
	}
	
	
}

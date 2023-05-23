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
			LocalDate validatedDate = validateDate();	
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
		boolean updated = false;
		DialogueHelper.startDialogue("Enter Taste drücken um Liste aller getätigten Einkäufe zu erhalten");
	    DialogueHelper.printPurchases(purchaseHandler.getAll());
	    
		Purchase purchase = getPurchaseToEdit();
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
	
	
	private LocalDate validateDate() {
		LocalDate date;

		String purchaseDate = DialogueHelper.inputDialogue("Datum des Kaufs (TT:MM:JJJJ)");	
		String[] dateValues = purchaseDate.split(":");
		try {
			date = LocalDate.of(Integer.parseInt(dateValues[2]), Integer.parseInt(dateValues[1]), Integer.parseInt(dateValues[0]));		
		}
		catch(Exception e) {
			date = null;
		}
	
		return date;
	}

	private boolean breakInput() {
		System.out.println();
		return DialogueHelper.saveData("Falls sie weitere Einkäufe eintragen wollen erneut '+' drücken: ", "+");
	}
	
	private Company validateCompany() throws SQLException {
		Company company;
		try {
			int companyId = DialogueHelper.getIdDialogue("Geben sie die ID der Firma an, bei welcher Ihr Kauf geätigt wurde");	
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
			int categoryId = DialogueHelper.getIdDialogue("Geben sie die ID der Kategorie an, zu welcher ihr Kauf am besten passt");	
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
			int id = DialogueHelper.getIdDialogue("ID der Kategorie auswählen, die Sie bearbeiten möchten");
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

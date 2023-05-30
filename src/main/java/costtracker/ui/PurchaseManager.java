package costtracker.ui;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import costtracker.buisnesslogic.CategoryHandler;
import costtracker.buisnesslogic.CompanyHandler;
import costtracker.buisnesslogic.PurchaseHandler;
import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;
import costtracker.businessobjects.Purchase;
import costtracker.ui.interfaces.Adder;
import costtracker.ui.interfaces.Editor;

public class PurchaseManager implements Editor, Adder {

	private PurchaseHandler purchaseHandler;
	private CompanyHandler companyHandler;
	private CategoryHandler categoryHandler;
	private CategoryModelFactory categoryModelFactory;
	private CompanyModelFactory companyModelFactory;
	private PurchaseModelFactory purchaseModelFactory;
	boolean submit;
	
	public PurchaseManager() {
		this.purchaseHandler = new PurchaseHandler();
		this.companyHandler = new CompanyHandler();
		this.categoryHandler = new CategoryHandler();
		this.companyModelFactory = new CompanyModelFactory();
		this.categoryModelFactory = new CategoryModelFactory();
		this.purchaseModelFactory = new PurchaseModelFactory();
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
			DialogueHelper.printCompanies(companyModelFactory.createCompanyModels(companyHandler.getAll()));	
			System.out.println();
			Company company = validateCompany();
			System.out.println();
			DialogueHelper.printCategories(categoryModelFactory.createCategoryModels(categoryHandler.getAll()));
			Category category = validateCategory();
			String purchaseDescription = DialogueHelper.inputDialogue("Geben sie optional eine Beschreibung ihres Kaufes an");
			System.out.println();		
			submit = DialogueHelper.submitEntry();
			
			if (submit && checkIfAllParametersPresent(purchaseName, validatedDate, validatedValue, company, category)) {
				created = purchaseHandler.create(new Purchase(0, purchaseName, purchaseDescription, validatedDate, validatedValue, company, category));
				String succesful = "Angelegt!";
				String unsuccesful = "Anlegen fehlgeschlagen!";
				DialogueHelper.validateCreation(created, succesful, unsuccesful);
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
		Purchase purchase;
		DialogueHelper.startDialogue("Enter Taste drücken um Liste aller getätigten Einkäufe zu erhalten");
	    DialogueHelper.printPurchases(purchaseModelFactory.createPurchaseModels(purchaseHandler.getAll()));
	    
	    int input = DialogueHelper.interactQuestion("Wollen sie einen Kauf anpassen (1) oder löschen (2)?");
		
		while(validInput) {
			if (!DialogueHelper.isValidInput(input, 3)) {
				DialogueHelper.println("Falsche Eingabe " + input + "!");
				break;
			}	
			purchase = getPurchaseToEdit();
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
					DialogueHelper.printCompanies(companyModelFactory.createCompanyModels(companyHandler.getAll()));
					Company company = validateCompany();
					if (company == null) {
						company = purchase.getCompany();
					}
					DialogueHelper.printCategories(categoryModelFactory.createCategoryModels(categoryHandler.getAll()));
					Category category = validateCategory();
					if (category == null) {
						category = purchase.getCategory();
					}
					String newPurchaseDescription = DialogueHelper.changeDialogue("Beschreibung", purchase.getDescription());
					submit = DialogueHelper.submitEntry();
					
					if (submit) {
						updated = purchaseHandler.update(new Purchase(purchase.getId(), newPurchaseName, newPurchaseDescription, newPurchaseDate, newPurchasePrice, company, category));
						String succesful = "Erfolgreich bearbeitet!";
						String unsuccesful = "Bearbeiten fehlgeschlagen!";
						DialogueHelper.validateCreation(updated, succesful, unsuccesful);
					}	
				}
			}
			else if (input == 2) {
				validInput = false;
				List<Purchase> purchaseToDelete = new ArrayList<Purchase>();
				purchaseToDelete.add(purchase);
				DialogueHelper.println("");
				try {
					//DialogueHelper.printPurchases(purchaseToDelete);					
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
		double price;
		
		String inputPrice = DialogueHelper.inputDialogue("Kaufpreis");
		try {
			price = Double.parseDouble(inputPrice);
		}
		catch(Exception e){
			price = 0;
		}		
		return price;
	}

	private boolean breakInput() {
		System.out.println();
		return DialogueHelper.saveData("Falls sie weitere Einkäufe eintragen wollen erneut '+' drücken: ", "+");
	}
	
	private Company validateCompany() throws SQLException {
		Company company;
		try {
			int companyId = DialogueHelper.getIntDialogue("Geben sie die ID der Firma an, bei welcher Ihr Kauf geätigt wurde");	
			company = companyHandler.getById(companyId);
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
			category = categoryHandler.getById(categoryId);
		}
		catch(Exception e) {
			category = null;
		}
		return category;
	}
	
	private Purchase getPurchaseToEdit() throws SQLException {
		int id = DialogueHelper.getIntDialogue("ID des Kaufs auswählen, welchen Sie bearbeiten möchten");
		List<PurchaseModel> purchaseModels = purchaseModelFactory.createPurchaseModels(purchaseHandler.getAll());
		PurchaseModel purchaseModel = purchaseModels.stream().filter(p -> p.getPostion() == id).findAny().orElse(null);	
		return purchaseModel.getPurchase();
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

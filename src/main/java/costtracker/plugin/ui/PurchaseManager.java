package costtracker.plugin.ui;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import costtracker.application.handlers.CategoryHandler;
import costtracker.application.handlers.CompanyHandler;
import costtracker.application.handlers.PurchaseHandler;
import costtracker.domain.businessobjects.Category;
import costtracker.domain.businessobjects.Company;
import costtracker.domain.businessobjects.IncorrectEntryException;
import costtracker.domain.businessobjects.Purchase;
import costtracker.plugin.ui.interfaces.Adder;
import costtracker.plugin.ui.interfaces.Editor;

public class PurchaseManager implements Editor, Adder {

	@Override
	public void add() throws SQLException {
		
		boolean breaking;
		boolean created = false;
		CompanyModelFactory companyModelFactory = new CompanyModelFactory();
		CompanyHandler companyHandler = new CompanyHandler();
		CategoryModelFactory categoryModelFactory = new CategoryModelFactory();
		CategoryHandler categoryHandler = new CategoryHandler();
		PurchaseHandler purchaseHandler = new PurchaseHandler();
		do {
			String addPurchase = "Enter Taste drücken um neuen Einkauf hinzuzufügen";
			DialogueHelper.startDialogue(addPurchase);
			String name = "Name";
			String purchaseName = DialogueHelper.inputDialogue(name);
			double validatedValue = validatedValue();
			String date = "Datum des Kaufs (TT:MM:JJJJ)";
			LocalDate validatedDate = DateConverter.convertDate(date);	
			DialogueHelper.println("");
			List<Company> companies = companyHandler.getAll();
			List<CompanyModel> companyModels = companyModelFactory.createCompanyModels(companies);
			DialogueHelper.printCompanies(companyModels);	
			DialogueHelper.println("");
			Company company = validateCompany(companyHandler);
			DialogueHelper.println("");
			List<Category> categories = categoryHandler.getAll();
			List<CategoryModel> categoryModels = categoryModelFactory.createCategoryModels(categories);
			DialogueHelper.printCategories(categoryModels);
			Category category = validateCategory(categoryHandler);
			String desc = "Geben sie optional eine Beschreibung ihres Kaufes an";
			String purchaseDescription = DialogueHelper.inputDialogue(desc);
			System.out.println();		
			boolean submit = DialogueHelper.submitEntry();
			boolean correct = submit && checkIfAllParametersPresent(purchaseName, validatedDate, validatedValue, company, category);
			if (correct) {
				try {
					created = purchaseHandler.create(Purchase.PurchaseBuilder.withValues(purchaseName, validatedDate, validatedValue).withCompany(company).withCategory(category).withDescription(purchaseDescription).build());
				} catch (IncorrectEntryException e) {
					String errorMsg = "Fehler!";
					DialogueHelper.println(errorMsg);
				}
				String succesful = "Angelegt!";
				String unsuccesful = "Anlegen fehlgeschlagen!";
				DialogueHelper.validateCreation(created, succesful, unsuccesful);
			}			
			else {
				String errorMsg = "Unzureichende Eingaben!";
				DialogueHelper.print(errorMsg);
			}
			breaking = breakInput();
		}while(breaking);
	}

	@Override
	public void edit() throws SQLException {
		boolean validInput = true;
		boolean updated = false;
		PurchaseModelFactory purchaseModelFactory = new PurchaseModelFactory();
		PurchaseHandler purchaseHandler = new PurchaseHandler();
		CompanyModelFactory companyModelFactory = new CompanyModelFactory();
		CompanyHandler companyHandler = new CompanyHandler();
		CategoryModelFactory categoryModelFactory = new CategoryModelFactory();
		CategoryHandler categoryHandler = new CategoryHandler();
		Purchase purchase;
		String printPurchases = "Enter Taste drücken um Liste aller getätigten Einkäufe zu erhalten";
		DialogueHelper.startDialogue(printPurchases);
		List<Purchase> purchases = purchaseHandler.getAll();
		List<PurchaseModel> purchaseModels = purchaseModelFactory.createPurchaseModels(purchases);
	    DialogueHelper.printPurchases(purchaseModels);
	    String question = "Wollen sie einen Kauf anpassen (1) oder löschen (2)?";
	    int input = DialogueHelper.interactQuestion(question);
		
		while(validInput) {
			boolean valid = !DialogueHelper.isValidInput(input, 3);
			if (valid) {
				String output = "Falsche Eingabe " + input + "!";
				DialogueHelper.println(output);
				break;
			}	
			purchase = getPurchaseToEdit(purchaseModelFactory, purchaseHandler);
			if (input == 1) {
				validInput = false;
				if (purchase == null) {
					String errorMsg = "Kein Einkauf unter dieser ID hinterlegt!";
					DialogueHelper.println(errorMsg);
				}
				else {
					//Hier weiter machen
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
					Company company = validateCompany(companyHandler);
					if (company == null) {
						company = purchase.getCompany();
					}
					DialogueHelper.printCategories(categoryModelFactory.createCategoryModels(categoryHandler.getAll()));
					Category category = validateCategory(categoryHandler);
					if (category == null) {
						category = purchase.getCategory();
					}
					String newPurchaseDescription = DialogueHelper.changeDialogue("Beschreibung", purchase.getDescription());
					boolean submit = DialogueHelper.submitEntry();
					
					if (submit) {
						try {
							updated = purchaseHandler.update(Purchase.PurchaseBuilder.withValues(newPurchaseName, newPurchaseDate, newPurchasePrice).withCompany(company).withCategory(category).withDescription(newPurchaseDescription).build());
						} catch (IncorrectEntryException e) {
							String errorMsg = "Fehler!";
							DialogueHelper.println(errorMsg);
						}
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
			else {
				break;
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
	
	private Company validateCompany(CompanyHandler companyHandler) throws SQLException {
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
	
	private Category validateCategory(CategoryHandler categoryHandler) throws SQLException {
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
	
	private Purchase getPurchaseToEdit(PurchaseModelFactory purchaseModelFactory, PurchaseHandler purchaseHandler) throws SQLException {
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

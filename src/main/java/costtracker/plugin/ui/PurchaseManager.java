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
			List<Company> companies = companyHandler.getEnabled();
			List<CompanyModel> companyModels = companyModelFactory.createCompanyModels(companies);
			CompanyPrinter.printCompanies(companyModels);	
			DialogueHelper.println("");
			Company company = validateCompany(companyHandler);
			DialogueHelper.println("");
			List<Category> categories = categoryHandler.getEnabled();
			List<CategoryModel> categoryModels = categoryModelFactory.createCategoryModels(categories);
			CategoryPrinter.printCategories(categoryModels);
			Category category = validateCategory(categoryHandler);
			String desc = "Geben sie optional eine Beschreibung ihres Kaufes an";
			String purchaseDescription = DialogueHelper.inputDialogue(desc);
			DialogueHelper.println("");		
			tryCreatePurchase(purchaseName, validatedDate, validatedValue, company, category, purchaseHandler, purchaseDescription, created);
			breaking = breakInput();
		}while(breaking);
	}
	
	private void tryCreatePurchase(String purchaseName, LocalDate validatedDate, double validatedValue, Company company, Category category, PurchaseHandler purchaseHandler, String purchaseDescription, boolean created) {
		boolean submit = DialogueHelper.submitEntry();
		boolean correct = submit && checkIfAllParametersPresent(purchaseName, validatedDate, validatedValue, company, category);
		if (correct) {
			try {
				Purchase purchase = Purchase.PurchaseBuilder.withValues(purchaseName, validatedDate, validatedValue).withCompany(company).withCategory(category).withDescription(purchaseDescription).build();
				created = purchaseHandler.create(purchase);
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
	    PurchasePrinter.printPurchases(purchaseModels);
	    String question = "Wollen sie einen Kauf anpassen (1) oder löschen (2)?";
		while(validInput) {
			int input = DialogueHelper.interactQuestion(question);
			boolean notValid = !DialogueHelper.isValidInput(input, 3);
			if (notValid) {
				String output = "Falsche Eingabe!";
				DialogueHelper.println(output);
				break;
			}	
			purchase = tryGetPurchase(purchaseModelFactory, purchaseHandler);
			if (input == 1) {
				tryEditPurchase(validInput, purchase, companyModelFactory, companyHandler, categoryModelFactory, categoryHandler, updated, purchaseHandler);
				break;
			}
			else if (input == 2) {
				tryDeletePurchase(validInput, purchase, purchaseHandler, purchaseModelFactory);
				break;
			}	
			else {
				break;
			}
		}
	}
	
	private Purchase tryGetPurchase(PurchaseModelFactory purchaseModelFactory, PurchaseHandler purchaseHandler) {
		try {
			return getPurchaseToEdit(purchaseModelFactory, purchaseHandler);
		} catch (Exception e) {
			return null;
		}
	}
	
	private void tryEditPurchase(boolean validInput, Purchase purchase, CompanyModelFactory companyModelFactory, CompanyHandler companyHandler,
								CategoryModelFactory categoryModelFactory, CategoryHandler categoryHandler, boolean updated, PurchaseHandler purchaseHandler) throws SQLException {
		validInput = false;
		if (purchase != null) {
			String purchaseName = purchase.getName();
			String name = "Name";
			String newPurchaseName = DialogueHelper.changeDialogue(name, purchaseName);
			boolean nameIsEmpty = newPurchaseName.isEmpty();
			if (nameIsEmpty) {
				newPurchaseName = purchase.getName();
			}
			double newPurchasePrice = Validator.checkPrize(purchase);
			boolean priceIsEmpty = String.valueOf(newPurchasePrice).isEmpty();
			if (priceIsEmpty) {
				newPurchasePrice = purchase.getPrice();
			}
			LocalDate newPurchaseDate = Validator.checkDate(purchase);
			boolean dateIsEmpty = newPurchaseDate == null; 
			if (dateIsEmpty) {
				newPurchaseDate = purchase.getDate();
			}
			List<Company> companies = companyHandler.getAll();
			List<CompanyModel> companyModels = companyModelFactory.createCompanyModels(companies);
			CompanyPrinter.printCompanies(companyModels);
			Company company = validateCompany(companyHandler);
			boolean companyIsEmptyy = company == null;
			if (companyIsEmptyy) {
				company = purchase.getCompany();
			}
			List<Category> categories = categoryHandler.getAll();
			List<CategoryModel> categoryModels = categoryModelFactory.createCategoryModels(categories);
			CategoryPrinter.printCategories(categoryModels);
			Category category = validateCategory(categoryHandler);
			boolean categoryIsEmpty = category == null; 
			if (categoryIsEmpty) {
				category = purchase.getCategory();
			}
			String purchaseDescription = purchase.getDescription();
			String description = "Beschreibung";
			String newPurchaseDescription = DialogueHelper.changeDialogue(description, purchaseDescription);
			boolean submit = DialogueHelper.submitEntry();
			if (submit) {
				try {
					Purchase purchaseNew = Purchase.PurchaseBuilder.withValues(purchaseName, newPurchaseDate, newPurchasePrice).withCompany(company).withCategory(category).withDescription(newPurchaseDescription).withId(purchase.getId()).build();
					updated = purchaseHandler.update(purchaseNew);
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
	
	private void tryDeletePurchase(boolean validInput, Purchase purchase, PurchaseHandler purchaseHandler, PurchaseModelFactory purchaseModelFactory) {
		validInput = false;
		List<Purchase> purchaseToDelete = new ArrayList<Purchase>();
		purchaseToDelete.add(purchase);
		DialogueHelper.println("");
		List<PurchaseModel> purchaseModels = purchaseModelFactory.createPurchaseModels(purchaseToDelete);
		boolean moreThenZeroPurchases = purchaseModels.get(0).getPurchase() != null;
		if (moreThenZeroPurchases) {
			PurchasePrinter.printPurchases(purchaseModels);						
			if (DialogueHelper.validateDeleteOrDeactivation("Erfolgreich gelöscht!")) {
				int id = purchase.getId();
				purchaseHandler.deleteById(id);
			}
		}
	}
	
	private double validatedValue() {
		double price;
		String purchasePrice = "Kaufpreis";
		String inputPrice = DialogueHelper.inputDialogue(purchasePrice);
		try {
			price = Double.parseDouble(inputPrice);
		}
		catch(Exception e){
			price = 0;
		}		
		return price;
	}

	private boolean breakInput() {
		DialogueHelper.println("");
		String saving = "Falls sie weitere Einkäufe eintragen wollen erneut '+' drücken: ";
		String input = "+";
		return DialogueHelper.saveData(saving, input);
	}
	
	private Company validateCompany(CompanyHandler companyHandler) throws SQLException {
		Company company;
		try {
			String getId = "Geben sie die ID der Firma an, bei welcher Ihr Kauf geätigt wurde";
			int companyId = DialogueHelper.getIntDialogue(getId);	
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
			String getId = "Geben sie die ID der Kategorie an, zu welcher ihr Kauf am besten passt";
			int categoryId = DialogueHelper.getIntDialogue(getId);	
			category = categoryHandler.getById(categoryId);
		}
		catch(Exception e) {
			category = null;
		}
		return category;
	}
	
	private Purchase getPurchaseToEdit(PurchaseModelFactory purchaseModelFactory, PurchaseHandler purchaseHandler) throws SQLException {
		String getId = "ID des Kaufs auswählen, welchen Sie bearbeiten möchten";
		int id = DialogueHelper.getIntDialogue(getId);
		List<Purchase> purchases = purchaseHandler.getAll();
		List<PurchaseModel> purchaseModels = purchaseModelFactory.createPurchaseModels(purchases);
		PurchaseModel purchaseModel = purchaseModels.stream().filter(p -> p.getPostion() == id).findAny().orElse(null);	
		Purchase purchase = purchaseModel.getPurchase();
		return purchase;
	}
	
	private boolean checkIfAllParametersPresent(String name, LocalDate date, double price, Company company, Category category) {
		boolean checkIfCorrectInput = !name.isEmpty() && date != null && (price == 0 || !String.valueOf(price).isEmpty()) && !company.equals(null) && !category.equals(null);
		return checkIfCorrectInput;
	}
	
	
}

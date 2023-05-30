package costtracker.ui;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import costtracker.buisnesslogic.CategoryHandler;
import costtracker.buisnesslogic.CompanyHandler;
import costtracker.buisnesslogic.PurchaseHandler;
import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;
import costtracker.businessobjects.Purchase;

public class History {
	
	public void printHistory() throws SQLException {
		PurchaseModelFactory purchaseModelFactory = new PurchaseModelFactory();
		PurchaseHandler purchaseHandler = new PurchaseHandler();
		String printPurchases = "Enter Taste drücken um Historie Ihrer Einkäufe anzuzeigen";
		DialogueHelper.startDialogue(printPurchases);
		List<PurchaseModel> getPurchases = purchaseModelFactory.createPurchaseModels(purchaseHandler.getAll());
		DialogueHelper.printPurchases(getPurchases);
		purchaseModelFactory.createPurchaseModels(null);
	}
	
	public void printHistoryForCompanyInTimeframe() {
		PurchaseModelFactory purchaseModelFactory = new PurchaseModelFactory();
		CompanyModelFactory companyModelFactory = new CompanyModelFactory();
		CompanyHandler companyHandler = new CompanyHandler();
		PurchaseHandler purchaseHandler = new PurchaseHandler();
		try {
			List<CompanyModel> getCompanies = companyModelFactory.createCompanyModels(companyHandler.getEnabled());
			DialogueHelper.printCompanies(getCompanies);
			String idDialogue = "Geben sie die ID der Firma an, nach welcher sortiert werden soll";
			int companyId = DialogueHelper.getIntDialogue(idDialogue);
			Company company = companyHandler.getById(companyId);
			String startDate = "Geben Sie das Startdatum an (TT:MM:JJJJ)";
			LocalDate inputDateBegin = DateConverter.convertDate(startDate);
			String endDate = "Geben Sie das Enddatum an (TT:MM:JJJJ)";
			LocalDate inputDateEnd = DateConverter.convertDate(endDate);
			DialogueHelper.println("");
			try {
				List<PurchaseModel> purchases = purchaseModelFactory.createPurchaseModels(purchaseHandler.getByCompanyByTimestamp(company, inputDateBegin, inputDateEnd));
				DialogueHelper.printPurchases(purchases);				
			}catch(Exception e) {
				String errorMsg = "In diesem Zeitraum wurden keine Käufe von dieser Firma getätigt!";
				DialogueHelper.println(errorMsg);
			}
		}catch(Exception e) {
			String errorMsg = "Falsche Angaben von Datum!";
			DialogueHelper.println(errorMsg);
		}
		purchaseModelFactory.createPurchaseModels(null);
	}
	
	public void printHistoryForCategoryInTimeframe() {
		CategoryModelFactory categoryModelFactory = new CategoryModelFactory();
		CategoryHandler categoryHandler = new CategoryHandler();
		PurchaseModelFactory purchaseModelFactory = new PurchaseModelFactory();
		PurchaseHandler purchaseHandler = new PurchaseHandler();
		try {
			List<CategoryModel> categories = categoryModelFactory.createCategoryModels(categoryHandler.getEnabled());
			DialogueHelper.printCategories(categories);
			String idDialogue = "Geben sie die ID der Kategorie an, nach welcher sortiert werden soll";
			int categoryId = DialogueHelper.getIntDialogue(idDialogue);
			Category category = categoryHandler.getById(categoryId);
			String startDate = "Geben Sie das Startdatum an (TT:MM:JJJJ)";
			LocalDate inputDateBegin = DateConverter.convertDate(startDate);
			String endDate = "Geben Sie das Enddatum an (TT:MM:JJJJ)";
			LocalDate inputDateEnd = DateConverter.convertDate(endDate);
			DialogueHelper.println("");
			try {
				List<PurchaseModel> purchases = purchaseModelFactory.createPurchaseModels(purchaseHandler.getByCategoryByTimestamp(category, inputDateBegin, inputDateEnd));
				DialogueHelper.printPurchases(purchases);				
			}catch(Exception e) {
				String errorMsg = "In diesem Zeitraum wurden keine Käufe aus dieser Kategorie getätigt!";
				DialogueHelper.println(errorMsg);
			}
		}catch(Exception e) {
			String errorMsg = "Falsche Angaben von Datum!";
			DialogueHelper.println(errorMsg);
		}
		purchaseModelFactory.createPurchaseModels(null);
	}
	
	public void printHistoryByYear() {
		PurchaseModelFactory purchaseModelFactory = new PurchaseModelFactory();
		PurchaseHandler purchaseHandler = new PurchaseHandler();
		String idDialogue = "Jahr nach dem gefiltert werden soll";
		int year = DialogueHelper.getIntDialogue(idDialogue);
		try {
			List<Purchase> purchase = purchaseHandler.getByYear(year);
			List<PurchaseModel> purchases = purchaseModelFactory.createPurchaseModels(purchase);
			DialogueHelper.printPurchases(purchases);
		}catch(Exception e) {
			String errorMsg = "In diesem Jahr wurden keine Käufe getätigt!";
			DialogueHelper.println(errorMsg);
		}
		purchaseModelFactory.createPurchaseModels(null);
	}
	
	public void printHistoryByMonth() {
		PurchaseModelFactory purchaseModelFactory = new PurchaseModelFactory();
		PurchaseHandler purchaseHandler = new PurchaseHandler();
		String inputDate = "Geben Sie den Monat und das Jahr an, wonach gefiltert werden soll (MM:JJJJ)";
		String monthAndYear = DialogueHelper.inputDialogue(inputDate);
		String[] monthAndYearValues = monthAndYear.split(":"); 
		try {
			int month = Integer.parseInt(monthAndYearValues[0]);
			int year = Integer.parseInt(monthAndYearValues[1]);
			List<Purchase> purchase = purchaseHandler.getByMonth(month, year);
			List<PurchaseModel> purchases = purchaseModelFactory.createPurchaseModels(purchase);
			DialogueHelper.printPurchases(purchases);
		}catch(Exception e) {
			String errorMsg = "In diesem Monat wurden keine Käufe getätigt!";
			DialogueHelper.println(errorMsg);
		}
		purchaseModelFactory.createPurchaseModels(null);
	}
	
	public void printHistoryByWeek() {
		PurchaseModelFactory purchaseModelFactory = new PurchaseModelFactory();
		PurchaseHandler purchaseHandler = new PurchaseHandler();
		String input = "Geben Sie die Woche und das Jahr an, wonach gefiltert werden soll (WW:JJJJ)";
		String weekAndYear = DialogueHelper.inputDialogue(input);
		String[] weekAndYearValues = weekAndYear.split(":"); 
		try {
			int week = Integer.parseInt(weekAndYearValues[0]);
			int year = Integer.parseInt(weekAndYearValues[1]);
			List<Purchase> purchase = purchaseHandler.getByWeek(week, year);
			List<PurchaseModel> purchases = purchaseModelFactory.createPurchaseModels(purchase);
			DialogueHelper.printPurchases(purchases);
		}catch(Exception e) {
			String errorMsg = "In dieser Woche wurden keine Käufe getätigt!";
			DialogueHelper.println(errorMsg);
		}
		purchaseModelFactory.createPurchaseModels(null);
	}
	
	public void printHistoryByTimespan() throws SQLException {
		PurchaseModelFactory purchaseModelFactory = new PurchaseModelFactory();
		PurchaseHandler purchaseHandler = new PurchaseHandler();
		try {
			String startDate = "Geben Sie das Startdatum an (TT:MM:JJJJ)";
			LocalDate inputDateBegin = DateConverter.convertDate(startDate);
			String endDate = "Geben Sie das Enddatum an (TT:MM:JJJJ)";
			LocalDate inputDateEnd = DateConverter.convertDate(endDate);
			String input = "Enter Taste drücken um Historie Ihrer Einkäufe in ihrer gewählten Zeitspanne anzuzeigen";
			DialogueHelper.startDialogue(input);
			try {
				List<Purchase> purchase = purchaseHandler.getByTimestamp(inputDateBegin, inputDateEnd);
				List<PurchaseModel> purchases = purchaseModelFactory.createPurchaseModels(purchase);
				DialogueHelper.printPurchases(purchases);				
			}catch(Exception e) {
				String errorMsg = "In diesem Zeitraum wurden keine Käufe getätigt!";
				DialogueHelper.println(errorMsg);
			}
		}catch(Exception e) {
			String errorMsg = "Falsche Angaben von Datum!";
			DialogueHelper.println(errorMsg);
		}
		purchaseModelFactory.createPurchaseModels(null);
	}
}

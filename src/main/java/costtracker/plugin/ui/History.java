package costtracker.plugin.ui;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import costtracker.application.handlers.CategoryHandler;
import costtracker.application.handlers.CompanyHandler;
import costtracker.application.handlers.PurchaseHandler;
import costtracker.domain.businessobjects.Category;
import costtracker.domain.businessobjects.Company;
import costtracker.domain.businessobjects.Purchase;

public class History {
	
	public void printHistory() throws SQLException {
		PurchaseModelFactory purchaseModelFactory = new PurchaseModelFactory();
		PurchaseHandler purchaseHandler = new PurchaseHandler();
		String printPurchases = "Enter Taste drücken um Historie Ihrer Einkäufe anzuzeigen";
		DialogueHelper.printStartDialogueWith(printPurchases);
		List<Purchase> purchases = purchaseHandler.getAll();
		List<PurchaseModel> getPurchases = purchaseModelFactory.createPurchaseModels(purchases);
		PurchasePrinter.printPurchases(getPurchases);
		purchaseModelFactory.createPurchaseModels(null);
	}
	
	public void printHistoryForCompanyInTimeframe() {
		PurchaseModelFactory purchaseModelFactory = new PurchaseModelFactory();
		CompanyModelFactory companyModelFactory = new CompanyModelFactory();
		CompanyHandler companyHandler = new CompanyHandler();
		PurchaseHandler purchaseHandler = new PurchaseHandler();
		try {
			List<Company> companies = companyHandler.getEnabled();
			List<CompanyModel> getCompanies = companyModelFactory.createCompanyModels(companies);
			CompanyPrinter.printCompanies(getCompanies);
			String idDialogue = "Geben sie die ID der Firma an, nach welcher sortiert werden soll";
			int companyId = DialogueHelper.printGetIdDialogueWith(idDialogue);
			Company company = companyHandler.getById(companyId);
			String startDate = "Geben Sie das Startdatum an (TT:MM:JJJJ)";
			LocalDate inputDateBegin = DateConverter.convertDate(startDate);
			String endDate = "Geben Sie das Enddatum an (TT:MM:JJJJ)";
			LocalDate inputDateEnd = DateConverter.convertDate(endDate);
			DialogueHelper.printLine("");
			tryPrintPurchasesForCompany(purchaseHandler, company, inputDateBegin, inputDateEnd, purchaseModelFactory);
		}catch(Exception e) {
			String errorMsg = "Falsche Angaben von Datum!";
			DialogueHelper.printLine(errorMsg);
		}
		purchaseModelFactory.createPurchaseModels(null);
	}
	
	private void tryPrintPurchasesForCompany(PurchaseHandler purchaseHandler, Company company, LocalDate inputDateBegin, LocalDate inputDateEnd, PurchaseModelFactory purchaseModelFactory) {
		try {
			List<Purchase> purchases = purchaseHandler.getByCompanyByTimestamp(company, inputDateBegin, inputDateEnd);
			List<PurchaseModel> purchaseModels = purchaseModelFactory.createPurchaseModels(purchases);
			PurchasePrinter.printPurchases(purchaseModels);				
		}catch(Exception e) {
			String errorMsg = "In diesem Zeitraum wurden keine Käufe von dieser Firma getätigt!";
			DialogueHelper.printLine(errorMsg);
		}
	}
	
	public void printHistoryForCategoryInTimeframe() {
		CategoryModelFactory categoryModelFactory = new CategoryModelFactory();
		CategoryHandler categoryHandler = new CategoryHandler();
		PurchaseModelFactory purchaseModelFactory = new PurchaseModelFactory();
		PurchaseHandler purchaseHandler = new PurchaseHandler();
		try {
			List<CategoryModel> categories = categoryModelFactory.createCategoryModels(categoryHandler.getEnabled());
			CategoryPrinter.printCategories(categories);
			String idDialogue = "Geben sie die ID der Kategorie an, nach welcher sortiert werden soll";
			int categoryId = DialogueHelper.printGetIdDialogueWith(idDialogue);
			Category category = categoryHandler.getById(categoryId);
			String startDate = "Geben Sie das Startdatum an (TT:MM:JJJJ)";
			LocalDate inputDateBegin = DateConverter.convertDate(startDate);
			String endDate = "Geben Sie das Enddatum an (TT:MM:JJJJ)";
			LocalDate inputDateEnd = DateConverter.convertDate(endDate);
			DialogueHelper.printLine("");
			tryPrintPurchasesForCategory(purchaseModelFactory, purchaseHandler, category, inputDateBegin, inputDateEnd);
		}catch(Exception e) {
			String errorMsg = "Falsche Angaben von Datum!";
			DialogueHelper.printLine(errorMsg);
		}
		purchaseModelFactory.createPurchaseModels(null);
	}
	
	private void tryPrintPurchasesForCategory(PurchaseModelFactory purchaseModelFactory, PurchaseHandler purchaseHandler, Category category, LocalDate inputDateBegin, LocalDate inputDateEnd) {
		try {
			List<PurchaseModel> purchases = purchaseModelFactory.createPurchaseModels(purchaseHandler.getByCategoryByTimestamp(category, inputDateBegin, inputDateEnd));
			PurchasePrinter.printPurchases(purchases);				
		}catch(Exception e) {
			String errorMsg = "In diesem Zeitraum wurden keine Käufe aus dieser Kategorie getätigt!";
			DialogueHelper.printLine(errorMsg);
		}
	}
	
	public void printHistoryByYear() {
		PurchaseModelFactory purchaseModelFactory = new PurchaseModelFactory();
		PurchaseHandler purchaseHandler = new PurchaseHandler();
		String idDialogue = "Jahr nach dem gefiltert werden soll";
		int year = DialogueHelper.printGetIdDialogueWith(idDialogue);
		try {
			List<Purchase> purchase = purchaseHandler.getByYear(year);
			List<PurchaseModel> purchases = purchaseModelFactory.createPurchaseModels(purchase);
			PurchasePrinter.printPurchases(purchases);
		}catch(Exception e) {
			String errorMsg = "In diesem Jahr wurden keine Käufe getätigt!";
			DialogueHelper.printLine(errorMsg);
		}
		purchaseModelFactory.createPurchaseModels(null);
	}
	
	public void printHistoryByMonth() {
		PurchaseModelFactory purchaseModelFactory = new PurchaseModelFactory();
		PurchaseHandler purchaseHandler = new PurchaseHandler();
		String inputDate = "Geben Sie den Monat und das Jahr an, wonach gefiltert werden soll (MM:JJJJ)";
		String monthAndYear = DialogueHelper.printInputDialogueWith(inputDate);
		String[] monthAndYearValues = monthAndYear.split(":"); 
		try {
			int month = Integer.parseInt(monthAndYearValues[0]);
			int year = Integer.parseInt(monthAndYearValues[1]);
			List<Purchase> purchase = purchaseHandler.getByMonth(month, year);
			List<PurchaseModel> purchases = purchaseModelFactory.createPurchaseModels(purchase);
			PurchasePrinter.printPurchases(purchases);
		}catch(Exception e) {
			String errorMsg = "In diesem Monat wurden keine Käufe getätigt!";
			DialogueHelper.printLine(errorMsg);
		}
		purchaseModelFactory.createPurchaseModels(null);
	}
	
	public void printHistoryByWeek() {
		PurchaseModelFactory purchaseModelFactory = new PurchaseModelFactory();
		PurchaseHandler purchaseHandler = new PurchaseHandler();
		String input = "Geben Sie die Woche und das Jahr an, wonach gefiltert werden soll (WW:JJJJ)";
		String weekAndYear = DialogueHelper.printInputDialogueWith(input);
		String[] weekAndYearValues = weekAndYear.split(":"); 
		try {
			int week = Integer.parseInt(weekAndYearValues[0]);
			int year = Integer.parseInt(weekAndYearValues[1]);
			List<Purchase> purchase = purchaseHandler.getByWeek(week, year);
			List<PurchaseModel> purchases = purchaseModelFactory.createPurchaseModels(purchase);
			PurchasePrinter.printPurchases(purchases);
		}catch(Exception e) {
			String errorMsg = "In dieser Woche wurden keine Käufe getätigt!";
			DialogueHelper.printLine(errorMsg);
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
			DialogueHelper.printStartDialogueWith(input);
			tryPrintPurchasesByTimespan(purchaseHandler, inputDateBegin, inputDateEnd, purchaseModelFactory);
		}catch(Exception e) {
			String errorMsg = "Falsche Angaben von Datum!";
			DialogueHelper.printLine(errorMsg);
		}
		purchaseModelFactory.createPurchaseModels(null);
	}
	
	private void tryPrintPurchasesByTimespan(PurchaseHandler purchaseHandler, LocalDate inputDateBegin, LocalDate inputDateEnd, PurchaseModelFactory purchaseModelFactory) {
		try {
			List<Purchase> purchase = purchaseHandler.getByTimestamp(inputDateBegin, inputDateEnd);
			List<PurchaseModel> purchases = purchaseModelFactory.createPurchaseModels(purchase);
			PurchasePrinter.printPurchases(purchases);				
		}catch(Exception e) {
			String errorMsg = "In diesem Zeitraum wurden keine Käufe getätigt!";
			DialogueHelper.printLine(errorMsg);
		}
	}
}

package costtracker.ui;

import java.sql.SQLException;
import java.time.LocalDate;

import costtracker.buisnesslogic.CategoryHandler;
import costtracker.buisnesslogic.CompanyHandler;
import costtracker.buisnesslogic.PurchaseHandler;
import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;

public class History {

	PurchaseHandler purchaseHandler;
	CompanyHandler companyHandler;
	CategoryHandler categoryHandler;
	
	public History() {
		this.purchaseHandler = new PurchaseHandler();
		this.companyHandler = new CompanyHandler();
		this.categoryHandler = new CategoryHandler();
	}
	
	public void printHistory() throws SQLException {
		DialogueHelper.startDialogue("Enter Taste drücken um Historie Ihrer Einkäufe anzuzeigen");
		DialogueHelper.printPurchases(purchaseHandler.getAll());
	}
	
	public void printHistoryForCompanyInTimeframe() {
		try {
			DialogueHelper.printCompanies(companyHandler.getEnabled());
			int companyId = DialogueHelper.getIntDialogue("Geben sie die ID der Firma an, nach welcher sortiert werden soll");
			Company company = companyHandler.getById(companyId);
			LocalDate inputDateBegin = DateConverter.convertDate("Geben Sie das Startdatum an (TT:MM:JJJJ)");
			LocalDate inputDateEnd = DateConverter.convertDate("Geben Sie das Enddatum an (TT:MM:JJJJ)");
			DialogueHelper.println("");
			try {
				DialogueHelper.printPurchases(purchaseHandler.getByCompanyByTimestamp(company, inputDateBegin, inputDateEnd));				
			}catch(Exception e) {
				DialogueHelper.println("In diesem Zeitraum wurden keine Käufe von dieser Firma getätigt!");
			}
		}catch(Exception e) {
			DialogueHelper.println("Falsche Angaben von Datum!");
		}
	}
	
	public void printHistoryForCategoryInTimeframe() {
		try {
			DialogueHelper.printCategories(categoryHandler.getEnabled());
			int categoryId = DialogueHelper.getIntDialogue("Geben sie die ID der Kategorie an, nach welcher sortiert werden soll");
			Category category = categoryHandler.getById(categoryId);
			LocalDate inputDateBegin = DateConverter.convertDate("Geben Sie das Startdatum an (TT:MM:JJJJ)");
			LocalDate inputDateEnd = DateConverter.convertDate("Geben Sie das Enddatum an (TT:MM:JJJJ)");
			DialogueHelper.println("");
			try {
				DialogueHelper.printPurchases(purchaseHandler.getByCategoryByTimestamp(category, inputDateBegin, inputDateEnd));				
			}catch(Exception e) {
				DialogueHelper.println("In diesem Zeitraum wurden keine Käufe aus dieser Kategorie getätigt!");
			}
		}catch(Exception e) {
			DialogueHelper.println("Falsche Angaben von Datum!");
		}
	}
	
	public void printHistoryByYear() {
		int year = DialogueHelper.getIntDialogue("Jahr nach dem gefiltert werden soll");
		try {
			DialogueHelper.printPurchases(purchaseHandler.getByYear(year));
		}catch(Exception e) {
			DialogueHelper.println("In diesem Jahr wurden keine Käufe getätigt!");
		}
	}
	
	public void printHistoryByMonth() {
		String monthAndYear = DialogueHelper.inputDialogue("Geben Sie den Monat und das Jahr an, wonach gefiltert werden soll (MM:JJJJ)");
		String[] monthAndYearValues = monthAndYear.split(":"); 
		try {
			DialogueHelper.printPurchases(purchaseHandler.getByMonth(Integer.parseInt(monthAndYearValues[0]), Integer.parseInt(monthAndYearValues[1])));
		}catch(Exception e) {
			DialogueHelper.println("In diesem Monat wurden keine Käufe getätigt!");
		}
	}
	
	public void printHistoryByWeek() {
		String weekAndYear = DialogueHelper.inputDialogue("Geben Sie die Woche und das Jahr an, wonach gefiltert werden soll (WW:JJJJ)");
		String[] weekAndYearValues = weekAndYear.split(":"); 
		try {
			DialogueHelper.printPurchases(purchaseHandler.getByWeek(Integer.parseInt(weekAndYearValues[0]), Integer.parseInt(weekAndYearValues[1])));
		}catch(Exception e) {
			DialogueHelper.println("In dieser Woche wurden keine Käufe getätigt!");
		}
	}
	
	public void printHistoryByTimespan() throws SQLException {
		try {
			LocalDate inputDateBegin = DateConverter.convertDate("Geben Sie das Startdatum an (TT:MM:JJJJ)");
			LocalDate inputDateEnd = DateConverter.convertDate("Geben Sie das Enddatum an (TT:MM:JJJJ)");
			DialogueHelper.startDialogue("Enter Taste drücken um Historie Ihrer Einkäufe in ihrer gewählten Zeitspanne anzuzeigen");
			try {
				DialogueHelper.printPurchases(purchaseHandler.getByTimestamp(inputDateBegin, inputDateEnd));				
			}catch(Exception e) {
				DialogueHelper.println("In diesem Zeitraum wurden keine Käufe getätigt!");
			}
		}catch(Exception e) {
			DialogueHelper.println("Falsche Angaben von Datum!");
		}
	}
}

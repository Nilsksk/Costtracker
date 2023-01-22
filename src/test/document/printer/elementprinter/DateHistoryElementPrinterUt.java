package test.document.printer.elementprinter;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;
import costtracker.businessobjects.Purchase;
import costtracker.document.elements.DateHistoryElement;
import costtracker.document.linewriter.CSVLineWriter;
import costtracker.document.printer.elementprinter.DateHistoryElementPrinter;

class DateHistoryElementPrinterUt {

	@Test
	void TestGetElementHeader() {
		LocalDate date = LocalDate.of(2023, 1, 21);
		String header = date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
		DateHistoryElement element = new DateHistoryElement(date);
		Purchase purchase = new Purchase(0, "purchase", "description", LocalDate.of(2023, 1, 21), 0, null, null);
		element.addPurchase(purchase);
		DateHistoryElementPrinter printer = new DateHistoryElementPrinter();

		printer.registerElement(element);

		var ret = printer.getElementHeader();

		assertEquals(header, ret);
	}

	@Test
	void TestGetType() {
		LocalDate date = LocalDate.of(2023, 1, 21);
		DateHistoryElement element = new DateHistoryElement(date);
		Purchase purchase = new Purchase(0, "purchase", "description", LocalDate.of(2023, 1, 21), 0, null, null);
		element.addPurchase(purchase);
		DateHistoryElementPrinter printer = new DateHistoryElementPrinter();
		
		printer.registerElement(element);
		
		var ret = printer.getType();
		
		assertEquals("Date", ret);
	}

	@Test
	void TestGetElementDescription() {
		String description = "Date;Name;Description;Price;Category;Company;\n";
		LocalDate date = LocalDate.of(2023, 1, 21);
		DateHistoryElement element = new DateHistoryElement(date);
		Purchase purchase = new Purchase(0, "purchase", "description", LocalDate.of(2023, 1, 21), 0, null, null);
		element.addPurchase(purchase);
		DateHistoryElementPrinter printer = new DateHistoryElementPrinter();

		printer.registerLineWriter(new CSVLineWriter());
		printer.registerElement(element);

		var ret = printer.getDescription();

		assertEquals(description, ret);
	}

	@Test
	void TestGetElementLines() {
		String name = "purchase";
		String description = "description";
		LocalDate date = LocalDate.of(2023, 1, 21);
		String dateString = date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
		double price = 5.5;
		Category category = new Category(1, "cat");
		Company company = new Company(1, "comp", "");
		DateHistoryElement element = new DateHistoryElement(date);
		Purchase purchase = new Purchase(0, name, description, date, price, company, category);
		element.addPurchase(purchase);
		String line = dateString + ";" + name + ";" + description + ";" + price + ";"  +category.getName() + ";"
				+ company.getName() + ";\n";
		DateHistoryElementPrinter printer = new DateHistoryElementPrinter();

		printer.registerElement(element);
		printer.registerLineWriter(new CSVLineWriter());

		var ret = printer.getElementLines();

		assertEquals(line, ret);
	}

	@Test
	void TestGetElementLinesNoCompany() {
		String name = "purchase";
		String description = "description";
		LocalDate date = LocalDate.of(2023, 1, 21);
		String dateString = date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
		double price = 5.5;
		Category category = new Category(1, "cat");
		DateHistoryElement element = new DateHistoryElement(date);
		Purchase purchase = new Purchase(0, name, description, date, price, null, category);
		element.addPurchase(purchase);
		String line = dateString + ";" + name + ";" + description + ";" + price  +  ";"
				+ category.getName() + ";;\n";
		DateHistoryElementPrinter printer = new DateHistoryElementPrinter();

		printer.registerElement(element);
		printer.registerLineWriter(new CSVLineWriter());

		var ret = printer.getElementLines();

		assertEquals(line, ret);
	}

}

package costtracker.ut.document.printer.elementprinter;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;
import costtracker.businessobjects.IncorrectEntryException;
import costtracker.businessobjects.Purchase;
import costtracker.document.elements.DateHistoryElement;
import costtracker.document.linewriter.CSVLineWriter;
import costtracker.document.printer.elementprinter.DateHistoryElementPrinter;

class DateHistoryElementPrinterUnitTest {

	@Test
	void TestGetElementHeader() throws IncorrectEntryException {
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		LocalDate date = LocalDate.of(2023, 1, 21);
		String header = date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear() + ";1.0;\n";
		DateHistoryElement element = new DateHistoryElement(date);
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues("purchase", date, 1.0)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.build();
		element.addPurchase(purchase);
		DateHistoryElementPrinter printer = new DateHistoryElementPrinter();

		printer.registerElement(element);
		printer.registerLineWriter(new CSVLineWriter());

		var ret = printer.getElementHeader();

		assertEquals(header, ret);
	}

	@Test
	void TestGetType() throws IncorrectEntryException {
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		LocalDate date = LocalDate.of(2023, 1, 21);
		DateHistoryElement element = new DateHistoryElement(date);
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), 1.0)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.build();
		element.addPurchase(purchase);
		DateHistoryElementPrinter printer = new DateHistoryElementPrinter();
		
		printer.registerElement(element);
		
		var ret = printer.getType();
		
		assertEquals("Date", ret);
	}

	@Test
	void TestGetElementDescription() throws IncorrectEntryException {
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		String description = "Date;Name;Description;Price;Category;Company;\n";
		LocalDate date = LocalDate.of(2023, 1, 21);
		DateHistoryElement element = new DateHistoryElement(date);
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), 1.0)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.build();
		element.addPurchase(purchase);
		DateHistoryElementPrinter printer = new DateHistoryElementPrinter();

		printer.registerLineWriter(new CSVLineWriter());
		printer.registerElement(element);

		var ret = printer.getDescription();

		assertEquals(description, ret);
	}

	@Test
	void TestGetElementLines() throws IncorrectEntryException {
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		String name = "purchase";
		String description = "description";
		LocalDate date = LocalDate.of(2023, 1, 21);
		String dateString = date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
		double price = 5.5;
		Company company = Company.CompanyBuilder
				.withName("comp")
				.withId(1)
				.build();
		DateHistoryElement element = new DateHistoryElement(date);
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues(name, date, price)
				.withId(1)
				.withCategory(category)
				.withCompany(company)
				.withDescription(description)
				.build();
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
	void TestGetElementLinesNoCompany() throws IncorrectEntryException {
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		String name = "purchase";
		String description = "description";
		LocalDate date = LocalDate.of(2023, 1, 21);
		String dateString = date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
		double price = 5.5;
		DateHistoryElement element = new DateHistoryElement(date);
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues(name, date, price)
				.withId(1)
				.withCategory(category)
				.withDescription(description)
				.build();
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

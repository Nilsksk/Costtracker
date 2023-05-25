package costtracker.ut.document.printer.elementprinter;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;
import costtracker.businessobjects.IncorrectEntryException;
import costtracker.businessobjects.Purchase;
import costtracker.document.elements.CategoryHistoryElement;
import costtracker.document.linewriter.CSVLineWriter;
import costtracker.document.printer.elementprinter.CategoryHistoryElementPrinter;

class CategoryHistoryElementPrinterUnitTest {

	@Test
	void TestGetElementHeader() throws IncorrectEntryException {
		String header = "category";
		Category category = Category.CategoryBuilder
				.withName(header)
				.withId(1)
				.build();
		CategoryHistoryElement element = new CategoryHistoryElement(category);
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), 1.0)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.build();
		header += ";1.0;\n";
		element.addPurchase(purchase);
		CategoryHistoryElementPrinter printer = new CategoryHistoryElementPrinter();

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
		CategoryHistoryElement element = new CategoryHistoryElement(category);
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), 1.0)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.build();
		element.addPurchase(purchase);
		CategoryHistoryElementPrinter printer = new CategoryHistoryElementPrinter();
		
		printer.registerElement(element);

		var ret = printer.getType();
		
		assertEquals("Category", ret);
	}

	@Test
	void TestGetElementDescription() throws IncorrectEntryException {
		String description = "Category;Name;Description;Price;Date;Company;\n";
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		CategoryHistoryElement element = new CategoryHistoryElement(category);
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), 1.0)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.build();
		element.addPurchase(purchase);
		CategoryHistoryElementPrinter printer = new CategoryHistoryElementPrinter();

		printer.registerElement(element);
		printer.registerLineWriter(new CSVLineWriter());

		var ret = printer.getDescription();

		assertEquals(description, ret);
	}

	@Test
	void TestGetElementLines() throws IncorrectEntryException {
		String name = "purchase";
		String description = "description";
		LocalDate date = LocalDate.of(2023, 1, 21);
		double price = 5.5;
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		Company company = Company.CompanyBuilder
				.withName("comp")
				.withId(1)
				.build();
		CategoryHistoryElement element = new CategoryHistoryElement(category);
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues(name, date, price)
				.withId(1)
				.withCategory(category)
				.withCompany(company)
				.withDescription(description)
				.build();
		element.addPurchase(purchase);
		String line = category.getName() + ";" + name + ";" + description + ";" + price + ";" + purchase.getDateString() + ";"
				+ company.getName() + ";\n";
		CategoryHistoryElementPrinter printer = new CategoryHistoryElementPrinter();

		printer.registerElement(element);
		printer.registerLineWriter(new CSVLineWriter());

		var ret = printer.getElementLines();

		assertEquals(line, ret);
	}
	
	@Test
	void TestGetElementLinesNoCompany() throws IncorrectEntryException {
		String name = "purchase";
		String description = "description";
		LocalDate date = LocalDate.of(2023, 1, 21);
		double price = 5.5;
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		CategoryHistoryElement element = new CategoryHistoryElement(category);
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues(name, date, price)
				.withId(1)
				.withCategory(category)
				.withDescription(description)
				.build();
		element.addPurchase(purchase);
		String line = category.getName() + ";" + name + ";" + description + ";" + price + ";" + purchase.getDateString() + ";;\n";
		CategoryHistoryElementPrinter printer = new CategoryHistoryElementPrinter();
		
		printer.registerElement(element);
		printer.registerLineWriter(new CSVLineWriter());
		
		var ret = printer.getElementLines();
		
		assertEquals(line, ret);
	}

}

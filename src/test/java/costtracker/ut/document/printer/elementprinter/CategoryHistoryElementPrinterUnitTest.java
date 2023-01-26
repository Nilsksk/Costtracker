package costtracker.ut.document.printer.elementprinter;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;
import costtracker.businessobjects.Purchase;
import costtracker.document.elements.CategoryHistoryElement;
import costtracker.document.linewriter.CSVLineWriter;
import costtracker.document.printer.elementprinter.CategoryHistoryElementPrinter;

class CategoryHistoryElementPrinterUnitTest {

	@Test
	void TestGetElementHeader() {
		String header = "category";
		Category category = new Category(1, header);
		CategoryHistoryElement element = new CategoryHistoryElement(category);
		Purchase purchase = new Purchase(0, "purchase", "description", LocalDate.of(2023, 1, 21), 0, null, category);
		header += ";0.0;\n";
		element.addPurchase(purchase);
		CategoryHistoryElementPrinter printer = new CategoryHistoryElementPrinter();

		printer.registerElement(element);
		printer.registerLineWriter(new CSVLineWriter());
		var ret = printer.getElementHeader();

		assertEquals(header, ret);
	}

	@Test
	void TestGetType() {
		Category category = new Category(1, "");
		CategoryHistoryElement element = new CategoryHistoryElement(category);
		Purchase purchase = new Purchase(0, "purchase", "description", LocalDate.of(2023, 1, 21), 0, null, category);
		element.addPurchase(purchase);
		CategoryHistoryElementPrinter printer = new CategoryHistoryElementPrinter();
		
		printer.registerElement(element);

		var ret = printer.getType();
		
		assertEquals("Category", ret);
	}

	@Test
	void TestGetElementDescription() {
		String description = "Category;Name;Description;Price;Date;Company;\n";
		Category category = new Category(1, "");
		CategoryHistoryElement element = new CategoryHistoryElement(category);
		Purchase purchase = new Purchase(0, "purchase", "description", LocalDate.of(2023, 1, 21), 0, null, category);
		element.addPurchase(purchase);
		CategoryHistoryElementPrinter printer = new CategoryHistoryElementPrinter();

		printer.registerElement(element);
		printer.registerLineWriter(new CSVLineWriter());

		var ret = printer.getDescription();

		assertEquals(description, ret);
	}

	@Test
	void TestGetElementLines() {
		String name = "purchase";
		String description = "description";
		LocalDate date = LocalDate.of(2023, 1, 21);
		double price = 5.5;
		Category category = new Category(1, "cat");
		Company company = new Company(1, "comp", "");
		CategoryHistoryElement element = new CategoryHistoryElement(category);
		Purchase purchase = new Purchase(0, name, description, date, price, company, category);
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
	void TestGetElementLinesNoCompany() {
		String name = "purchase";
		String description = "description";
		LocalDate date = LocalDate.of(2023, 1, 21);
		double price = 5.5;
		Category category = new Category(1, "cat");
		CategoryHistoryElement element = new CategoryHistoryElement(category);
		Purchase purchase = new Purchase(0, name, description, date, price, null, category);
		element.addPurchase(purchase);
		String line = category.getName() + ";" + name + ";" + description + ";" + price + ";" + purchase.getDateString() + ";;\n";
		CategoryHistoryElementPrinter printer = new CategoryHistoryElementPrinter();
		
		printer.registerElement(element);
		printer.registerLineWriter(new CSVLineWriter());
		
		var ret = printer.getElementLines();
		
		assertEquals(line, ret);
	}

}

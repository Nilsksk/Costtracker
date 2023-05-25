package costtracker.ut.document.printer.elementprinter;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;
import costtracker.businessobjects.IncorrectEntryException;
import costtracker.businessobjects.Purchase;
import costtracker.document.elements.CompanyHistoryElement;
import costtracker.document.linewriter.CSVLineWriter;
import costtracker.document.printer.elementprinter.CompanyHistoryElementPrinter;

class CompanyHistoryElementPrinterUnitTest {

	@Test
	void TestGetElementHeader() throws IncorrectEntryException {
		String header = "company";
		Company company = Company.CompanyBuilder
				.withName(header)
				.withId(1)
				.build();
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		CompanyHistoryElement element = new CompanyHistoryElement(company);
		 header += ";1.0;\n";
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), 1.0)
				.withId(1)
				.withCategory(category)
				.withCompany(company)
				.withDescription("description")
				.build();
		element.addPurchase(purchase);
		CompanyHistoryElementPrinter printer = new CompanyHistoryElementPrinter();

		printer.registerElement(element);
		printer.registerLineWriter(new CSVLineWriter());
		
		var ret = printer.getElementHeader();

		assertEquals(header, ret);
	}

	@Test
	void TestGetType() throws IncorrectEntryException {
		String header = "category";
		Company company = Company.CompanyBuilder
				.withName(header)
				.withId(1)
				.build();
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		CompanyHistoryElement element = new CompanyHistoryElement(company);
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), 1.0)
				.withId(1)
				.withCategory(category)
				.withCompany(company)
				.withDescription("description")
				.build();
		element.addPurchase(purchase);
		CompanyHistoryElementPrinter printer = new CompanyHistoryElementPrinter();
		
		printer.registerElement(element);
		
		var ret = printer.getType();
		
		assertEquals("Company", ret);
	}

	@Test
	void TestGetElementDescription() throws IncorrectEntryException {
		String description = "Company;Name;Description;Price;Date;Category;\n";
		Company company = Company.CompanyBuilder
				.withName("comp")
				.withId(1)
				.build();
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		CompanyHistoryElement element = new CompanyHistoryElement(company);
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), 1.0)
				.withId(1)
				.withCategory(category)
				.withCompany(company)
				.withDescription("description")
				.build();
		element.addPurchase(purchase);
		CompanyHistoryElementPrinter printer = new CompanyHistoryElementPrinter();

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
		CompanyHistoryElement element = new CompanyHistoryElement(company);
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues(name, date, price)
				.withId(1)
				.withCategory(category)
				.withCompany(company)
				.withDescription(description)
				.build();
		element.addPurchase(purchase);
		String line = company.getName() + ";" + name + ";" + description + ";" + price + ";" + purchase.getDateString() + ";"
				+ category.getName() + ";\n";
		CompanyHistoryElementPrinter printer = new CompanyHistoryElementPrinter();
		printer.registerLineWriter(new CSVLineWriter());

		printer.registerElement(element);

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
		Company company = Company.CompanyBuilder
				.withName("comp")
				.withId(1)
				.build();
		CompanyHistoryElement element = new CompanyHistoryElement(company);
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues(name, date, price)
				.withId(1)
				.withCategory(category)
				.withCompany(company)
				.withDescription(description)
				.build();
		element.addPurchase(purchase);
		String line =  company.getName() + ";" + name + ";" + description + ";" + price + ";" + purchase.getDateString() + ";"
				+ category.getName() + ";\n";
		CompanyHistoryElementPrinter printer = new CompanyHistoryElementPrinter();
		
		printer.registerElement(element);
		printer.registerLineWriter(new CSVLineWriter());
		
		var ret = printer.getElementLines();
		
		assertEquals(line, ret);
	}
	
	

}

package test.document.printer.elementprinter;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;
import costtracker.businessobjects.Purchase;
import costtracker.document.elements.CompanyHistoryElement;
import costtracker.document.linewriter.CSVLineWriter;
import costtracker.document.printer.elementprinter.CompanyHistoryElementPrinter;

class CompanyHistoryElementPrinterUt {

	@Test
	void TestGetElementHeader() {
		String header = "category";
		Company company = new Company(1, header, "loc");
		CompanyHistoryElement element = new CompanyHistoryElement(company);
		Purchase purchase = new Purchase(0, "purchase", "description", LocalDate.of(2023, 1, 21), 0, company, null);
		element.addPurchase(purchase);
		CompanyHistoryElementPrinter printer = new CompanyHistoryElementPrinter();

		printer.registerElement(element);

		var ret = printer.getElementHeader();

		assertEquals(header, ret);
	}

	@Test
	void TestGetType() {
		String header = "category";
		Company company = new Company(1, header, "loc");
		CompanyHistoryElement element = new CompanyHistoryElement(company);
		Purchase purchase = new Purchase(0, "purchase", "description", LocalDate.of(2023, 1, 21), 0, company, null);
		element.addPurchase(purchase);
		CompanyHistoryElementPrinter printer = new CompanyHistoryElementPrinter();
		
		printer.registerElement(element);
		
		var ret = printer.getType();
		
		assertEquals("Company", ret);
	}

	@Test
	void TestGetElementDescription() {
		String description = "Company;Name;Description;Price;Date;Category;\n";
		Company company = new Company(1, "com", "loc");
		CompanyHistoryElement element = new CompanyHistoryElement(company);
		Purchase purchase = new Purchase(0, "purchase", "description", LocalDate.of(2023, 1, 21), 0, company, null);
		element.addPurchase(purchase);
		CompanyHistoryElementPrinter printer = new CompanyHistoryElementPrinter();

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
		CompanyHistoryElement element = new CompanyHistoryElement(company);
		Purchase purchase = new Purchase(0, name, description, date, price, company, category);
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
	void TestGetElementLinesNoCompany() {
		String name = "purchase";
		String description = "description";
		LocalDate date = LocalDate.of(2023, 1, 21);
		double price = 5.5;
		Category category = new Category(1, "cat");
		Company company = new Company(0, "", "");
		CompanyHistoryElement element = new CompanyHistoryElement(company);
		Purchase purchase = new Purchase(0, name, description, date, price, null, category);
		element.addPurchase(purchase);
		String line =  ";" + name + ";" + description + ";" + price + ";" + purchase.getDateString() + ";"
				+ category.getName() + ";\n";
		CompanyHistoryElementPrinter printer = new CompanyHistoryElementPrinter();
		
		printer.registerElement(element);
		printer.registerLineWriter(new CSVLineWriter());
		
		var ret = printer.getElementLines();
		
		assertEquals(line, ret);
	}
	
	

}

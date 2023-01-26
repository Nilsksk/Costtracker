package costtracker.ut.document.printer.csv;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;
import costtracker.businessobjects.Purchase;
import costtracker.document.elements.CategoryHistoryElement;
import costtracker.document.elements.CompanyHistoryElement;
import costtracker.document.elements.DateHistoryElement;
import costtracker.document.elements.HistoryElement;
import costtracker.document.printer.CSVPrinter;
import costtracker.document.printer.elementprinter.CategoryHistoryElementPrinter;
import costtracker.document.printer.elementprinter.CompanyHistoryElementPrinter;
import costtracker.document.printer.elementprinter.DateHistoryElementPrinter;
import costtracker.document.printer.elementprinter.ElementPrinter;

class CSVPrinterUnitTest {

	@BeforeEach
	void setUpBefore() {
		File file = new File("Test.csv");
		if (file.exists())
			file.delete();
	}

	@Test
	void TestPrint_Date() {
		LocalDate dateStart = LocalDate.of(2022, 1, 1);
		LocalDate dateEnd = LocalDate.of(2022, 1, 21);
		Category category = new Category(1, "");
		HistoryElement element = new DateHistoryElement(dateEnd);
		Purchase purchase = new Purchase(0, "purchase", "description", dateEnd, 0, null, category);
		element.addPurchase(purchase);
		ElementPrinter elementPrinter = new DateHistoryElementPrinter();
		CSVPrinter printer = new CSVPrinter(elementPrinter);
		printer.setPath("Test.csv");
		printer.setTimespan(dateStart, dateEnd);

		var list = new ArrayList<HistoryElement>();
		list.add(element);
		var ret = printer.print(list);

		assertEquals(true, ret);
	}

	@Test
	void TestPrint_Category() {
		LocalDate dateStart = LocalDate.of(2022, 1, 1);
		LocalDate dateEnd = LocalDate.of(2022, 1, 21);
		Category category = new Category(1, "cat");
		HistoryElement element = new CategoryHistoryElement(category);
		Purchase purchase = new Purchase(0, "purchase", "description", dateEnd, 0, null, category);
		element.addPurchase(purchase);
		ElementPrinter elementPrinter = new CategoryHistoryElementPrinter();
		CSVPrinter printer = new CSVPrinter(elementPrinter);
		printer.setPath("Test.csv");
		printer.setTimespan(dateStart, dateEnd);

		var list = new ArrayList<HistoryElement>();
		list.add(element);
		var ret = printer.print(list);

		assertEquals(true, ret);
	}

	@Test
	void TestPrint_Company() {
		LocalDate dateStart = LocalDate.of(2022, 1, 1);
		LocalDate dateEnd = LocalDate.of(2022, 1, 21);
		Category category = new Category(1, "cat");
		Company company = new Company(1, "comp", "loc");
		HistoryElement element = new CompanyHistoryElement(company);
		Purchase purchase = new Purchase(0, "purchase", "description", dateEnd, 0, null, category);
		element.addPurchase(purchase);
		ElementPrinter elementPrinter = new CompanyHistoryElementPrinter();
		CSVPrinter printer = new CSVPrinter(elementPrinter);
		printer.setPath("Test.csv");
		printer.setTimespan(dateStart, dateEnd);

		var list = new ArrayList<HistoryElement>();
		list.add(element);
		var ret = printer.print(list);

		assertEquals(true, ret);
	}

	@Test
	void TestPrint_FileExists() {
		String path = "Test.csv";
		LocalDate dateS = LocalDate.of(2023, 1, 1);
		LocalDate date = LocalDate.of(2023, 1, 21);
		Category category = new Category(1, "");
		HistoryElement element = new DateHistoryElement(date);
		Purchase purchase = new Purchase(0, "purchase", "description", date, 0, null, category);
		element.addPurchase(purchase);
		ElementPrinter elementPrinter = new DateHistoryElementPrinter();
		CSVPrinter printer = new CSVPrinter(elementPrinter);
		printer.setPath(path);
		printer.setTimespan(dateS, date);

		var list = new ArrayList<HistoryElement>();
		list.add(element);
		printer.print(list);

		File file = new File(path);

		assertTrue(file.exists());
	}

	@Test
	void TestPrint_FileLines_OnePurchase() throws IOException {
		String path = "Test.csv";
		LocalDate dateS = LocalDate.of(2023, 1, 1);
		LocalDate date = LocalDate.of(2023, 1, 21);
		Category category = new Category(1, "");
		HistoryElement element = new DateHistoryElement(date);
		Purchase purchase = new Purchase(0, "purchase", "description", date, 0, null, category);
		element.addPurchase(purchase);
		ElementPrinter elementPrinter = new DateHistoryElementPrinter();
		CSVPrinter printer = new CSVPrinter(elementPrinter);
		printer.setPath(path);
		printer.setTimespan(dateS, date);

		var list = new ArrayList<HistoryElement>();
		list.add(element);
		printer.print(list);

		long lineCount;
		try (Stream<String> stream = Files.lines(Path.of(path), StandardCharsets.UTF_8)) {
		  lineCount = stream.count();
		}
		
		assertEquals(4, lineCount);
	}

	@Test
	void TestPrint_FileLines_TwoPurchase() throws IOException {
		String path = "Test.csv";
		LocalDate dateS = LocalDate.of(2023, 1, 1);
		LocalDate date = LocalDate.of(2023, 1, 21);
		Category category = new Category(1, "");
		HistoryElement element = new DateHistoryElement(date);
		Purchase purchase = new Purchase(0, "purchase", "description", date, 0, null, category);
		Purchase purchase2 = new Purchase(0, "purchase", "description", date, 0, null, category);
		element.addPurchase(purchase);
		element.addPurchase(purchase2);
		ElementPrinter elementPrinter = new DateHistoryElementPrinter();
		CSVPrinter printer = new CSVPrinter(elementPrinter);
		printer.setPath(path);
		printer.setTimespan(dateS, date);
		
		var list = new ArrayList<HistoryElement>();
		list.add(element);
		printer.print(list);
		
		long lineCount;
		try (Stream<String> stream = Files.lines(Path.of(path), StandardCharsets.UTF_8)) {
			lineCount = stream.count();
		}
		
		assertEquals(5, lineCount);
	}

	@Test
	void TestPrint_FileLines_TwoElements() throws IOException {
		String path = "Test.csv";
		LocalDate dateS = LocalDate.of(2023, 1, 1);
		LocalDate date = LocalDate.of(2023, 1, 21);
		Category category = new Category(1, "");
		HistoryElement element = new DateHistoryElement(date);
		HistoryElement element2 = new DateHistoryElement(date);
		Purchase purchase = new Purchase(0, "purchase", "description", date, 0, null, category);
		Purchase purchase2 = new Purchase(0, "purchase", "description", date, 0, null, category);
		element.addPurchase(purchase);
		element.addPurchase(purchase2);
		element2.addPurchase(purchase);
		ElementPrinter elementPrinter = new DateHistoryElementPrinter();
		CSVPrinter printer = new CSVPrinter(elementPrinter);
		printer.setPath(path);
		printer.setTimespan(dateS, date);
		
		var list = new ArrayList<HistoryElement>();
		list.add(element);
		list.add(element2);
		printer.print(list);
		
		long lineCount;
		try (Stream<String> stream = Files.lines(Path.of(path), StandardCharsets.UTF_8)) {
			lineCount = stream.count();
		}
		
		assertEquals(8, lineCount);
	}

	@Test
	void TestPrint_FileLines_TwoElements_TwoPurchases() throws IOException {
		String path = "Test.csv";
		LocalDate dateS = LocalDate.of(2023, 1, 1);
		LocalDate date = LocalDate.of(2023, 1, 21);
		Category category = new Category(1, "");
		HistoryElement element = new DateHistoryElement(date);
		HistoryElement element2 = new DateHistoryElement(date);
		Purchase purchase = new Purchase(0, "purchase", "description", date, 0, null, category);
		Purchase purchase2 = new Purchase(0, "purchase", "description", date.plusDays(1), 0, null, category);
		element.addPurchase(purchase);
		element.addPurchase(purchase2);
		element2.addPurchase(purchase);
		element2.addPurchase(purchase2);
		ElementPrinter elementPrinter = new DateHistoryElementPrinter();
		CSVPrinter printer = new CSVPrinter(elementPrinter);
		printer.setPath(path);
		printer.setTimespan(dateS, date);
		
		var list = new ArrayList<HistoryElement>();
		list.add(element);
		list.add(element2);
		printer.print(list);
		
		long lineCount;
		try (Stream<String> stream = Files.lines(Path.of(path), StandardCharsets.UTF_8)) {
			lineCount = stream.count();
		}
		
		assertEquals(9, lineCount);
	}

}

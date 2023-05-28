package costtracker.ut.document.printer.csv;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;
import costtracker.businessobjects.IncorrectEntryException;
import costtracker.businessobjects.Purchase;
import costtracker.document.elements.HistoryDocumentHeader;
import costtracker.document.elements.HistoryElement;
import costtracker.document.elements.HistoryElementsCreator;
import costtracker.document.elements.PurchaseEntry;
import costtracker.document.printer.CSVDocumentPrinter;
import costtracker.document.type.ElementType;

class CSVPrinterUnitTest {

	@BeforeEach
	void setUpBefore() {

	}

	@Test
	void TestPrintElement() throws IncorrectEntryException {
		LocalDate date = LocalDate.of(2022, 1, 21);
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues("purchase", date, 1.0)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.build();
		List<Purchase> purchases = new ArrayList<Purchase>();
		purchases.add(purchase);
		HistoryElement element = new HistoryElementsCreator(purchases, ElementType.Date).createHistoryElements().get(0);
		CSVDocumentPrinter printer = new CSVDocumentPrinter();
		var firstLine = "Date:;" + element.getHeader() + ";" + "Gesamt:" + ";" + element.getTotal() +";\n";
		var secondLine = "Name;Preis;Datum;Kategorie;Firma;Beschreibung;\n";
		
		var sb = printer.printElement(element);

		assertEquals(firstLine + secondLine, sb.toString());
	}

	@Test
	void TestPrintEntryNoCompany() throws IncorrectEntryException {
		LocalDate date = LocalDate.of(2022, 1, 21);
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues("purchase", date, 1.0)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.build();
		
		PurchaseEntry entry = new PurchaseEntry(purchase);
		CSVDocumentPrinter printer = new CSVDocumentPrinter();
		
		var line = entry.getName() + ";" + entry.getPrice() + ";" + entry.getDate() + ";" + entry.getCategory() + ";" + entry.getCompany() + ";" + entry.getDescription() + ";\n";
		
		var sb = printer.printEntry(entry);

		assertEquals(line, sb.toString());
	}

	@Test
	void TestPrintEntryNoDescription() throws IncorrectEntryException {
		LocalDate date = LocalDate.of(2022, 1, 21);
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		Company company = Company.CompanyBuilder
				.withName("comp")
				.withId(1)
				.build();
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues("purchase", date, 1.0)
				.withId(1)
				.withCategory(category)
				.withCompany(company)
				.build();
		
		PurchaseEntry entry = new PurchaseEntry(purchase);
		CSVDocumentPrinter printer = new CSVDocumentPrinter();
		
		var line = entry.getName() + ";" + entry.getPrice() + ";" + entry.getDate() + ";" + entry.getCategory() + ";" + entry.getCompany() + ";" + entry.getDescription() + ";\n";
		
		var sb = printer.printEntry(entry);
		
		assertEquals(line, sb.toString());
	}
	@Test
	void TestPrintEntryNoCompanyNoDescription() throws IncorrectEntryException {
		LocalDate date = LocalDate.of(2022, 1, 21);
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues("purchase", date, 1.0)
				.withId(1)
				.withCategory(category)
				.build();
		
		PurchaseEntry entry = new PurchaseEntry(purchase);
		CSVDocumentPrinter printer = new CSVDocumentPrinter();
		
		var line = entry.getName() + ";" + entry.getPrice() + ";" + entry.getDate() + ";" + entry.getCategory() + ";" + entry.getCompany() + ";" + entry.getDescription() + ";\n";
		
		var sb = printer.printEntry(entry);
		
		assertEquals(line, sb.toString());
	}

	@Test
	void TestPrintHeader() throws IncorrectEntryException {
		LocalDate dateStart = LocalDate.of(2022, 1, 1);
		LocalDate dateEnd = LocalDate.of(2022, 1, 21);
		String description = "Data for";
		
		HistoryDocumentHeader header = new HistoryDocumentHeader(dateStart, dateEnd, description);
		CSVDocumentPrinter printer = new CSVDocumentPrinter();

		String line = description + ";Startdatum:;" + dateStart.toString() + ";Enddatum:;" + dateEnd.toString() + ";\n";
		
		var sb = printer.printHeader(header);

		assertEquals(line, sb.toString());
	}
//
//	@Test
//	void TestPrint_FileExists() throws IncorrectEntryException {
//		String path = "Test.csv";
//		LocalDate dateS = LocalDate.of(2023, 1, 1);
//		LocalDate date = LocalDate.of(2023, 1, 21);
//		Category category = Category.CategoryBuilder
//				.withName("cat")
//				.withId(1)
//				.build();
//		HistoryElement element = new DateHistoryElement(date);
//		Purchase purchase = Purchase.PurchaseBuilder
//				.withValues("purchase", date, 1.0)
//				.withId(1)
//				.withCategory(category)
//				.withDescription("description")
//				.build();
//		element.addPurchase(purchase);
//		ElementPrinter elementPrinter = new DateHistoryElementPrinter();
//		CSVPrinter printer = new CSVPrinter(elementPrinter);
//		printer.setPath(path);
//		printer.setTimespan(dateS, date);
//
//		var list = new ArrayList<HistoryElement>();
//		list.add(element);
//		printer.print(list);
//
//		File file = new File(path);
//
//		assertTrue(file.exists());
//	}
//
//	@Test
//	void TestPrint_FileLines_OnePurchase() throws IOException, IncorrectEntryException {
//		String path = "Test.csv";
//		LocalDate dateS = LocalDate.of(2023, 1, 1);
//		LocalDate date = LocalDate.of(2023, 1, 21);
//		Category category = Category.CategoryBuilder
//				.withName("cat")
//				.withId(1)
//				.build();
//		HistoryElement element = new DateHistoryElement(date);
//		Purchase purchase = Purchase.PurchaseBuilder
//				.withValues("purchase", date, 1.0)
//				.withId(1)
//				.withCategory(category)
//				.withDescription("description")
//				.build();
//		element.addPurchase(purchase);
//		ElementPrinter elementPrinter = new DateHistoryElementPrinter();
//		CSVPrinter printer = new CSVPrinter(elementPrinter);
//		printer.setPath(path);
//		printer.setTimespan(dateS, date);
//
//		var list = new ArrayList<HistoryElement>();
//		list.add(element);
//		printer.print(list);
//
//		long lineCount;
//		try (Stream<String> stream = Files.lines(Path.of(path), StandardCharsets.UTF_8)) {
//		  lineCount = stream.count();
//		}
//		
//		assertEquals(4, lineCount);
//	}
//
//	@Test
//	void TestPrint_FileLines_TwoPurchase() throws IOException, IncorrectEntryException {
//		String path = "Test.csv";
//		LocalDate dateS = LocalDate.of(2023, 1, 1);
//		LocalDate date = LocalDate.of(2023, 1, 21);
//		Category category = Category.CategoryBuilder
//				.withName("cat")
//				.withId(1)
//				.build();
//		HistoryElement element = new DateHistoryElement(date);
//		Purchase purchase = Purchase.PurchaseBuilder
//				.withValues("purchase", date, 1.0)
//				.withId(1)
//				.withCategory(category)
//				.withDescription("description")
//				.build();
//		Purchase purchase2 =Purchase.PurchaseBuilder
//				.withValues("purchase", date, 1.0)
//				.withId(1)
//				.withCategory(category)
//				.withDescription("description")
//				.build(); 
//		element.addPurchase(purchase);
//		element.addPurchase(purchase2);
//		ElementPrinter elementPrinter = new DateHistoryElementPrinter();
//		CSVPrinter printer = new CSVPrinter(elementPrinter);
//		printer.setPath(path);
//		printer.setTimespan(dateS, date);
//		
//		var list = new ArrayList<HistoryElement>();
//		list.add(element);
//		printer.print(list);
//		
//		long lineCount;
//		try (Stream<String> stream = Files.lines(Path.of(path), StandardCharsets.UTF_8)) {
//			lineCount = stream.count();
//		}
//		
//		assertEquals(5, lineCount);
//	}
//
//	@Test
//	void TestPrint_FileLines_TwoElements() throws IOException, IncorrectEntryException {
//		String path = "Test.csv";
//		LocalDate dateS = LocalDate.of(2023, 1, 1);
//		LocalDate date = LocalDate.of(2023, 1, 21);
//		Category category = Category.CategoryBuilder
//				.withName("cat")
//				.withId(1)
//				.build();
//		HistoryElement element = new DateHistoryElement(date);
//		HistoryElement element2 = new DateHistoryElement(date);
//		Purchase purchase = Purchase.PurchaseBuilder
//				.withValues("purchase", date, 1.0)
//				.withId(1)
//				.withCategory(category)
//				.withDescription("description")
//				.build();
//		Purchase purchase2 = Purchase.PurchaseBuilder
//				.withValues("purchase", date, 1.0)
//				.withId(1)
//				.withCategory(category)
//				.withDescription("description")
//				.build();
//		element.addPurchase(purchase);
//		element.addPurchase(purchase2);
//		element2.addPurchase(purchase);
//		ElementPrinter elementPrinter = new DateHistoryElementPrinter();
//		CSVPrinter printer = new CSVPrinter(elementPrinter);
//		printer.setPath(path);
//		printer.setTimespan(dateS, date);
//		
//		var list = new ArrayList<HistoryElement>();
//		list.add(element);
//		list.add(element2);
//		printer.print(list);
//		
//		long lineCount;
//		try (Stream<String> stream = Files.lines(Path.of(path), StandardCharsets.UTF_8)) {
//			lineCount = stream.count();
//		}
//		
//		assertEquals(8, lineCount);
//	}
//
//	@Test
//	void TestPrint_FileLines_TwoElements_TwoPurchases() throws IOException, IncorrectEntryException {
//		String path = "Test.csv";
//		LocalDate dateS = LocalDate.of(2023, 1, 1);
//		LocalDate date = LocalDate.of(2023, 1, 21);
//		Category category = Category.CategoryBuilder
//				.withName("cat")
//				.withId(1)
//				.build();
//		HistoryElement element = new DateHistoryElement(date);
//		HistoryElement element2 = new DateHistoryElement(date);
//		Purchase purchase = Purchase.PurchaseBuilder
//				.withValues("purchase", date, 1.0)
//				.withId(1)
//				.withCategory(category)
//				.withDescription("description")
//				.build();
//		Purchase purchase2 = 
//				Purchase.PurchaseBuilder
//				.withValues("purchase", date.plusDays(1), 1.0)
//				.withId(1)
//				.withCategory(category)
//				.withDescription("description")
//				.build();
//		element.addPurchase(purchase);
//		element.addPurchase(purchase2);
//		element2.addPurchase(purchase);
//		element2.addPurchase(purchase2);
//		ElementPrinter elementPrinter = new DateHistoryElementPrinter();
//		CSVPrinter printer = new CSVPrinter(elementPrinter);
//		printer.setPath(path);
//		printer.setTimespan(dateS, date);
//		
//		var list = new ArrayList<HistoryElement>();
//		list.add(element);
//		list.add(element2);
//		printer.print(list);
//		
//		long lineCount;
//		try (Stream<String> stream = Files.lines(Path.of(path), StandardCharsets.UTF_8)) {
//			lineCount = stream.count();
//		}
//		
//		assertEquals(9, lineCount);
//	}

}

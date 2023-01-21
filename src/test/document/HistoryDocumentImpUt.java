package test.document;

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
import costtracker.document.HistoryDocumentImp;
import costtracker.document.elements.CategoryHistoryElement;
import costtracker.document.elements.CompanyHistoryElement;
import costtracker.document.elements.DateHistoryElement;
import costtracker.document.elements.HistoryElement;
import costtracker.document.printer.CSVPrinter;
import costtracker.document.printer.elementprinter.DateHistoryElementPrinter;
import costtracker.document.printer.elementprinter.ElementPrinter;
import costtracker.document.type.DocumentType;
import costtracker.document.type.ElementType;

class HistoryDocumentImpUt {

	@BeforeEach
	void setUpBefore() {
		File file = new File("Test.csv");
		if (file.exists())
			file.delete();
	}
	
	@Test
	void TestPrint_Date() throws IOException {
		String path = "Test.csv";
		LocalDate dateS = LocalDate.of(2023, 1, 1);
		LocalDate date = LocalDate.of(2023, 1, 21);
		Category category = new Category(1, "");
		HistoryElement element = new DateHistoryElement(date);
		Purchase purchase = new Purchase(0, "purchase", "description", date, 0, null, category);
		element.addPurchase(purchase);

		var list = new ArrayList<HistoryElement>();
		list.add(element);
		HistoryDocumentImp document = new HistoryDocumentImp(path);
		document.addElements(list);
		document.setDocumentType(DocumentType.CSV);
		document.setElementType(ElementType.Date);
		document.setTimespan(dateS, date);
		var ret = document.print();
		assertEquals(true, ret);
		
		long lineCount;
		try (Stream<String> stream = Files.lines(Path.of(path), StandardCharsets.UTF_8)) {
		  lineCount = stream.count();
		}
		
		assertEquals(4, lineCount);
	}
	@Test
	void TestPrint_Category() throws IOException {
		String path = "Test.csv";
		LocalDate dateS = LocalDate.of(2023, 1, 1);
		LocalDate date = LocalDate.of(2023, 1, 21);
		Category category = new Category(1, "");
		HistoryElement element = new CategoryHistoryElement(category);
		Purchase purchase = new Purchase(0, "purchase", "description", date, 0, null, category);
		element.addPurchase(purchase);
		
		var list = new ArrayList<HistoryElement>();
		list.add(element);
		HistoryDocumentImp document = new HistoryDocumentImp(path);
		document.addElements(list);
		document.setDocumentType(DocumentType.CSV);
		document.setElementType(ElementType.Category);
		document.setTimespan(dateS, date);
		var ret = document.print();
		assertEquals(true, ret);
		
		long lineCount;
		try (Stream<String> stream = Files.lines(Path.of(path), StandardCharsets.UTF_8)) {
		  lineCount = stream.count();
		}
		
		assertEquals(4, lineCount);
	}
	@Test
	void TestPrint_Company() throws IOException {
		String path = "Test.csv";
		LocalDate dateS = LocalDate.of(2023, 1, 1);
		LocalDate date = LocalDate.of(2023, 1, 21);
		Category category = new Category(1, "");
		Company company = new Company(1, "comp", "loc");
		HistoryElement element = new CompanyHistoryElement(company);
		Purchase purchase = new Purchase(0, "purchase", "description", date, 0, company, category);
		element.addPurchase(purchase);
		
		var list = new ArrayList<HistoryElement>();
		list.add(element);
		HistoryDocumentImp document = new HistoryDocumentImp(path);
		document.addElements(list);
		document.setDocumentType(DocumentType.CSV);
		document.setElementType(ElementType.Company);
		document.setTimespan(dateS, date);
		var ret = document.print();
		assertEquals(true, ret);
		
		long lineCount;
		try (Stream<String> stream = Files.lines(Path.of(path), StandardCharsets.UTF_8)) {
		  lineCount = stream.count();
		}
		
		assertEquals(4, lineCount);
	}

}

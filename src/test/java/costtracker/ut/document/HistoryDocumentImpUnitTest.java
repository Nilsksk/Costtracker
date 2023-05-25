package costtracker.ut.document;

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
import costtracker.businessobjects.IncorrectEntryException;
import costtracker.businessobjects.Purchase;
import costtracker.document.HistoryDocumentImp;
import costtracker.document.elements.CategoryHistoryElement;
import costtracker.document.elements.CompanyHistoryElement;
import costtracker.document.elements.DateHistoryElement;
import costtracker.document.elements.HistoryElement;
import costtracker.document.type.DocumentType;
import costtracker.document.type.ElementType;

class HistoryDocumentImpUnitTest {

	@BeforeEach
	void setUpBefore() {
		File file = new File("Test.csv");
		if (file.exists())
			file.delete();
	}
	
	@Test
	void TestPrint_Date() throws IOException, IncorrectEntryException {
		String path = "Test.csv";
		LocalDate dateS = LocalDate.of(2023, 1, 1);
		LocalDate date = LocalDate.of(2023, 1, 21);
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		HistoryElement element = new DateHistoryElement(date);
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), 1.0)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.build();
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
	void TestPrint_Category() throws IOException, IncorrectEntryException {
		String path = "Test.csv";
		LocalDate dateS = LocalDate.of(2023, 1, 1);
		LocalDate date = LocalDate.of(2023, 1, 21);
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		HistoryElement element = new CategoryHistoryElement(category);
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), 1.0)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.build();
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
	void TestPrint_Company() throws IOException, IncorrectEntryException {
		String path = "Test.csv";
		LocalDate dateS = LocalDate.of(2023, 1, 1);
		LocalDate date = LocalDate.of(2023, 1, 21);
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		Company company = Company.CompanyBuilder
				.withName("comp")
				.withId(1)
				.build();
		HistoryElement element = new CompanyHistoryElement(company);
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), 1.0)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.withCompany(company)
				.build();
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

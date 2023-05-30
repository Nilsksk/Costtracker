package costtracker.ut.document;


import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import costtracker.application.in.ElementType;
import costtracker.application.in.HistoryDocument;
import costtracker.application.in.HistoryDocumentBase;
import costtracker.domain.businessobjects.Category;
import costtracker.domain.businessobjects.IncorrectEntryException;
import costtracker.domain.businessobjects.Purchase;

class HistoryDocumentImpUnitTest {

	@BeforeEach
	void setUpBefore() {
		File file = new File("Test.csv");
		if (file.exists())
			file.delete();
	}
	
	@Test
	void TestCreateCSVDocument() throws IncorrectEntryException {
		String description = "Data for";
		String name = "Categories";
		String path = "./";
		LocalDate dateStart = LocalDate.of(2023,05,22);
		LocalDate dateEnd = LocalDate.of(2023,05,23);
		List<Purchase> purchases = new ArrayList<Purchase>();
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		Category category2 = Category.CategoryBuilder
				.withName("cat2")
				.withId(2)
				.build();
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), 1.0)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.build();
		Purchase purchase2 = Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), 1.0)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.build();
		Purchase purchase3 = Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), 1.0)
				.withId(1)
				.withCategory(category2)
				.withDescription("description")
				.build();
		purchases.add(purchase);
		purchases.add(purchase2);
		purchases.add(purchase3);
		HistoryDocument document = HistoryDocumentBase.HistoryDocumentBuilder
		.asCSV()
		.withDescription(description)
		.withKpi(ElementType.Category)
		.withName(name)
		.withPath(path)
		.withTimespan(dateStart, dateEnd)
		.withData(purchases)
		.build();
		
		document.print();
		
	}
	
	@Test
	void TestCreateXMLDocument() throws IncorrectEntryException {
		String description = "Data for";
		String name = "Categories";
		String path = "./";
		LocalDate dateStart = LocalDate.of(2023,05,22);
		LocalDate dateEnd = LocalDate.of(2023,05,23);
		List<Purchase> purchases = new ArrayList<Purchase>();
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		Category category2 = Category.CategoryBuilder
				.withName("cat2")
				.withId(2)
				.build();
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), 1.0)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.build();
		Purchase purchase2 = Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), 1.0)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.build();
		Purchase purchase3 = Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), 1.0)
				.withId(1)
				.withCategory(category2)
				.withDescription("description")
				.build();
		purchases.add(purchase);
		purchases.add(purchase2);
		purchases.add(purchase3);
		HistoryDocument document = HistoryDocumentBase.HistoryDocumentBuilder
				.asXML()
				.withDescription(description)
				.withKpi(ElementType.Category)
				.withName(name)
				.withPath(path)
				.withTimespan(dateStart, dateEnd)
				.withData(purchases)
				.build();
		
		document.print();
		
	}

	@Test
	void TestCreateJSONDocument() throws IncorrectEntryException {
		String description = "Data for";
		String name = "Categories";
		String path = "./";
		LocalDate dateStart = LocalDate.of(2023,05,22);
		LocalDate dateEnd = LocalDate.of(2023,05,23);
		List<Purchase> purchases = new ArrayList<Purchase>();
		Category category = Category.CategoryBuilder
				.withName("cat")
				.withId(1)
				.build();
		Category category2 = Category.CategoryBuilder
				.withName("cat2")
				.withId(2)
				.build();
		Purchase purchase = Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), 1.0)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.build();
		Purchase purchase2 = Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), 1.0)
				.withId(1)
				.withCategory(category)
				.withDescription("description")
				.build();
		Purchase purchase3 = Purchase.PurchaseBuilder
				.withValues("purchase", LocalDate.of(2023,1, 21), 1.0)
				.withId(1)
				.withCategory(category2)
				.withDescription("description")
				.build();
		purchases.add(purchase);
		purchases.add(purchase2);
		purchases.add(purchase3);
		HistoryDocument document = HistoryDocumentBase.HistoryDocumentBuilder
				.asJSON()
				.withDescription(description)
				.withKpi(ElementType.Category)
				.withName(name)
				.withPath(path)
				.withTimespan(dateStart, dateEnd)
				.withData(purchases)
				.build();
		
		document.print();
		
	}
}
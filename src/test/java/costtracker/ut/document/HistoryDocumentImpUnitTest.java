package costtracker.ut.document;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import costtracker.businessobjects.Category;
import costtracker.businessobjects.IncorrectEntryException;
import costtracker.businessobjects.Purchase;
import costtracker.document.HistoryDocument;
import costtracker.document.HistoryDocumentBase;
import costtracker.document.type.ElementType;

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
		String path = ".\\";
		LocalDate dateStart = LocalDate.of(2023,05,22);
		LocalDate dateEnd = LocalDate.of(2023,05,23);
		List<Purchase> purchases = new ArrayList<Purchase>();
		Category category = Category.CategoryBuilder
		.withName("cat")
		.withId(1)
		.build();
		Purchase purchase = Purchase.PurchaseBuilder
		.withValues("purchase", LocalDate.of(2023,1, 21), 1.0)
		.withId(1)
		.withCategory(category)
		.withDescription("description")
		.build();
		purchases.add(purchase);
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
}
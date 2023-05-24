package costtracker.ut.document.reader;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import costtracker.businessobjects.Category;
import costtracker.document.reader.CSVImportFile;
import costtracker.document.reader.CategoryImportFile;

public class CategoryImportFileUnitTest {
	private String name = "name";
	
	@BeforeEach
	public void SetUp() {
		CategoryImportTestFile categoryImportTestFile = new CategoryImportTestFile();
		String line = name + ";"; 
		categoryImportTestFile.create(line);
	}
	

	
	@Test
	public void testPurchaseImportFile() {
		File file = new File("TestCategoryImportFile.csv");
		CategoryImportFile purchaseFile = new CategoryImportFile(new CSVImportFile(file));
		var result = purchaseFile.getCategories();
		
		assertEquals(1, result.size());
		Category category= result.get(0);
		
		assertEquals(name, category.getName());
	}
}

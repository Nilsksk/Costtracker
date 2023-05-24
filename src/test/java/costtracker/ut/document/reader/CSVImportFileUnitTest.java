package costtracker.ut.document.reader;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import costtracker.document.reader.CSVImportFile;

class CSVImportFileUnitTest {

	private String name = "name";
	private String description = "description";

	@BeforeEach
	public void SetUp() {
		CSVImportTestFile csvImportTestFile = new CSVImportTestFile();
		String line = name + ";" + description + ";"; 
		csvImportTestFile.create(line);
	}
	
	@Test
	void testCSVImportFile() {
		File file = new File("TestImportFile.csv");
		CSVImportFile csvFile = new CSVImportFile(file);
		var purchase = csvFile.getNextEntry();

		assertEquals("name", purchase.get("name"));
		assertEquals("description", purchase.get("description"));
	}

}

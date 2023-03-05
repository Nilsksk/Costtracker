package costtracker.ut.document.reader;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import costtracker.document.reader.CSVImportFile;

class CSVImportFileUnitTest {

	@Test
	void testCSVImportFile() {

		File file = new File("TestImportFile.csv");
		CSVImportFile csvFile = new CSVImportFile(file);
		var purchase = csvFile.getNextEntry();

		assertEquals("name", purchase.get("Name"));
		assertEquals("description", purchase.get("Description"));
	}

}

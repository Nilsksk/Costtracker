package costtracker.ut.document.reader;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import costtracker.businessobjects.Company;
import costtracker.document.reader.CSVImportFile;
import costtracker.document.reader.CompanyImportFile;

public class CompanyImportFileUnitTest {
	private String name = "name";
	private String location = "location";
	
	@BeforeEach
	public void SetUp() {
		CompanyImportTestFile companyImportTestFile = new CompanyImportTestFile();
		String line = name + ";" + location + ";"; 
		companyImportTestFile.create(line);
	}
	

	
	@Test
	public void testPurchaseImportFile() {
		File file = new File("TestCompanyImportFile.csv");
		CompanyImportFile purchaseFile = new CompanyImportFile(new CSVImportFile(file));
		var result = purchaseFile.getCompanies();
		
		assertEquals(1, result.size());
		Company company= result.get(0);
		
		assertEquals(name, company.getName());
		assertEquals(location, company.getLocation());
	}
}

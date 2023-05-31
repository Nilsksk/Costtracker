package costtracker.ut.document.reader;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import costtracker.application.out.CSVImportFile;
import costtracker.application.out.PurchaseImportFile;
import costtracker.domain.businessobjects.Purchase;

public class PurchaseImportFileUnitTest {
	
	private String name = "name";
	private String description = "description";
	private double price = 44.2;
	private int day = 7;
	private int month = 03;
	private int year = 2023;
	private int company = 7;
	private int category = 5;
	
	@BeforeEach
	public void SetUp() {
		PurchaseImportTestFile purchaseImportTestFile = new PurchaseImportTestFile();
		String line = name + ";" + description +";" + year + "-" + parseDayOrMonth(month) + "-" + parseDayOrMonth(day) + ";" + price + ";" + category + ";" + company + ";"; 
		purchaseImportTestFile.create(line);
	}
	
	
	private String parseDayOrMonth(int dayOrMonth) {
		if(dayOrMonth < 10) {
			return "0" + dayOrMonth;
		}
		return "" + dayOrMonth;
	}
	
	@Test
	public void testPurchaseImportFile() {
		File file = new File("TestPurchaseImportFile.csv");
		PurchaseImportFile purchaseFile = new PurchaseImportFile(new CSVImportFile(file));
		var result = purchaseFile.getPurchases();
		
		assertEquals(1, result.size());
		Purchase purchase = result.get(0);
		
		assertEquals(name, purchase.getName());
		assertEquals(description, purchase.getDescription());
		assertEquals(price, purchase.getPrice());
		assertEquals(day, purchase.getDate().getDayOfMonth());
		assertEquals(month, purchase.getDate().getMonthValue());
		assertEquals(year, purchase.getDate().getYear());
		assertEquals(category, purchase.getCategory().getId());
		assertEquals(company, purchase.getCompany().getId());
	}
}

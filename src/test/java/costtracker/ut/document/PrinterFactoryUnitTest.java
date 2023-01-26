package costtracker.ut.document;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import costtracker.document.PrinterFactory;
import costtracker.document.printer.CSVPrinter;
import costtracker.document.printer.elementprinter.CategoryHistoryElementPrinter;
import costtracker.document.printer.elementprinter.CompanyHistoryElementPrinter;
import costtracker.document.printer.elementprinter.DateHistoryElementPrinter;
import costtracker.document.type.DocumentType;
import costtracker.document.type.ElementType;

class PrinterFactoryUnitTest {

	@Test
	void testGetPrinter_CSV() throws Exception {
		PrinterFactory factory = PrinterFactory.getInstance();
		var printer = factory.getPrinter(DocumentType.CSV, null);
		assertInstanceOf(CSVPrinter.class, printer);
	}
	@Test
	void testGetElementPrinter_Date() throws Exception {
		PrinterFactory factory = PrinterFactory.getInstance();
		var elementPrinter = factory.getElementPrinter(ElementType.Date);
		assertInstanceOf(DateHistoryElementPrinter.class, elementPrinter);
	}
	@Test
	void testGetPrinter_Company() throws Exception {
		PrinterFactory factory = PrinterFactory.getInstance();
		var elementPrinter = factory.getElementPrinter(ElementType.Company);
		assertInstanceOf(CompanyHistoryElementPrinter.class, elementPrinter);
	}
	@Test
	void testGetPrinter_Category() throws Exception {
		PrinterFactory factory = PrinterFactory.getInstance();
		var elementPrinter = factory.getElementPrinter(ElementType.Category);
		assertInstanceOf(CategoryHistoryElementPrinter.class, elementPrinter);
	}

}

package costtracker.document;

import costtracker.document.printer.CSVPrinter;
import costtracker.document.printer.Printer;
import costtracker.document.printer.elementprinter.CategoryHistoryElementPrinter;
import costtracker.document.printer.elementprinter.CompanyHistoryElementPrinter;
import costtracker.document.printer.elementprinter.DateHistoryElementPrinter;
import costtracker.document.printer.elementprinter.ElementPrinter;
import costtracker.document.type.DocumentType;
import costtracker.document.type.ElementType;

public class PrinterFactory {
	
	private static PrinterFactory instance;
	
	private PrinterFactory() {
		
	}
	
	public static PrinterFactory getInstance() {
		if(instance == null)
			instance = new PrinterFactory();
		return instance;
	}
	
	public Printer getPrinter(DocumentType documentType, ElementPrinter elementPrinter) throws Exception {
		if(documentType == DocumentType.CSV)
			return new CSVPrinter(elementPrinter);
		throw new Exception("No suitable document type");
	}
	
	public ElementPrinter getElementPrinter(ElementType elementType) throws Exception{
		if(elementType == ElementType.Company)
			return new CompanyHistoryElementPrinter();
		else if(elementType == ElementType.Category)
			return new CategoryHistoryElementPrinter();
		else if(elementType == ElementType.Date)
			return new DateHistoryElementPrinter();
		throw new Exception("No suitable element type");
	}
}

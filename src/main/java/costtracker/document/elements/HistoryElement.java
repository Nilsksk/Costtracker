package costtracker.document.elements;

import costtracker.document.printer.DocumentPrinter;

public interface HistoryElement {
	double getTotal();	
	String getHeader();
	String printWith(DocumentPrinter printer);
	String getType();
}

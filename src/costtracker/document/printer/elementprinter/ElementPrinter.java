package costtracker.document.printer.elementprinter;

import costtracker.document.elements.HistoryElement;

public interface ElementPrinter {
	String getElementHeader();
	
	String getDescription();
	
	String getElementLines();
	
	void registerElement(HistoryElement element);	
	
	String getType();
}

package costtracker.document.printer.elementprinter;

import costtracker.document.elements.HistoryElement;
import costtracker.document.linewriter.LineWriter;

public interface ElementPrinter {
	String getElementHeader();
	
	String getDescription();
	
	String getElementLines();
	
	void registerElement(HistoryElement element);	
	
	String getType();

	void registerLineWriter(LineWriter lineWriter);
}

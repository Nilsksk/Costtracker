package costtracker.document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import costtracker.document.elements.HistoryElement;
import costtracker.document.printer.Printer;
import costtracker.document.printer.elementprinter.ElementPrinter;
import costtracker.document.type.DocumentType;
import costtracker.document.type.ElementType;

public class HistoryDocumentImp implements HistoryDocument {

	List<HistoryElement> elements;
	DocumentType documentType;
	ElementType elementType;
	String path;
	LocalDate dateStart;
	LocalDate dateEnd;

	public HistoryDocumentImp(String path) {
		elements = new ArrayList<HistoryElement>();
		this.path = path;
	}

	@Override
	public boolean addElement(HistoryElement element) {
		return elements.add(element);
	}

	@Override
	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;

	}

	public void setTimespan(LocalDate dateStart, LocalDate dateEnd) {
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}
	
	@Override
	public boolean print() {
		Printer printer;
		try {
			PrinterFactory factory = PrinterFactory.getInstance();
			ElementPrinter elementPrinter = factory.getElementPrinter(elementType);
			printer = factory.getPrinter(documentType, elementPrinter);
			printer.setPath(path);
			printer.setTimespan(dateStart, dateEnd);
			return printer.print(elements);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void setElementType(ElementType elementType) {
		this.elementType = elementType;
	}

	public void addElements(ArrayList<HistoryElement> list) {
		elements.addAll(list);
		
	}

}

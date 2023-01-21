package costtracker.document;

import java.time.LocalDate;
import java.util.ArrayList;

import costtracker.document.elements.HistoryElement;
import costtracker.document.type.DocumentType;
import costtracker.document.type.ElementType;

public interface HistoryDocument {

	void setTimespan(LocalDate dateStart, LocalDate dateEnd);
	
	boolean addElement(HistoryElement element);
	
	void addElements(ArrayList<HistoryElement> list);
	
	void setDocumentType(DocumentType documentType);

	void setElementType(ElementType elementType);
	
	boolean print();
	
}

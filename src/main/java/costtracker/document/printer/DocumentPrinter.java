package costtracker.document.printer;

import costtracker.document.elements.HistoryDocumentHeader;
import costtracker.document.elements.HistoryElement;
import costtracker.document.elements.PurchaseEntry;

public interface DocumentPrinter {

	public StringBuilder printHeader(HistoryDocumentHeader historyDocumentHeader);
	
	public StringBuilder printElement(HistoryElement historyElement);
	
	public StringBuilder printEntry(PurchaseEntry purchaseEntry);

}

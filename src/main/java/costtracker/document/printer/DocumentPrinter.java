package costtracker.document.printer;

import costtracker.document.elements.HistoryDocumentHeader;
import costtracker.document.elements.HistoryElement;
import costtracker.document.elements.PurchaseEntry;

public interface DocumentPrinter {

	public String printHeader(HistoryDocumentHeader historyDocumentHeader);
	
	public String printElement(HistoryElement historyElement);
	
	public String printEntry(PurchaseEntry purchaseEntry);

	public String printPurchaseEntryStart();

	public String printPurchaseEntrySeperator();

	public String printPurchaseEntryEnd();

}

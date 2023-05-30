package costtracker.application.in;

public interface DocumentPrinter {

	public String printHeader(HistoryDocumentHeader historyDocumentHeader);
	
	public String printElement(HistoryElement historyElement);
	
	public String printEntry(PurchaseEntry purchaseEntry);

	public String printPurchaseEntryStart();

	public String printPurchaseEntrySeperator();

	public String printPurchaseEntryEnd();

}

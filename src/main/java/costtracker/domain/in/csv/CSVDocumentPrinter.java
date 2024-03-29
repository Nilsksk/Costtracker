package costtracker.domain.in.csv;

import costtracker.domain.in.DocumentPrinter;
import costtracker.domain.in.HistoryDocumentHeader;
import costtracker.domain.in.HistoryElement;
import costtracker.domain.in.PurchaseEntry;

/**
 * This class is an internal class. Don't use, it might not work!
 * @author Florian Felix
 *
 */
public class CSVDocumentPrinter implements DocumentPrinter {

	@Override
	public String printHeader(HistoryDocumentHeader historyDocumentHeader) {
		StringBuilder sb = new StringBuilder();
		sb.append(historyDocumentHeader.getDescription());
		sb.append(";");
		sb.append("Startdatum:");
		sb.append(";");
		sb.append(historyDocumentHeader.getDateStart().toString());
		sb.append(";");
		sb.append("Enddatum:");
		sb.append(";");
		sb.append(historyDocumentHeader.getDateEnd().toString());
		sb.append(";");
		sb.append("\n");
		return sb.toString();
	}

	@Override
	public String printElement(HistoryElement historyElement) {
		StringBuilder sb = new StringBuilder();
		sb.append(historyElement.getType() + ":");
		sb.append(";");
		sb.append(historyElement.getHeader());
		sb.append(";");
		sb.append("Gesamt:");
		sb.append(";");
		sb.append(historyElement.getTotal());
		sb.append(";");
		sb.append("\n");
		sb.append(printHeaderLineForEntries());
		return sb.toString();
	}
	
	private StringBuilder printHeaderLineForEntries() {
		StringBuilder sb = new StringBuilder();
		sb.append("Name");
		sb.append(";");
		sb.append("Preis");
		sb.append(";");
		sb.append("Datum");
		sb.append(";");
		sb.append("Kategorie");
		sb.append(";");
		sb.append("Firma");
		sb.append(";");
		sb.append("Beschreibung");
		sb.append(";");
		sb.append("\n");
		return sb;
	}
	
	@Override
	public String printEntry(PurchaseEntry purchaseEntry) {
		StringBuilder sb = new StringBuilder();
		sb.append(purchaseEntry.getName());
		sb.append(";");
		sb.append(purchaseEntry.getPrice());
		sb.append(";");
		sb.append(purchaseEntry.getDate());
		sb.append(";");
		sb.append(purchaseEntry.getCategory());
		sb.append(";");
		sb.append(purchaseEntry.getCompany());
		sb.append(";");
		sb.append(purchaseEntry.getDescription());
		sb.append(";");
		sb.append("\n");
		return sb.toString();
	}

	@Override
	public String printPurchaseEntryStart() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String printPurchaseEntrySeperator() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String printPurchaseEntryEnd() {
		// TODO Auto-generated method stub
		return "";
	}
}

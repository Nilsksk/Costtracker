package costtracker.document.printer;

import costtracker.document.elements.HistoryDocumentHeader;
import costtracker.document.elements.HistoryElement;
import costtracker.document.elements.PurchaseEntry;

public class CSVDocumentPrinter implements DocumentPrinter {

	@Override
	public StringBuilder printHeader(HistoryDocumentHeader historyDocumentHeader) {
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
		return sb;
	}

	@Override
	public StringBuilder printElement(HistoryElement historyElement) {
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
		return sb;
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
	public StringBuilder printEntry(PurchaseEntry purchaseEntry) {
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
		return sb;
	}
}

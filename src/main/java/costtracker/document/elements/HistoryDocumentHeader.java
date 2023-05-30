package costtracker.document.elements;

import java.time.LocalDate;

import costtracker.document.printer.DocumentPrinter;
/**
 * This class is an internal class. Don't use, it might not work!
 * @author Florian Felix
 *
 */
public class HistoryDocumentHeader {
	
	private LocalDate dateStart;	
	private LocalDate dateEnd;	
	private String description;
	
	public HistoryDocumentHeader(LocalDate dateStart, LocalDate dateEnd, String description) {
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.description = description;
	}

	public LocalDate getDateStart() {
		return dateStart;
	}

	public LocalDate getDateEnd() {
		return dateEnd;
	}

	public String getDescription() {
		return description;
	}

	public StringBuilder printWith(DocumentPrinter printer) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(printer.printHeader(this));
		return sb;
	}
}

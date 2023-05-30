package costtracker.document.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import costtracker.businessobjects.IncorrectEntryException;
import costtracker.document.HistoryDocument;
import costtracker.document.HistoryDocumentBase;
import costtracker.document.elements.HistoryDocumentHeader;
import costtracker.document.elements.HistoryElement;
import costtracker.document.elements.HistoryElementsCreator;

public class CSVHistoryDocument extends HistoryDocumentBase implements HistoryDocument {

	public static class CSVHistoryDocumentBuilder extends HistoryDocumentBuilder<CSVHistoryDocumentBuilder> {
		public CSVHistoryDocumentBuilder() {
			
		}
		
		public CSVHistoryDocument build() throws IncorrectEntryException {
			validateDocument();
			String fullPath = path + "\\" + name + ".csv";
			File file = new File(fullPath);
			HistoryDocumentHeader historyDocumentHeader = new HistoryDocumentHeader(dateStart, dateEnd, description);
			List<HistoryElement> historyElements = new HistoryElementsCreator(purchases, elementType).createHistoryElements();
			CSVHistoryDocument csvHistoryDocument = new CSVHistoryDocument(historyDocumentHeader, historyElements, file);
			return csvHistoryDocument;			
		}
	}

	public void print() {
		StringBuilder sb = new StringBuilder();
		sb.append(header.printWith(printer));
		for (HistoryElement historyElement : historyElements) {
			sb.append(historyElement.printWith(printer));
		}
		try {
			try(FileWriter fileWriter = new FileWriter(file)){				
				fileWriter.write(sb.toString());
			}
		} catch (IOException e) {
			System.err.println("Document could not be printed!");			
		}
	}
	
	private CSVHistoryDocument(HistoryDocumentHeader historyDocumentHeader, List<HistoryElement> historyElements, File file) {
		this.historyElements = historyElements;
		this.header = historyDocumentHeader;
		this.file = file;
		this.printer = new CSVDocumentPrinter();
	}
	
	
}

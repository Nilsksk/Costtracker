package costtracker.document;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import costtracker.document.elements.HistoryDocumentHeader;
import costtracker.document.elements.HistoryElement;
import costtracker.document.elements.HistoryElementsCreator;
import costtracker.document.printer.CSVDocumentPrinter;

public class CSVHistoryDocument extends HistoryDocumentBase implements HistoryDocument {

	public static class CSVHistoryDocumentBuilder extends HistoryDocumentBuilder<CSVHistoryDocumentBuilder> {
		public CSVHistoryDocumentBuilder() {
			
		}
		
		public CSVHistoryDocument build() {
			validateDocument();
			String fullPath = path + "\\" + name + ".csv";
			File file = new File(fullPath);
			HistoryDocumentHeader historyDocumentHeader = new HistoryDocumentHeader(dateStart, dateEnd, description);
			List<HistoryElement> historyElements = new HistoryElementsCreator(purchases, elementType).createHistoryElements();
			CSVHistoryDocument csvHistoryDocument = new CSVHistoryDocument(path, name, historyDocumentHeader, historyElements, file);
			return csvHistoryDocument;			
		}

		private void validateDocument() {
			
		}
	}

	List<HistoryElement> historyElements;
	CSVDocumentPrinter printer;

	private CSVHistoryDocument(String path, String name, HistoryDocumentHeader historyDocumentHeader, List<HistoryElement> historyElements, File file) {
		this.historyElements = historyElements;
		this.path = path;
		this.name = name;
		this.header = historyDocumentHeader;
		this.file = file;
	}
	
	@Override
	public void print() {
		StringBuilder sb = new StringBuilder();
		sb.append(header.printWith(printer));
		for (HistoryElement historyElement : elements) {
			sb.append(historyElement.printWith(printer));
		}
		try {
			try(FileWriter fileWriter = new FileWriter(file)){				
				fileWriter.write(sb.toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
	}
}

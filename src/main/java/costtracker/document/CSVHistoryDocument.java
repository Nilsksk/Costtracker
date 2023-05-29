package costtracker.document;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import costtracker.businessobjects.IncorrectEntryException;
import costtracker.document.elements.HistoryDocumentHeader;
import costtracker.document.elements.HistoryElement;
import costtracker.document.elements.HistoryElementsCreator;
import costtracker.document.printer.CSVDocumentPrinter;

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

		private void validateDocument() throws IncorrectEntryException {
			Path p = Path.of(path);
			if(dateStart == null)
				throw new IncorrectEntryException("No start date!");
			else if(dateEnd == null)
				throw new IncorrectEntryException("No end date!");
			else if(!p.toFile().isDirectory())
				throw new IncorrectEntryException("Invalid path!");
			else if(purchases == null || purchases.size() == 0)
				throw new IncorrectEntryException("No purchases available to create history!");
			else if(name == null || name.equals(""))
				throw new IncorrectEntryException("No name!");
		}
	}

	private CSVDocumentPrinter printer;

	private CSVHistoryDocument(HistoryDocumentHeader historyDocumentHeader, List<HistoryElement> historyElements, File file) {
		this.historyElements = historyElements;
		this.header = historyDocumentHeader;
		this.file = file;
		this.printer = new CSVDocumentPrinter();
	}
	
	@Override
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
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
	}
}

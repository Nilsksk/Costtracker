package costtracker.application.in.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import costtracker.application.in.HistoryDocument;
import costtracker.application.in.HistoryDocumentBase;
import costtracker.application.in.HistoryDocumentHeader;
import costtracker.application.in.HistoryElement;
import costtracker.application.in.HistoryElementsCreator;
import costtracker.domain.businessobjects.IncorrectEntryException;

public class XMLHistoryDocument extends HistoryDocumentBase implements HistoryDocument {

	public static class XMLHistoryDocumentBuilder extends HistoryDocumentBuilder<XMLHistoryDocumentBuilder> {
		public XMLHistoryDocumentBuilder() {

		}

		public XMLHistoryDocument build() throws IncorrectEntryException {
			validateDocument();
			String fullPath = path + "\\" + name + ".xml";
			File file = new File(fullPath);
			HistoryDocumentHeader historyDocumentHeader = new HistoryDocumentHeader(dateStart, dateEnd, description);
			List<HistoryElement> historyElements = new HistoryElementsCreator(purchases, elementType)
					.createHistoryElements();
			XMLHistoryDocument xmlHistoryDocument = new XMLHistoryDocument(historyDocumentHeader, historyElements,
					file);
			return xmlHistoryDocument;
		}
	}

	public XMLHistoryDocument(HistoryDocumentHeader historyDocumentHeader, List<HistoryElement> historyElements,
			File file) {
		this.header = historyDocumentHeader;
		this.historyElements = historyElements;
		this.file = file;
		this.printer = new XMLDocumentPrinter();
	}
	@Override
	public void print() {
		StringBuilder sb = new StringBuilder();
		sb.append("<document>");
		sb.append("\n");
		sb.append(header.printWith(printer));
		sb.append("<elements>");
		sb.append("\n");
		for (HistoryElement historyElement : historyElements) {
			sb.append("<element>");
			sb.append("\n");
			sb.append(historyElement.printWith(printer));
			sb.append("</element>");
			sb.append("\n");
		}
		sb.append("</elements>");
		sb.append("\n");
		sb.append("</document>");
		try {
			try(FileWriter fileWriter = new FileWriter(file)){				
				fileWriter.write(sb.toString());
			}
		} catch (IOException e) {
			System.err.println("Document could not be printed!");			
		}
	}
}

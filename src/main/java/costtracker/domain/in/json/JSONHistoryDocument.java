package costtracker.domain.in.json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import costtracker.adapter.in.HistoryDocumentHeader;
import costtracker.adapter.in.HistoryElement;
import costtracker.adapter.in.HistoryElementsCreator;
import costtracker.application.in.HistoryDocument;
import costtracker.application.in.HistoryDocumentBase;
import costtracker.domain.businessobjects.IncorrectEntryException;

public class JSONHistoryDocument extends HistoryDocumentBase implements HistoryDocument {

	public static class JSONHistoryDocumentBuilder extends HistoryDocumentBuilder<JSONHistoryDocumentBuilder> {
		public JSONHistoryDocumentBuilder() {

		}

		public JSONHistoryDocument build() throws IncorrectEntryException {
			validateDocument();
			String fullPath = path + "\\" + name + ".json";
			File file = new File(fullPath);
			HistoryDocumentHeader historyDocumentHeader = new HistoryDocumentHeader(dateStart, dateEnd, description);
			List<HistoryElement> historyElements = new HistoryElementsCreator(purchases, elementType)
					.createHistoryElements();
			JSONHistoryDocument jsonHistoryDocument = new JSONHistoryDocument(historyDocumentHeader, historyElements,
					file);
			return jsonHistoryDocument;
		}
	}

	public JSONHistoryDocument(HistoryDocumentHeader historyDocumentHeader, List<HistoryElement> historyElements,
			File file) {
		this.historyElements = historyElements;
		this.header = historyDocumentHeader;
		this.file = file;
		this.printer = new JSONDocumentPrinter();
	}

	@Override
	public void print() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\n");
		sb.append(header.printWith(printer));
		sb.append(",\n");
		sb.append("\"elements\":[");
		for (HistoryElement historyElement : historyElements) {
			sb.append("{\n");
			sb.append(historyElement.printWith(printer));
			sb.append("}");
			if (!isLastHistoryElementOf(historyElements, historyElement)) {
				sb.append(",");
				sb.append("\n");
			}
		}
		sb.append("]\n");
		sb.append("}");
		try {
			try (FileWriter fileWriter = new FileWriter(file)) {
				fileWriter.write(sb.toString());
			}
		} catch (IOException e) {
			System.err.println("Document could not be printed!");
		}
	}

	private boolean isLastHistoryElementOf(List<HistoryElement> historyElements, HistoryElement historyElement) {
		int lastIndex = historyElements.size() - 1;
		HistoryElement lastElement = historyElements.get(lastIndex);
		return historyElement.equals(lastElement);
	}
}

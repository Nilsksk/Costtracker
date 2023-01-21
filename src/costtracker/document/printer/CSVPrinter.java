package costtracker.document.printer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import costtracker.document.elements.HistoryElement;
import costtracker.document.printer.elementprinter.ElementPrinter;

public class CSVPrinter implements Printer {

	private LocalDate dateStart;
	private LocalDate dateEnd;
	private ElementPrinter elementPrinter;
	private String path;

	public CSVPrinter(ElementPrinter elementPrinter) {
		this.elementPrinter = elementPrinter;
	}

	@Override
	public boolean print(List<HistoryElement> element) {
		StringBuilder sb = new StringBuilder();
		File file = new File(path);
		if (file.exists())
			return false;
		FileWriter writer;
		try {
			file.createNewFile();
			writer = new FileWriter(file);
			sb.append(createDocumentHeader());
			for (HistoryElement historyElement : element) {
				elementPrinter.registerElement(historyElement);
				sb.append(elementPrinter.getElementHeader() + ";" + historyElement.getTotal());
				sb.append("\n");
				sb.append(elementPrinter.getDescription());
				sb.append("\n");
				sb.append(elementPrinter.getElementLines());
			}
			writer.append(sb.toString());
			writer.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	@Override
	public void setTimespan(LocalDate dateStart, LocalDate dateEnd) {
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}

	private String dateToString(LocalDate date) {
		return date.getYear() + "/" + date.getDayOfMonth() + "/" + date.getMonthValue();
	}

	private String createDocumentHeader() {
		return "Purchases;" + dateToString(dateStart) + ";" + dateToString(dateEnd) + "\n";
	}

	@Override
	public void setPath(String path) {
		this.path = path;
		
	}

}

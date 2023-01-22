package costtracker.document.printer.elementprinter;

import costtracker.businessobjects.Purchase;
import costtracker.document.elements.DateHistoryElement;
import costtracker.document.elements.HistoryElement;
import costtracker.document.linewriter.LineWriter;

public class DateHistoryElementPrinter implements ElementPrinter {

	private DateHistoryElement element;

	private LineWriter lineWriter;
	
	@Override
	public String getElementHeader() {
		lineWriter.newLine();
		lineWriter.appendToLine(this.element.getHeader());
		lineWriter.appendToLine(Double.toString(this.element.getTotal()));
		return lineWriter.returnLine();
	}

	@Override
	public String getElementLines() {
		StringBuilder lines = new StringBuilder();
		for (Purchase purchase : element.getPurchases()) {
			lineWriter.newLine();
			lineWriter.appendToLine(purchase.getDateString());
			lineWriter.appendToLine(purchase.getName());
			lineWriter.appendToLine(purchase.getDescription());
			lineWriter.appendToLine(Double.toString(purchase.getPrice()));
			lineWriter.appendToLine(purchase.getCategory().getName());
			if (purchase.getCompany() != null) {
				lineWriter.appendToLine(purchase.getCompany().getName());
			}
			else
				lineWriter.appendToLine("");
			lines.append(lineWriter.returnLine());
		}
		return lines.toString();
	}

	@Override
	public void registerElement(HistoryElement element) {
		this.element = (DateHistoryElement) element;
	}

	@Override
	public String getDescription() {
		lineWriter.newLine();
		lineWriter.appendToLine("Date");
		lineWriter.appendToLine("Name");
		lineWriter.appendToLine("Description");
		lineWriter.appendToLine("Price");
		lineWriter.appendToLine("Category");
		lineWriter.appendToLine("Company");
		return lineWriter.returnLine();
	}

	@Override
	public String getType() {
		return "Date";
	}

	@Override
	public void registerLineWriter(LineWriter lineWriter) {
		this.lineWriter = lineWriter;
		
	}

}

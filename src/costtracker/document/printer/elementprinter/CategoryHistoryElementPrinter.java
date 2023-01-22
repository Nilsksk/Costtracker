package costtracker.document.printer.elementprinter;

import costtracker.businessobjects.Purchase;
import costtracker.document.elements.CategoryHistoryElement;
import costtracker.document.elements.HistoryElement;
import costtracker.document.linewriter.LineWriter;

public class CategoryHistoryElementPrinter implements ElementPrinter {

	private CategoryHistoryElement element;

	private LineWriter lineWriter;

	@Override
	public String getElementHeader() {
		return element.getHeader();
	}

	@Override
	public String getElementLines() {
		StringBuilder lines = new StringBuilder();
		for (Purchase purchase : element.getPurchases()) {
			lineWriter.newLine();
			lineWriter.appendToLine(purchase.getCategory().getName());
			lineWriter.appendToLine(purchase.getName());
			lineWriter.appendToLine(purchase.getDescription());
			lineWriter.appendToLine(Double.toString(purchase.getPrice()));
			lineWriter.appendToLine(purchase.getDateString());
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
		this.element = (CategoryHistoryElement) element;
	}

	@Override
	public String getDescription() {
		lineWriter.newLine();
		lineWriter.appendToLine("Category");
		lineWriter.appendToLine("Name");
		lineWriter.appendToLine("Description");
		lineWriter.appendToLine("Price");
		lineWriter.appendToLine("Date");
		lineWriter.appendToLine("Company");
		return lineWriter.returnLine();
	}

	@Override
	public String getType() {
		return "Category";
	}

	@Override
	public void registerLineWriter(LineWriter lineWriter) {
		this.lineWriter = lineWriter;

	}

}

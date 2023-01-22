package costtracker.document.printer.elementprinter;

import costtracker.businessobjects.Purchase;
import costtracker.document.elements.CompanyHistoryElement;
import costtracker.document.elements.HistoryElement;
import costtracker.document.linewriter.LineWriter;

public class CompanyHistoryElementPrinter implements ElementPrinter {

	private CompanyHistoryElement element;

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
			if (purchase.getCompany() != null) {
				lineWriter.appendToLine(purchase.getCategory().getName());
			}
			else
				lineWriter.appendToLine("");
			lineWriter.appendToLine(purchase.getName());
			lineWriter.appendToLine(purchase.getDescription());
			lineWriter.appendToLine(Double.toString(purchase.getPrice()));
			lineWriter.appendToLine(purchase.getDateString());
			lineWriter.appendToLine(purchase.getCategory().getName());
			lines.append(lineWriter.returnLine());
		}
		return lines.toString();
	}

	@Override
	public void registerElement(HistoryElement element) {
		this.element = (CompanyHistoryElement) element;

	}

	@Override
	public String getDescription() {
		lineWriter.newLine();
		lineWriter.appendToLine("Company");
		lineWriter.appendToLine("Name");
		lineWriter.appendToLine("Description");
		lineWriter.appendToLine("Price");
		lineWriter.appendToLine("Date");
		lineWriter.appendToLine("Category");
		return lineWriter.returnLine();
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Company";
	}

	@Override
	public void registerLineWriter(LineWriter lineWriter) {
		this.lineWriter = lineWriter;
	}

}

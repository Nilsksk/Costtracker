package costtracker.document.printer.elementprinter;

import costtracker.businessobjects.Purchase;
import costtracker.document.elements.DateHistoryElement;
import costtracker.document.elements.HistoryElement;

public class DateHistoryElementPrinter implements ElementPrinter {

	private DateHistoryElement element;

	@Override
	public String getElementHeader() {
		return element.getHeader();
	}

	@Override
	public String getElementLines() {
		StringBuilder sb = new StringBuilder();
		for (Purchase purchase : element.getPurchases()) {
			sb.append(purchase.getDateString());
			sb.append(";");
			sb.append(purchase.getName());
			sb.append(";");
			sb.append(purchase.getDescription());
			sb.append(";");
			sb.append(purchase.getPrice());
			sb.append(";");
			sb.append(purchase.getCategory().getName());
			sb.append(";");
			if (purchase.getCompany() != null) {
				sb.append(purchase.getCompany().getName());
			}
			sb.append(";");
			sb.append("\n");
		}
		return sb.toString();
	}

	@Override
	public void registerElement(HistoryElement element) {
		this.element = (DateHistoryElement) element;
	}

	@Override
	public String getDescription() {
		return "Date;Name;Description;Price;Category;Company";
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Date";
	}

}

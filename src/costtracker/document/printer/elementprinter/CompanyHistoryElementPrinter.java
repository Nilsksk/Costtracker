package costtracker.document.printer.elementprinter;

import costtracker.businessobjects.Purchase;
import costtracker.document.elements.CompanyHistoryElement;
import costtracker.document.elements.HistoryElement;

public class CompanyHistoryElementPrinter implements ElementPrinter {

	private CompanyHistoryElement element;

	@Override
	public String getElementHeader() {
		return element.getHeader();
	}

	@Override
	public String getElementLines() {
		StringBuilder sb = new StringBuilder();
		for (Purchase purchase : element.getPurchases()) {
			if (purchase.getCompany() != null) {
				sb.append(purchase.getCompany().getName());
			}
			sb.append(";");
			sb.append(purchase.getName());
			sb.append(";");
			sb.append(purchase.getDescription());
			sb.append(";");
			sb.append(purchase.getPrice());
			sb.append(";");
			sb.append(purchase.getDateString());
			sb.append(";");
			sb.append(purchase.getCategory().getName());
			sb.append(";");
			sb.append("\n");
		}
		return sb.toString();
	}

	@Override
	public void registerElement(HistoryElement element) {
		this.element = (CompanyHistoryElement) element;

	}

	@Override
	public String getDescription() {
		return "Company;Name;Description;Price;Date;Category";
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Company";
	}

}

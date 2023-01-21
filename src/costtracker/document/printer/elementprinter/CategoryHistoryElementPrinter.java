package costtracker.document.printer.elementprinter;

import costtracker.businessobjects.Purchase;
import costtracker.document.elements.CategoryHistoryElement;
import costtracker.document.elements.HistoryElement;

public class CategoryHistoryElementPrinter implements ElementPrinter {

	private CategoryHistoryElement element;
	
	@Override
	public String getElementHeader() {
		return element.getHeader();
	}

	
	
	@Override
	public String getElementLines() {
		StringBuilder sb = new StringBuilder();
		for (Purchase purchase : element.getPurchases()) {
			sb.append(purchase.getCategory().getName());
			sb.append(";");
			sb.append(purchase.getName());
			sb.append(";");
			sb.append(purchase.getDescription());
			sb.append(";");
			sb.append(purchase.getPrice());
			sb.append(";");
			sb.append(purchase.getDateString());
			sb.append(";");
			if (purchase.getCompany() != null) {
				sb.append(purchase.getCompany().getName());
				sb.append(";");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	@Override
	public void registerElement(HistoryElement element) {
		this.element = (CategoryHistoryElement) element;
	}



	@Override
	public String getDescription() {
		return "Category;Name;Description;Price;Date;Company";
	}



	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Category";
	}

}

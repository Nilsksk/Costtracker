package costtracker.application.in.xml;

import costtracker.application.in.DocumentPrinter;
import costtracker.application.in.HistoryDocumentHeader;
import costtracker.application.in.HistoryElement;
import costtracker.application.in.PurchaseEntry;

/**
 * This class is an internal class. Don't use, it might not work!
 * @author Florian Felix
 *
 */
public class XMLDocumentPrinter implements DocumentPrinter {

	@Override
	public String printHeader(HistoryDocumentHeader historyDocumentHeader) {
		XMLNode headerNode = new XMLNode("header", "");
		XMLAttribute descriptionAttribute = new XMLAttribute("description", historyDocumentHeader.getDescription());
		XMLAttribute startDateAttribute = new XMLAttribute("startDate", historyDocumentHeader.getDateStart().toString());
		XMLAttribute endDateAttribute = new XMLAttribute("startDate", historyDocumentHeader.getDateEnd().toString());
		headerNode.addChild(descriptionAttribute);
		headerNode.addChild(startDateAttribute);
		headerNode.addChild(endDateAttribute);
		return headerNode.toString();
	}

	@Override
	public String printElement(HistoryElement historyElement) {
		XMLNode elementNode = new XMLNode("elementHeader", "");
		XMLAttribute headerAttribute = new XMLAttribute(historyElement.getType().toLowerCase(), historyElement.getHeader());
		XMLAttribute totalAttribute = new XMLAttribute("total",String.valueOf(historyElement.getTotal()));
		elementNode.addChild(headerAttribute);
		elementNode.addChild(totalAttribute);
		return elementNode.toString();
	}

	@Override
	public String printEntry(PurchaseEntry purchaseEntry) {
		XMLNode purchaseNode = new XMLNode("purchase","");
		XMLAttribute nameAttribute = new XMLAttribute("name", purchaseEntry.getName());
		 XMLAttribute priceAttribute = new XMLAttribute("price", purchaseEntry.getPrice());
		 XMLAttribute dateAttribute = new XMLAttribute("date", purchaseEntry.getDate());
		 XMLAttribute categoryAttribute = new XMLAttribute("category", purchaseEntry.getCategory());
		 XMLAttribute companyAttribute = new XMLAttribute("company", purchaseEntry.getCompany());
		 XMLAttribute descriptionAttribute = new XMLAttribute("description", purchaseEntry.getDescription());
		purchaseNode.addChild(nameAttribute);
		purchaseNode.addChild(priceAttribute);
		purchaseNode.addChild(dateAttribute);
		purchaseNode.addChild(categoryAttribute);
		purchaseNode.addChild(companyAttribute);
		purchaseNode.addChild(descriptionAttribute);
		return purchaseNode.toString();
	}

	@Override
	public String printPurchaseEntryStart() {
		return "<purchases>\n";
	}

	@Override
	public String printPurchaseEntrySeperator() {
		return "";
	}

	@Override
	public String printPurchaseEntryEnd() {
		return "</purchases>\n";
	}

}

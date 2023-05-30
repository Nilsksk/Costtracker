package costtracker.document.xml;

import costtracker.document.elements.HistoryDocumentHeader;
import costtracker.document.elements.HistoryElement;
import costtracker.document.elements.PurchaseEntry;
import costtracker.document.printer.DocumentPrinter;

/**
 * This class is an internal class. Don't use, it might not work!
 * @author Florian Felix
 *
 */
public class XMLDocumentPrinter implements DocumentPrinter {

	@Override
	public StringBuilder printHeader(HistoryDocumentHeader historyDocumentHeader) {
		StringBuilder sb = new StringBuilder();
		XMLNode headerNode = new XMLNode("header", "");
		XMLAttribute descriptionAttribute = new XMLAttribute("description", historyDocumentHeader.getDescription());
		XMLAttribute startDateAttribute = new XMLAttribute("startDate", historyDocumentHeader.getDateStart().toString());
		XMLAttribute endDateAttribute = new XMLAttribute("startDate", historyDocumentHeader.getDateEnd().toString());
		headerNode.addChild(descriptionAttribute);
		headerNode.addChild(startDateAttribute);
		headerNode.addChild(endDateAttribute);
		sb.append(headerNode.toString());
		return sb;
	}

	@Override
	public StringBuilder printElement(HistoryElement historyElement) {
		StringBuilder sb = new StringBuilder();
		XMLNode elementNode = new XMLNode("elementHeader", "");
		XMLAttribute headerAttribute = new XMLAttribute(historyElement.getType().toLowerCase(), historyElement.getHeader());
		XMLAttribute totalAttribute = new XMLAttribute("total",String.valueOf(historyElement.getTotal()));
		elementNode.addChild(headerAttribute);
		elementNode.addChild(totalAttribute);
		sb.append(elementNode.toString());
		return sb;
	}

	@Override
	public StringBuilder printEntry(PurchaseEntry purchaseEntry) {
		StringBuilder sb = new StringBuilder();
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
		sb.append(purchaseNode.toString());
		return sb;
	}

}
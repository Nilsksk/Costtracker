package costtracker.document.json;

import costtracker.document.elements.HistoryDocumentHeader;
import costtracker.document.elements.HistoryElement;
import costtracker.document.elements.PurchaseEntry;
import costtracker.document.printer.DocumentPrinter;

/**
 * This class is an internal class. Don't use, it might not work!
 * 
 * @author IITAdmin
 *
 */
public class JSONDocumentPrinter implements DocumentPrinter {

	@Override
	public StringBuilder printHeader(HistoryDocumentHeader historyDocumentHeader) {
		JSONObject headerObject = new JSONObject();
		
		JSONString jsonDescription = new JSONString(historyDocumentHeader.getDescription());
		JSONString jsonDateStart = new JSONString(String.valueOf(historyDocumentHeader.getDateStart()));
		JSONString jsonDateEnd = new JSONString(String.valueOf(historyDocumentHeader.getDateEnd()));
		
		headerObject.addPair(new JSONPair("description", jsonDescription));
		headerObject.addPair(new JSONPair("startDate", jsonDateStart));
		headerObject.addPair(new JSONPair("endDate", jsonDateEnd));
		
		JSONPair header = new JSONPair("header", headerObject);
		
		StringBuilder sb = new StringBuilder();
		sb.append(header.toString());
		sb.append(",");
		return sb;
	}

	@Override
	public StringBuilder printElement(HistoryElement historyElement) {
		JSONObject elementHeaderObject = new JSONObject();
		
		JSONPair header = new JSONPair(historyElement.getType(), new JSONString(historyElement.getHeader()));
		JSONPair total = new JSONPair("total", new JSONDouble(historyElement.getTotal()));
		
		elementHeaderObject.addPair(header);
		elementHeaderObject.addPair(total);
		
		JSONPair elementHeader = new JSONPair("elementHeader", elementHeaderObject);
		
		StringBuilder sb = new StringBuilder();
		sb.append(elementHeader.toString());
		sb.append(",\n");
		sb.append("\"purchases\":[");
		
		return sb;
	}

	@Override
	public StringBuilder printEntry(PurchaseEntry purchaseEntry) {
		JSONObject purchaseObject = new JSONObject();
		
		JSONPair name = new JSONPair("name", new JSONString(purchaseEntry.getName()));
		JSONPair price = new JSONPair("price", new JSONDouble(Double.valueOf(purchaseEntry.getPrice())));
		JSONPair date = new JSONPair("date", new JSONString(purchaseEntry.getDate()));
		JSONPair category = new JSONPair("category", new JSONString(purchaseEntry.getCategory()));
		JSONPair company = new JSONPair("company", new JSONString(purchaseEntry.getCompany()));
		JSONPair description = new JSONPair("description", new JSONString(purchaseEntry.getDescription()));
		
		purchaseObject.addPair(name);
		purchaseObject.addPair(price);
		purchaseObject.addPair(date);
		purchaseObject.addPair(category);
		purchaseObject.addPair(company);
		purchaseObject.addPair(description);
		
		StringBuilder sb = new StringBuilder();
		sb.append(purchaseObject);
		sb.append(", ");
		return sb;
	}
	

}
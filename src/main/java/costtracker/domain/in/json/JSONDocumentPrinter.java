package costtracker.domain.in.json;

import costtracker.adapter.in.HistoryDocumentHeader;
import costtracker.adapter.in.HistoryElement;
import costtracker.adapter.in.PurchaseEntry;
import costtracker.adapter.in.DocumentPrinter;
/**
 * This class is an internal class. Don't use, it might not work!
 * 
 * @author IITAdmin
 *
 */
public class JSONDocumentPrinter implements DocumentPrinter {

	@Override
	public String printHeader(HistoryDocumentHeader historyDocumentHeader) {
		JSONObject headerObject = new JSONObject();
		
		JSONString jsonDescription = new JSONString(historyDocumentHeader.getDescription());
		JSONString jsonDateStart = new JSONString(String.valueOf(historyDocumentHeader.getDateStart()));
		JSONString jsonDateEnd = new JSONString(String.valueOf(historyDocumentHeader.getDateEnd()));
		
		headerObject.addPair(new JSONPair("description", jsonDescription));
		headerObject.addPair(new JSONPair("startDate", jsonDateStart));
		headerObject.addPair(new JSONPair("endDate", jsonDateEnd));
		
		JSONPair header = new JSONPair("header", headerObject);
		
		return header.toString();
	}

	@Override
	public String printElement(HistoryElement historyElement) {
		JSONObject elementHeaderObject = new JSONObject();
		
		JSONPair header = new JSONPair(historyElement.getType(), new JSONString(historyElement.getHeader()));
		JSONPair total = new JSONPair("total", new JSONDouble(historyElement.getTotal()));
		
		elementHeaderObject.addPair(header);
		elementHeaderObject.addPair(total);
		
		JSONPair elementHeader = new JSONPair("elementHeader", elementHeaderObject);
		
		return elementHeader.toString();	
	}

	@Override
	public String printEntry(PurchaseEntry purchaseEntry) {
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
		
		return purchaseObject.toString();
	}

	@Override
	public String printPurchaseEntryStart() {
		return ",\n\"purchases\":[";
	}

	@Override
	public String printPurchaseEntrySeperator() {
		return ",\n";
	}

	@Override
	public String printPurchaseEntryEnd() {
		return "]\n";
	}
	

}

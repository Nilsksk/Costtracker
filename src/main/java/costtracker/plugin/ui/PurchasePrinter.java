package costtracker.plugin.ui;

import java.util.List;

public class PurchasePrinter {

	public static void printPurchases(List<PurchaseModel> purchases) {
		boolean moreThenZeroPurchases = purchases.size() > 0;
		if (moreThenZeroPurchases) {
			int longestPurchaseId = purchases
								.stream()
								.mapToInt(p -> String
								.valueOf(p.getPostion())
								.length()).max()
								.getAsInt();
			int longestPurchaseName = purchases
								.stream()
								.mapToInt(p -> p.getPurchase()
								.getName()
								.length())
								.max()
								.getAsInt();
			int longestPurchasePrice = purchases
								.stream()
								.mapToInt(p -> String
								.valueOf(p.getPurchase()
								.getPrice())
								.length())
								.max()
								.getAsInt();
			int longestPurchaseDate = purchases
								.stream()
								.mapToInt(p -> p.getPurchase()
								.getDateString()
								.length())
								.max()
								.getAsInt();
			int longestPurchaseCompany = purchases
								.stream()
								.mapToInt(p -> p.getPurchase()
								.getCompany() == null ? 0 : p.getPurchase()
								.getCompany()
								.getName()
								.length())
								.max()
								.getAsInt();
			int longestPurchaseLocation = purchases
								.stream()
								.mapToInt(p -> p.getPurchase()
								.getCompany()
								.getLocation()
								.length())
								.max()
								.getAsInt();
			int longestPurchaseCategory = purchases
								.stream()
								.mapToInt(p -> p.getPurchase()
								.getCategory()
								.getName()
								.length())
								.max()
								.getAsInt();
			purchasePrinter(purchases, longestPurchaseId, longestPurchaseName, longestPurchasePrice, longestPurchaseDate, longestPurchaseCompany, longestPurchaseLocation, longestPurchaseCategory);
		}
		DialogueHelper.printLine("");
	}
	
	private static void purchasePrinter(List<PurchaseModel> purchases, int longestPurchaseId, int longestPurchaseName, 
										int longestPurchasePrice, int longestPurchaseDate, int longestPurchaseCompany, int longestPurchaseLocation, int longestPurchaseCategory) {
		for (PurchaseModel purchaseModel : purchases) {
			String printId = String.valueOf(purchaseModel.getPostion());
			printColumn(longestPurchaseId, printId);
			String printName = purchaseModel.getPurchase().getName();
			printColumn(longestPurchaseName, printName);
			String printPrice = String.valueOf(purchaseModel.getPurchase().getPrice());
			printColumn(longestPurchasePrice, printPrice);
			String printDate = purchaseModel.getPurchase().getDateString();
			printColumn(longestPurchaseDate, printDate);
			boolean companyIsNull = purchaseModel.getPurchase().getCompany() == null;
			if (companyIsNull) {
				String empty = "";
				printColumn(longestPurchaseCompany, empty);
			} else {
				String companyName = purchaseModel.getPurchase().getCompany().getName();
				printColumn(longestPurchaseCompany, companyName);
				String companyLocation = purchaseModel.getPurchase().getCompany().getLocation();
				printColumn(longestPurchaseLocation, companyLocation);
			}
			String purchaseName = purchaseModel.getPurchase().getCategory().getName();
			printColumn(longestPurchaseCategory, purchaseName);
			String purchaseDescription = purchaseModel.getPurchase().getDescription();
			DialogueHelper.printLine(purchaseDescription);	
		}
	}

	 private static void printColumn(int maxLength, String value) {
		int numberWhitespaces = maxLength - value.length();
		String output = " " + value + DialogueHelper.addWhitespaces(numberWhitespaces) + " |";
		DialogueHelper.print(output);
	}
}

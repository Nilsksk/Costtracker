package costtracker.plugin.ui;

import java.util.List;
import java.util.Scanner;

import costtracker.domain.businessobjects.Category;
import costtracker.domain.businessobjects.Company;
import costtracker.domain.businessobjects.Purchase;

public class DialogueHelper {

	private static Scanner sc = new Scanner(System.in);

	public static String fillWhitespaces(int numberWhitespaces) {
		String whitespaces = "";
		for (int i = 0; i < numberWhitespaces; i++) {
			whitespaces += " ";
		}
		return whitespaces;
	}

	public static void startDialogue(String startingDialogue) {
		print(startingDialogue);
		sc.nextLine();
		println("");
	}

	public static String inputDialogue(String input) {
		String output = input + ": ";
		print(output);
		return returnInput();
	}

	public static String changeDialogue(String descriptionOfField, String input) {
		String output = "(" + descriptionOfField + ")" + " " + input + fillWhitespaces(0) + " -> ";
		print(output);
		return returnInput();
	}

	public static String returnInput() {
		return sc.nextLine();
	}

	public static int getIntDialogue(String input) {
		String output = input + ": ";
		print(output);
		String id = sc.nextLine();
		return mapToId(id);
	}

	private static int mapToId(String ret) {
		try {
			return Integer.parseInt(ret);
		} catch (Exception e) {
			return -1;
		}
	}

	public static void print(String string) {
		System.out.print(string);
	}

	public static void println(String string) {
		System.out.println(string);
	}
	
	public static boolean submitEntry() {
		String save = "Zum Speichern ihrer Daten '+' eingeben: ";
		String input = "+";
		return saveData(save, input);
	}

	public static void printCompanies(List<CompanyModel> companies) {
		boolean moreThenZeroCompanies = companies.size() > 0;
		if(moreThenZeroCompanies) {	
			int longestCompanyId = companies
								.stream()
								.mapToInt(c -> String
								.valueOf(c.getPosition())
								.length())
								.max()
								.getAsInt();
			int longestCompanyName = companies
								.stream()
								.mapToInt(c -> c.getCompany()
								.getName()
								.length())
								.max()
								.getAsInt();
			companyPrinter(companies, longestCompanyId, longestCompanyName);
		}
	}

	private static void companyPrinter(List<CompanyModel> companies, int longestCompanyId, int longestCompanyName) {
		for (CompanyModel companyModel : companies) {
			int numberWhitespaces = (longestCompanyId - String.valueOf(companyModel.getPosition()).length());
			String printId = " " + companyModel.getPosition() + DialogueHelper.fillWhitespaces(numberWhitespaces) + " | ";
			print(printId);
			numberWhitespaces = (longestCompanyName - companyModel
													.getCompany()
													.getName()
													.length());
			String printName = companyModel.getCompany().getName() + DialogueHelper.fillWhitespaces(numberWhitespaces) + " | ";
			print(printName);
			String printLocation = companyModel.getCompany().getLocation();
			println(printLocation);
		}
	}
	
	public static void printCategories(List<CategoryModel> categories) {
		boolean moreThenZeroCategories = categories.size() > 0;
		if (moreThenZeroCategories) {
			int longestCategoryId = categories
								.stream()
								.mapToInt(c -> String
								.valueOf(c.getPosition())
								.length()).max()
								.getAsInt();
			categoryPrinter(categories, longestCategoryId);
		}
	}
	
	private static void categoryPrinter(List<CategoryModel> categories, int longestCategoryId) {
		for (CategoryModel categoryModel : categories) {
			int numberWhitespaces = longestCategoryId - String
													.valueOf(categoryModel
													.getPosition())
													.length();
			String printId = " " + categoryModel.getPosition() + fillWhitespaces(numberWhitespaces) + " | ";
			print(printId);
			String printName = categoryModel.getCategory().getName();
			println(printName);
		}
	}

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
			int longestPurchaseCategory = purchases
								.stream()
								.mapToInt(p -> p.getPurchase()
								.getCategory()
								.getName()
								.length())
								.max()
								.getAsInt();
			purchasePrinter(purchases, longestPurchaseId, longestPurchaseName, longestPurchasePrice, longestPurchaseDate, longestPurchaseCompany, longestPurchaseCategory);
		}
		println("");
	}
	
	private static void purchasePrinter(List<PurchaseModel> purchases, int longestPurchaseId, int longestPurchaseName, 
										int longestPurchasePrice, int longestPurchaseDate, int longestPurchaseCompany, int longestPurchaseCategory) {
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
			}
			String purchaseName = purchaseModel.getPurchase().getCategory().getName();
			printColumn(longestPurchaseCategory, purchaseName);
			String purchaseDescription = purchaseModel.getPurchase().getDescription();
			println(purchaseDescription);	
		}
	}

	public static void printColumn(int maxLength, String value) {
		int numberWhitespaces = maxLength - value.length();
		String output = " " + value + fillWhitespaces(numberWhitespaces) + " |";
		print(output);
	}

	public static boolean saveData(String input, String action) {
		print(input);
		boolean output = sc.nextLine().equals(action);
		return output;
	}
	
	public static void validateCreation(boolean created, String succesful, String unsuccesful) {
		if (created) {
			println(succesful);
		}
		else {
			println(unsuccesful);
		}
	}
	
	public static int interactQuestion(String question) {
		print(question);

		Scanner sc = new Scanner(System.in);
		int action = sc.nextInt();
		println("");
		
		return action;
	}
	
	public static boolean isValidInput(int input, int maxInput) {
		boolean valid = input < maxInput && input > 0;
		return valid;
	}
	
	public static boolean validateDeleteOrDeactivation(String deleteOrDeactivate) {
		String question = "Löschung oder Deaktivierung durchführen (-)? ";
		String input = inputDialogue(question);
		println("");
		boolean equal = input.equals("-");
		if (equal) {
			println(deleteOrDeactivate);
			println("");
			return true;
		}
		return false;
	}
	
	public static boolean validateEnable(String activate) {
		String question = "Aktivierung durchführen (+)?";
		String input = inputDialogue(question);
		println("");
		boolean equal = input.equals("+");
		if (equal) {
			println(activate);
			println("");
			return true;
		}
		return false;
	}

}

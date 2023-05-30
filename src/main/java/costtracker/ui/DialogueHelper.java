package costtracker.ui;

import java.util.List;
import java.util.Scanner;

import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;
import costtracker.businessobjects.Purchase;

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
		System.out.print(startingDialogue);
		sc.nextLine();
		System.out.println();
	}

	public static String inputDialogue(String input) {

		System.out.print(input + ": ");
		return returnInput();
	}

	public static String changeDialogue(String descriptionOfField, String input) {

		System.out.print("(" + descriptionOfField + ")" + " " + input + fillWhitespaces(0) + " -> ");
		return returnInput();
	}

	public static String returnInput() {
		return sc.nextLine();
	}

	public static int getIntDialogue(String input) {
		System.out.print(input + ": ");
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
		return saveData("Zum Speichern ihrer Daten '+' eingeben: ", "+");
	}

	public static void printCompanies(List<CompanyModel> companies) {
		int longestCompanyId = companies.stream().mapToInt(c -> String.valueOf(c.getPosition()).length()).max().getAsInt();
		int longestCompanyName = companies.stream().mapToInt(c -> c.getCompany().getName().length()).max().getAsInt();
		for (CompanyModel companyModel : companies) {
			int numberWhitespaces = (longestCompanyId - String.valueOf(companyModel.getPosition()).length());
			System.out.print(" " + companyModel.getPosition() + DialogueHelper.fillWhitespaces(numberWhitespaces) + " | ");
			numberWhitespaces = (longestCompanyName - companyModel.getCompany().getName().length());
			System.out.print(companyModel.getCompany().getName() + DialogueHelper.fillWhitespaces(numberWhitespaces) + " | ");
			System.out.println(companyModel.getCompany().getLocation());
		}
	}

	public static void printCategories(List<CategoryModel> categories) {
		int longestCategoryId = categories.stream().mapToInt(c -> String.valueOf(c.getPosition()).length()).max().getAsInt();
		for (CategoryModel categoryModel : categories) {
			int numberWhitespaces = longestCategoryId - String.valueOf(categoryModel.getPosition()).length();
			System.out.print(" " + categoryModel.getPosition() + fillWhitespaces(numberWhitespaces) + " | ");
			System.out.println(categoryModel.getCategory().getName());
		}
	}

	public static void printPurchases(List<PurchaseModel> purchases) {
		int longestPurchaseId = purchases.stream().mapToInt(p -> String.valueOf(p.getPostion()).length()).max().getAsInt();
		int longestPurchaseName = purchases.stream().mapToInt(p -> p.getPurchase().getName().length()).max().getAsInt();
		int longestPurchasePrice = purchases.stream().mapToInt(p -> String.valueOf(p.getPurchase().getPrice()).length()).max()
				.getAsInt();
		int longestPurchaseDate = purchases.stream().mapToInt(p -> p.getPurchase().getDateString().length()).max().getAsInt();

		int longestPurchaseCompany = purchases.stream()
				.mapToInt(p -> p.getPurchase().getCompany() == null ? 0 : p.getPurchase().getCompany().getName().length()).max().getAsInt();
		int longestPurchaseCategory = purchases.stream().mapToInt(p -> p.getPurchase().getCategory().getName().length()).max()
				.getAsInt();
		for (PurchaseModel purchaseModel : purchases) {
			printColumn(longestPurchaseId, String.valueOf(purchaseModel.getPostion()));
			printColumn(longestPurchaseName, purchaseModel.getPurchase().getName());
			printColumn(longestPurchasePrice, String.valueOf(purchaseModel.getPurchase().getPrice()));
			printColumn(longestPurchaseDate, purchaseModel.getPurchase().getDateString());
			if (purchaseModel.getPurchase().getCompany() == null) {
				printColumn(longestPurchaseCompany, "");
			} else {
				printColumn(longestPurchaseCompany, purchaseModel.getPurchase().getCompany().getName());
			}
			printColumn(longestPurchaseCategory, purchaseModel.getPurchase().getCategory().getName());
			System.out.println(purchaseModel.getPurchase().getDescription());
		}
		System.out.println();

	}

	public static void printColumn(int maxLength, String value) {
		int numberWhitespaces = maxLength - value.length();
		System.out.print(" " + value + fillWhitespaces(numberWhitespaces) + " |");
	}

	public static boolean saveData(String input, String action) {
		System.out.print(input);
		return (sc.nextLine().equals(action));
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
		System.out.print(question);

		Scanner sc = new Scanner(System.in);
		int action = sc.nextInt();
		System.out.println();
		
		return action;
	}
	
	public static boolean isValidInput(int input, int maxInput) {
		return input < maxInput && input > 0;
	}
	
	public static boolean validateDeleteOrDeactivation(String deleteOrDeactivate) {
		String input = inputDialogue("Löschung oder Deaktivierung durchführen (-)? ");
		println("");
		if (input.equals("-")) {
			println(deleteOrDeactivate);
			println("");
			return true;
		}
		return false;
	}
	
	public static boolean validateEnable(String activate) {
		String input = inputDialogue("Aktivierung durchführen (+)?");
		println("");
		if (input.equals("+")) {
			println(activate);
			println("");
			return true;
		}
		return false;
	}

}

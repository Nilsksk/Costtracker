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

	public static void printCompanies(List<Company> companies) {
		int longestCompanyId = companies.stream().mapToInt(c -> String.valueOf(c.getId()).length()).max().getAsInt();
		int longestCompanyName = companies.stream().mapToInt(c -> c.getName().length()).max().getAsInt();
		for (Company company : companies) {
			int numberWhitespaces = (longestCompanyId - String.valueOf(company.getId()).length());
			System.out.print(" " + company.getId() + DialogueHelper.fillWhitespaces(numberWhitespaces) + " | ");
			numberWhitespaces = (longestCompanyName - company.getName().length());
			System.out.print(company.getName() + DialogueHelper.fillWhitespaces(numberWhitespaces) + " | ");
			System.out.println(company.getLocation());
		}
	}

	public static void printCategories(List<Category> categories) {
		int longestCategoryId = categories.stream().mapToInt(c -> String.valueOf(c.getId()).length()).max().getAsInt();
		for (Category category : categories) {
			int numberWhitespaces = longestCategoryId - String.valueOf(category.getId()).length();
			System.out.print(" " + category.getId() + fillWhitespaces(numberWhitespaces) + " | ");
			System.out.println(category.getName());
		}
	}

	public static void printPurchases(List<Purchase> purchases) {
		int longestPurchaseId = purchases.stream().mapToInt(p -> String.valueOf(p.getId()).length()).max().getAsInt();
		int longestPurchaseName = purchases.stream().mapToInt(p -> p.getName().length()).max().getAsInt();
		int longestPurchasePrice = purchases.stream().mapToInt(p -> String.valueOf(p.getPrice()).length()).max()
				.getAsInt();
		int longestPurchaseDate = purchases.stream().mapToInt(p -> p.getDateString().length()).max().getAsInt();

		int longestPurchaseCompany = purchases.stream()
				.mapToInt(p -> p.getCompany() == null ? 0 : p.getCompany().getName().length()).max().getAsInt();
		int longestPurchaseCategory = purchases.stream().mapToInt(p -> p.getCategory().getName().length()).max()
				.getAsInt();
		for (Purchase purchase : purchases) {
			printColumn(longestPurchaseId, String.valueOf(purchase.getId()));
			printColumn(longestPurchaseName, purchase.getName());
			printColumn(longestPurchasePrice, String.valueOf(purchase.getPrice()));
			printColumn(longestPurchaseDate, purchase.getDateString());
			if (purchase.getCompany() == null) {
				printColumn(longestPurchaseCompany, "");
			} else {
				printColumn(longestPurchaseCompany, purchase.getCompany().getName());
			}
			printColumn(longestPurchaseCategory, purchase.getCategory().getName());
			System.out.println(purchase.getDescription());
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
	
	public static void validateCreation(boolean created) {
		if (created) {
			println("Angelegt!");
		}
		else {
			println("Anlegen fehlgeschlagen!");
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
		print("Löschung oder Deaktivierung durchführen (-)? ");
		String input = sc.nextLine();
		println("");
		if (input.equals("-")) {
			println(deleteOrDeactivate);
			println("");
			return true;
		}
		return false;
	}

}

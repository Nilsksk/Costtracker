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

	public static String changeDialogue(String input) {

		System.out.print(input + " -> ");
		return returnInput();
	}

	public static String returnInput() {
		return sc.nextLine();
	}

	public static int getIdDialogue(String input) {
		System.out.print(input + ": ");
		String ret;
		ret = sc.nextLine();
		return mapToId(ret);
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
		System.out.println();
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

	}

	public static void printColumn(int maxLength, String value) {
		int numberWhitespaces = maxLength - value.length();
		System.out.print(" " + value + fillWhitespaces(numberWhitespaces) + " | ");
	}

	public static boolean saveData(String input, String action) {
		System.out.print(input);
		return (sc.nextLine().equals(action));
	}
}

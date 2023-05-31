package costtracker.plugin.ui;

import java.util.Scanner;

public class DialogueHelper {

	private static Scanner sc = new Scanner(System.in);

	public static String addWhitespaces(int numberWhitespaces) {
		String whitespaces = "";
		for (int i = 0; i < numberWhitespaces; i++) {
			whitespaces += " ";
		}
		return whitespaces;
	}

	public static void printStartDialogueWith(String startingDialogue) {
		print(startingDialogue);
		sc.nextLine();
		printLine("");
	}

	public static String printInputDialogueWith(String input) {
		String output = input + ": ";
		print(output);
		return returnInputOfScanner();
	}

	public static String printChangeDialogueWith(String descriptionOfField, String input) {
		String output = "(" + descriptionOfField + ")" + " " + input + addWhitespaces(0) + " -> ";
		print(output);
		return returnInputOfScanner();
	}

	public static String returnInputOfScanner() {
		return sc.nextLine();
	}

	public static int printGetIdDialogueWith(String input) {
		String output = input + ": ";
		print(output);
		String id = sc.nextLine();
		return mapToIdFrom(id);
	}

	private static int mapToIdFrom(String ret) {
		try {
			return Integer.parseInt(ret);
		} catch (Exception e) {
			return -1;
		}
	}

	public static void print(String string) {
		System.out.print(string);
	}

	public static void printLine(String string) {
		System.out.println(string);
	}
	
	public static boolean submitEntry() {
		String save = "Zum Speichern ihrer Daten '+' eingeben: ";
		String input = "+";
		return printSaveDataDialogueWith(save, input);
	}

	public static boolean printSaveDataDialogueWith(String input, String action) {
		print(input);
		boolean output = sc.nextLine().equals(action);
		return output;
	}
	
	public static void printCreationDialogueWith(boolean created, String succesful, String unsuccesful) {
		if (created) {
			printLine(succesful);
		}
		else {
			printLine(unsuccesful);
		}
	}
	
	public static int printInteractQuestionWith(String question) {
		print(question);
		String action = sc.nextLine();		
		printLine("");
		return mapToIdFrom(action);	
	}
	
	public static boolean inputIsGreaterThanZeroAndBelowMaxInput(int input, int maxInput) {
		boolean valid = input < maxInput && input > 0;
		return valid;
	}
	
	public static boolean printDeleteOrDeactivateDialogueWith(String deleteOrDeactivate) {
		String question = "Löschung oder Deaktivierung durchführen (-)? ";
		String input = printInputDialogueWith(question);
		printLine("");
		boolean equal = input.equals("-");
		if (equal) {
			printLine(deleteOrDeactivate);
			printLine("");
			return true;
		}
		return false;
	}
	
	public static boolean printEnableDialogueWith(String activate) {
		String question = "Aktivierung durchführen (+)?";
		String input = printInputDialogueWith(question);
		printLine("");
		boolean equal = input.equals("+");
		if (equal) {
			printLine(activate);
			printLine("");
			return true;
		}
		return false;
	}

}

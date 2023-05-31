package costtracker.plugin.ui;

import java.util.Scanner;

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
		String action = sc.nextLine();		
		println("");
		return mapToId(action);	
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

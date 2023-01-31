package costtracker.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import costtracker.businessobjects.*;

public class Dialogue {

	private Editor editor;

	private Adder adder;

	public Dialogue() {

	}

	public void talk() {

		System.out.println("\tCosttracker!\n");

		String question = ("Was wollen sie machen? Firma oder Kategorie bearbeiten (1), Käufe hinzufügen (2), Käufe bearbeiten (3), Historie auslesen (4)");
		int action = interactQuestion(question);

		boolean validInput = false;
		while (!validInput) {

			if (!isValidInput(action, 4)) {
				System.out.println("Falsche Eingabe " + action + "!");
				continue;
			}
			if (action == 1) {			//Done
				validInput = true;
				edit();

			} else if (action == 2) {
				validInput = true;
				adder.add();
				

			} else if (action == 3) {
				validInput = true;

			} else if (action == 4) {
				validInput = true;

			}
		}
		System.out.println();
	}

	private boolean isValidInput(int input, int maxInput) {
		return input < maxInput && input > 0;
	}

	private void edit() {
		String question = ("Wollen sie eine Firma ändern (1) oder hinzufügen (2)? Wollen sie eine Kategorie ändern (3) oder hinzufügen (4)?");
		int action = interactQuestion(question);

		boolean validInput = false;
		while (!validInput) {
			if (!isValidInput(action, 4)) {
				System.out.println("Falsche Eingabe " + action + "!");
				continue;
			}
			if (action == 1) {
				validInput = true;
				editor = new CompanyManager();
				editor.edit();

			} else if (action == 2) {
				validInput = true;
				adder = new CompanyManager();
				adder.add();

			} else if (action == 3) {
				editor = new CategoryManager();
				editor.edit();

			} else if (action == 4) {
				validInput = true;
				adder = new CategoryManager();
				adder.add();
			}

		}
	}

	private int interactQuestion(String question) {
		System.out.print(question);

		Scanner sc = new Scanner(System.in);
		int action = sc.nextInt();
		System.out.println();

		return action;
	}
}

package costtracker.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import costtracker.businessobjects.*;
import costtracker.ui.interfaces.Adder;
import costtracker.ui.interfaces.Editor;

public class Dialogue {

	private Editor editor;
	private Adder adder;
	private History history;

	public Dialogue() {
		history = new History();
	}

	public void talk() throws SQLException {

		System.out.println("\tCosttracker!\n");
		do {
			boolean validInput = true;
			int action = interactQuestion("Was wollen sie machen? Firma oder Kategorie bearbeiten (1), Käufe hinzufügen (2), Käufe bearbeiten (3), Historie auslesen (4), Programm schließen (5)");	
			while (validInput) {
			
				if (!isValidInput(action, 6)) {
					System.out.println("Falsche Eingabe " + action + "!");
					break;
				}
				if (action == 1) {			//Done
					validInput = false;
					editCompanyOrCategory();
	
				} else if (action == 2) {	//Done
					validInput = false;
					adder = new PurchaseManager();
					adder.add();
					
	
				} else if (action == 3) {	//Done
					validInput = false;
					editor = new PurchaseManager();
					editor.edit();
	
				} else if (action == 4) {	//Done
					validInput = false;
					history.printHistory();
					
				} else if (action == 5) {
					break;
				}
			}
			if (action == 5) {
				DialogueHelper.println("Bis bald!");
				break;
			}
		}while(true);
	}

	private boolean isValidInput(int input, int maxInput) {
		return input < maxInput && input > 0;
	}

	private void editCompanyOrCategory() throws SQLException {
		do {
			int action = interactQuestion("Wollen sie eine Firma ändern (1) oder hinzufügen (2)? Wollen sie eine Kategorie ändern (3) oder hinzufügen (4)? Oder zurück gehen (5)?");
			boolean validInput = true;
			while (validInput) {
				if (!isValidInput(action, 6)) {
					System.out.println("Falsche Eingabe " + action + "!");
				}
				if (action == 1) {
					validInput = false;
					editor = new CompanyManager();
					editor.edit();
	
				} else if (action == 2) {
					validInput = false;
					adder = new CompanyManager();
					adder.add();
	
				} else if (action == 3) {
					validInput = false;
					editor = new CategoryManager();
					editor.edit();
	
				} else if (action == 4) {
					validInput = false;
					adder = new CategoryManager();
					adder.add();
				}
				
				else if (action == 5) {
					break;
				}
			}
			System.out.println();
			if (action == 5) {
				break;
			}
		}while(true);
	}

	private int interactQuestion(String question) {
		System.out.print(question);

		Scanner sc = new Scanner(System.in);
		int action = sc.nextInt();
		System.out.println();
		
		return action;
	}
}

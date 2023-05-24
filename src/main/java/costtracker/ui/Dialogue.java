package costtracker.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import costtracker.buisnesslogic.CompanyHandler;
import costtracker.businessobjects.*;
import costtracker.ui.interfaces.Adder;
import costtracker.ui.interfaces.Deactivator;
import costtracker.ui.interfaces.Editor;

public class Dialogue {

	private Editor editor;
	private Adder adder;
	private Deactivator deactivator;
	private History history;

	public Dialogue() {
		this.history = new History();
	}

	public void talk() throws SQLException {

		System.out.println("\tCosttracker!\n");
		do {
			boolean validInput = true;
			int action = DialogueHelper.interactQuestion("Was wollen sie machen? Firma oder Kategorie bearbeiten (1), Käufe hinzufügen (2), Käufe bearbeiten (3), Historie auslesen (4), "
													    + "Programm schließen (5)");	
			while (validInput) {
			
				if (!DialogueHelper.isValidInput(action, 6)) {
					DialogueHelper.println("Falsche Eingabe " + action + "!");
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
					historyAction();
					
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

	private void editCompanyOrCategory() throws SQLException {
		do {
			int action = DialogueHelper.interactQuestion("Wollen sie eine Firma ändern (1), hinzufügen (2) oder ausblenden (3)? "
													    + "Wollen sie eine Kategorie ändern (4), hinzufügen (5) oder ausblenden (6)? "
													    + "Oder zurück gehen (7)?");
			boolean validInput = true;
			while (validInput) {
				if (!DialogueHelper.isValidInput(action, 8)) {
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
	
				}else if (action == 3) {
					validInput = false;
					deactivator = new CompanyManager();
					deactivator.deactivate();
					
				} else if (action == 4) {
					validInput = false;
					editor = new CategoryManager();
					editor.edit();
	
				} else if (action == 5) {
					validInput = false;
					adder = new CategoryManager();
					adder.add();
				} else if (action == 6) {
					validInput = false;
					deactivator = new CategoryManager();
					deactivator.deactivate();
				}
					
				else if (action == 7) {
					break;
				}
			}
			System.out.println();
			if (action == 7) {
				break;
			}
		}while(true);
	}
	
	private void historyAction() throws SQLException {
		do {
			int action = DialogueHelper.interactQuestion("Wollen Sie ihre komplette Kaufhistorie (1), gefiltert nach einer bestimmten Zeitspanne (2), "
													    + "gefiltert nach Firma in Zeitspanne (3) oder gefiltert nach Kategorie in Zeitspanne (4)? "
													    + "Oder zurück gehen (5)?");
			boolean validInput = true;
			while (validInput) {
				if (!DialogueHelper.isValidInput(action, 6)) {
					System.out.println("Falsche Eingabe " + action + "!");
				}
				if (action == 1) {
					validInput = false;
					history.printHistory();
	
				} else if (action == 2) {
					validInput = false;
					timespanAction();
	
				}else if (action == 3) {
					validInput = false;
					history.printHistoryForCompanyInTimeframe();
					
				} else if (action == 4) {
					validInput = false;
					history.printHistoryForCategoryInTimeframe();
	
				} else if (action == 5) {
					validInput = false;
					break;
				}
			}
			System.out.println();
			if (action == 5) {
				break;
			}
		}while(true);
	}
	
	private void timespanAction() throws SQLException {
		do {
			int action = DialogueHelper.interactQuestion("Wollen Sie ihre komplette Kaufhistorie nach einem bestimmten Jahr (1), einem Monat (2), "
													    + "einer Woche (3) oder einer individuellen Zeitspanne (4)? "
													    + "Oder zurück gehen (5)?");
			boolean validInput = true;
			while (validInput) {
				if (!DialogueHelper.isValidInput(action, 6)) {
					System.out.println("Falsche Eingabe " + action + "!");
				}
				if (action == 1) {
					validInput = false;
					history.printHistoryByYear();
					
				} else if (action == 2) {
					validInput = false;
					history.printHistoryByMonth();
	
				}else if (action == 3) {
					validInput = false;
					history.printHistoryByWeek();

				} else if (action == 4) {
					validInput = false;
					history.printHistoryByTimespan();
				} else if (action == 5) {
					validInput = false;
					break;
				}
			}
			System.out.println();
			if (action == 5) {
				break;
			}
		}while(true);
	}
}

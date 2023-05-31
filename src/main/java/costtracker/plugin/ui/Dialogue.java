package costtracker.plugin.ui;

import java.sql.SQLException;

import costtracker.application.in.HistoryDocumentService;
import costtracker.plugin.ui.interfaces.Activator;
import costtracker.plugin.ui.interfaces.Adder;
import costtracker.plugin.ui.interfaces.Deactivator;
import costtracker.plugin.ui.interfaces.Editor;

public class Dialogue {

	private Editor editor;
	private Adder adder;
	private Deactivator deactivator;
	private Activator activator;
	
	public void talk() throws SQLException {

		String start = "\tCosttracker!\n";
		DialogueHelper.println(start);
		boolean continueLoop = true;
		while(continueLoop){
			continueLoop = interaction1();
		}
	}
	
	private boolean interaction1() throws SQLException {
		boolean continueLoop = true;
		boolean validInput = true;
		String question = "Was wollen sie machen? Firma oder Kategorie bearbeiten (1), Käufe hinzufügen (2), Käufe bearbeiten (3), Historie auslesen (4), "
			    + "Programm schließen (5)";
		int action = DialogueHelper.interactQuestion(question);	
		while (validInput) {
		
			boolean notValid = !DialogueHelper.isValidInput(action, 6);
			if (notValid) {
				String errorMsg = "Falsche Eingabe " + action + "!";
				DialogueHelper.println(errorMsg);
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
			String end = "Bis bald!";
			DialogueHelper.println(end);
			continueLoop = false;
		}
		return continueLoop;
	}

	private void editCompanyOrCategory() throws SQLException {
		do {
			String question = "Wollen sie eine Firma ändern (1), hinzufügen (2), ausblenden (3) oder einblenden (4)? "
				    + "Wollen sie eine Kategorie ändern (5), hinzufügen (6), ausblenden (7) oder einblenden (8)? "
				    + "Oder zurück gehen (9)?";
			int action = DialogueHelper.interactQuestion(question);
			boolean validInput = true;
			while (validInput) {
				boolean notValid = !DialogueHelper.isValidInput(action, 10);
				if (notValid) {
					String errorMsg = "Falsche Eingabe " + action + "!";
					System.out.println(errorMsg);
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
				}else if (action == 4) {
					validInput = false;
					activator = new CompanyManager();
					activator.activate();
				} else if (action == 5) {
					validInput = false;
					editor = new CategoryManager();
					editor.edit();
				} else if (action == 6) {
					validInput = false;
					adder = new CategoryManager();
					adder.add();
				} else if (action == 7) {
					validInput = false;
					deactivator = new CategoryManager();
					deactivator.deactivate();
				} else if (action == 8) {
					validInput = false;
					activator = new CategoryManager();
					activator.activate();
				}
					
				else if (action == 9) {
					break;
				}
				else{
					break;
				}
			}
			System.out.println();
			if (action == 9) {
				break;
			}
			else {
				break;
			}
		}while(true);
	}
	
	private void historyAction() throws SQLException {
		History history = new History();
		HistoryDocumentService historyDocumentService = new HistoryDocumentService();
		do {
			String question = "Wollen Sie ihre komplette Kaufhistorie (1), gefiltert nach einer bestimmten Zeitspanne (2), "
				    + "gefiltert nach Firma in Zeitspanne (3) gefiltert nach Kategorie in Zeitspanne (4)? Oder als Dokumentenausgabe (5)?"
				    + "Oder zurück gehen (6)?";
			int action = DialogueHelper.interactQuestion(question);
			boolean validInput = true;
			while (validInput) {
				boolean notValid = !DialogueHelper.isValidInput(action, 7);
				if (notValid) {
					String errorMsg = "Falsche Eingabe " + action + "!";
					System.out.println(errorMsg);
				}
				if (action == 1) {
					validInput = false;
					history.printHistory();
	
				} else if (action == 2) {
					validInput = false;
					timespanAction(history);
	
				}else if (action == 3) {
					validInput = false;
					history.printHistoryForCompanyInTimeframe();
					
				} else if (action == 4) {
					validInput = false;
					history.printHistoryForCategoryInTimeframe();
	
				}else if (action  == 5) {
					validInput = false;
					historyDocumentService.createHistoryDocument();
					
				} else if (action == 6) {
					validInput = false;
					break;
				}
			}
			System.out.println();
			if (action == 6) {
				break;
			}
		}while(true);
	}
	
	private void timespanAction(History history) throws SQLException {
		do {
			String question = "Wollen Sie ihre komplette Kaufhistorie nach einem bestimmten Jahr (1), einem Monat (2), "
				    + "einer Woche (3) oder einer individuellen Zeitspanne (4)? "
				    + "Oder zurück gehen (5)?";
			int action = DialogueHelper.interactQuestion(question);
			boolean validInput = true;
			while (validInput) {
				boolean notValid = !DialogueHelper.isValidInput(action, 6);
				if (notValid) {
					String errorMsg = "Falsche Eingabe " + action + "!";
					System.out.println(errorMsg);
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
			if (action == 5) {
				break;
			}
		}while(true);
	}
}

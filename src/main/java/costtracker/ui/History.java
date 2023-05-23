package costtracker.ui;

import java.sql.SQLException;

import costtracker.buisnesslogic.PurchaseHandler;

public class History {

	PurchaseHandler purchaseHandler;
	
	public History() {
		this.purchaseHandler = new PurchaseHandler();
	}
	
	public void printHistory() throws SQLException {
		DialogueHelper.startDialogue("Enter Taste drücken um Historie Ihrer Einkäufe anzuzeigen");
		DialogueHelper.printPurchases(purchaseHandler.getAll());
	}
}

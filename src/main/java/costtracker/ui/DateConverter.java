package costtracker.ui;

import java.time.LocalDate;

public class DateConverter {

	public static LocalDate convertDate(String input) {
		LocalDate date;

		String purchaseDate = DialogueHelper.inputDialogue(input);	
		String[] dateValues = purchaseDate.split(":");
		try {
			date = LocalDate.of(Integer.parseInt(dateValues[2]), Integer.parseInt(dateValues[1]), Integer.parseInt(dateValues[0]));		
		}
		catch(Exception e) {
			date = null;
		}
	
		return date;
	}
}

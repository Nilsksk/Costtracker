package costtracker.plugin.ui;

import java.time.LocalDate;

public class DateConverter {

	public static LocalDate convertDate(String input) {
		LocalDate date;
		String purchaseDate = DialogueHelper.inputDialogue(input);	
		String[] dateValues = purchaseDate.split(":");
		try {
			int day =  Integer.parseInt(dateValues[0]);
			int month = Integer.parseInt(dateValues[1]);
			int year = Integer.parseInt(dateValues[2]);
			date = LocalDate.of(year, month, day);		
		}
		catch(Exception e) {
			date = null;
		}
		return date;
	}
}

package costtracker.document.elements;

import java.time.LocalDate;
import java.util.ArrayList;

import costtracker.businessobjects.Purchase;

public class DateHistoryElement extends HistoryElementBase implements HistoryElement {

	private LocalDate date;
	
	public DateHistoryElement(LocalDate date) {
		this.date = date;
		this.purchases = new ArrayList<Purchase>();
	}
	
	@Override
	public String getHeader() {
		return date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
		
	}

}

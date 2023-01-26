package costtracker.document.printer;

import java.time.LocalDate;
import java.util.List;

import costtracker.document.elements.HistoryElement;

public interface Printer {
	
	boolean print(List<HistoryElement> element);
	
	void setTimespan(LocalDate dateStart, LocalDate dateEnd);
	
	void setPath(String path);
}

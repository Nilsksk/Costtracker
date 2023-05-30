package costtracker.adapter.in;

public interface HistoryElement {
	double getTotal();	
	String getHeader();
	String printWith(DocumentPrinter printer);
	String getType();
}

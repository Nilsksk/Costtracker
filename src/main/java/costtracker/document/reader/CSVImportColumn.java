package costtracker.document.reader;

import java.util.ArrayList;
import java.util.List;

public class CSVImportColumn {
	
	private String header;
	private List<String> data;
	
	public CSVImportColumn(String header) {
		this.data = new ArrayList<>();
		this.header = header.toLowerCase();
	}
	
	public void addRow(String entry) {
		this.data.add(entry);
	}
	
	public String pop() {
		return hasElements() ? this.data.remove(0) : null;
	}
	
	public String getHeader() {
		return this.header;
	}
	
	public boolean hasElements() {
		return this.data.size() > 0; 
	}
}

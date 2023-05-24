package costtracker.document.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CSVImportFile {

	private List<CSVImportColumn> columns;

	private List<String> lines;
	
	public CSVImportFile(File file) {
		if (!file.isFile())
			return;
		readLines(file);
		columns = new ArrayList<CSVImportColumn>();
		extractHeaderLine();
		readDataLines();	
	}

	private void readDataLines() {
		for (String dataLine : lines) {
			int currentPosition = 0;
			for (String column : dataLine.split(";")) {
				columns.get(currentPosition).addRow(column);
				currentPosition++;
			}
			if(currentPosition < columns.size()) {
				while(currentPosition < columns.size()) {
					columns.get(currentPosition).addRow("");
					currentPosition++;
				}
			}
		}		
	}

	private void extractHeaderLine() {
		String header = lines.remove(0);
		for (String column : header.split(";")) {
			if (column != "")
				columns.add(new CSVImportColumn(column));
		}
	}

	public boolean hasNextEntry() {
		return columns.stream().allMatch(c -> c.hasElements());
	}
	
	public HashMap<String, String> getNextEntry() {
		HashMap<String, String> purchase = new HashMap<>();
		for (CSVImportColumn column: columns) {
			purchase.put(column.getHeader(), column.pop());
		}
		return purchase;
	}
	
	private void readLines(File file) {
		lines = new ArrayList<>();
		try (BufferedReader breader = new BufferedReader(new FileReader(file))) {
			lines.addAll(breader.lines().toList());
			lines.removeIf(l -> l.equals(""));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

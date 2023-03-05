package costtracker.document.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVReader {
	
	public void readFile(File file) {
		if(!file.isFile())
			return;
		List<String> lines;
			try (BufferedReader breader = new BufferedReader(new FileReader(file))) {
				lines = breader.lines().toList();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
}

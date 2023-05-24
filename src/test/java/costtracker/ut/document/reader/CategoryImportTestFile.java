package costtracker.ut.document.reader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CategoryImportTestFile {
	public void create(String dataText) {
		File file = new File("TestCategoryImportFile.csv");
		if(file.exists())
			file.delete();
		try {
			file.createNewFile();
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
				writer.write("Name;");
				writer.newLine();
				writer.write(dataText);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

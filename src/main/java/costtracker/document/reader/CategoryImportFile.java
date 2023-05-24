package costtracker.document.reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import costtracker.businessobjects.Category;

public class CategoryImportFile {
	private CSVImportFile importFile;

	public CategoryImportFile(CSVImportFile importFile) {
		this.importFile = importFile;
	}
	
	public List<Category> getCategories() {
		List<Category> categories = new ArrayList<>();
		while (this.importFile.hasNextEntry()) {
			HashMap<String, String> entry = this.importFile.getNextEntry();
			String name = entry.get("name");
			Category category = new Category(0, name);
			categories.add(category);
		}
		return categories;
	}
}

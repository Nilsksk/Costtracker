package costtracker.application.out;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import costtracker.domain.businessobjects.Category;
import costtracker.domain.businessobjects.IncorrectEntryException;

public class CategoryImportFile {
	private ImportFile importFile;

	public CategoryImportFile(ImportFile importFile) {
		this.importFile = importFile;
	}
	
	public List<Category> getCategories() {
		List<Category> categories = new ArrayList<>();
		while (this.importFile.hasNextEntry()) {
			HashMap<String, String> entry = this.importFile.getNextEntry();
			String name = entry.get("name");
			Category category;
			try {
				category = Category.CategoryBuilder.withName(name).withId(0).build();
				categories.add(category);
			} catch (IncorrectEntryException e) {
				System.err.println("Wrong format for category: name = " + name);
			}
		}
		return categories;
	}
}

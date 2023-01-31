package costtracker.ui;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;

public class CategoryManager implements Editor, Adder {

	private List<Category> categories;

	@Override
	public void add() {
		DialogueHelper.startDialogue("Enter Taste drücken zum Hinzufügen einer neuen Kategorie");

		String newCategoryName = DialogueHelper.inputDialogue("Name");
	}
	// Hinzufügen einer neuen Kategorie in die Datenbank

	@Override
	public void edit() {
		DialogueHelper.startDialogue("Enter Taste drücken zum Anzeigen aller existierenden Einträge von Kategorien");

		DialogueHelper.printCategories(categories);

		Category category = getCategoryToEdit();

		String editedName = DialogueHelper.changeDialogue(category.getName());
		if (!editedName.isEmpty()) {
			category.setName(editedName);
		}

		// Kategorie mit editierter Kategorie überschreiben
	}

	private Category getCategoryToEdit() {
		Optional<Category> categoryO;
		do {
			int id = DialogueHelper.getIdDialogue("ID der Kategorie auswählen, die Sie bearbeiten möchten: ");
			final int idCopy = id;
			categoryO = categories.stream().filter(c -> c.getId() == idCopy).findFirst();
			if (id == -1) {
				DialogueHelper.println("Falsche Eingabe!");
				continue;
			}
			if (categoryO.isEmpty()) {
				DialogueHelper.println("ID " + id + " nicht vorhanden");
			}
		} while (categoryO.isEmpty());

		return categoryO.get();
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

}

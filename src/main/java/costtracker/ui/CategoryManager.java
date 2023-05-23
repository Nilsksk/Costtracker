package costtracker.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import costtracker.buisnesslogic.CategoryHandler;
import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;
import costtracker.ui.interfaces.Adder;
import costtracker.ui.interfaces.Editor;
import costtracker.ui.DialogueHelper;

public class CategoryManager implements Editor, Adder {

	private CategoryHandler categoryHandler;
	boolean submit;

	public CategoryManager() {
		this.categoryHandler = new CategoryHandler();
	}
	@Override
	public void add() throws SQLException {
		boolean created = false;
		DialogueHelper.startDialogue("Enter Taste drücken zum Hinzufügen einer neuen Kategorie");
		String newCategoryName = DialogueHelper.inputDialogue("Name");	
		submit = DialogueHelper.submitEntry();
		
		if(submit && !newCategoryName.isEmpty()) {
			created = categoryHandler.create(new Category(newCategoryName));			
			DialogueHelper.validateCreation(created);
		}
		else {
			DialogueHelper.print("Unzureichende Eingaben!");
		}
	}
	

	@Override
	public void edit() throws SQLException {
		boolean updated = false;
		DialogueHelper.startDialogue("Enter Taste drücken zum Anzeigen aller existierenden Einträge von Kategorien");

		DialogueHelper.printCategories(categoryHandler.getAll());

		Category category = getCategoryToEdit();

		String editedName = DialogueHelper.changeDialogue("Name", category.getName());
		if(editedName.isEmpty()) {
			editedName = category.getName();
		}
		else{
			category.setName(editedName);
		}
		submit = DialogueHelper.submitEntry();
		
		if(submit) {
			updated = categoryHandler.update(new Category(category.getId(), editedName));			
		}
		DialogueHelper.validateCreation(updated);
	}

	private Category getCategoryToEdit() throws SQLException {	
		int id;
		do {
			id = DialogueHelper.getIdDialogue("ID der Kategorie auswählen, die Sie bearbeiten möchten: ");
		}while(id == -1);
		Category category = GetBusinessObject.getById(id, categoryHandler.getAll(), Category::getId);

		return category;
	}

}

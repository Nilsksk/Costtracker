package costtracker.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import costtracker.buisnesslogic.CategoryHandler;
import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;
import costtracker.ui.interfaces.Activator;
import costtracker.ui.interfaces.Adder;
import costtracker.ui.interfaces.Deactivator;
import costtracker.ui.interfaces.Editor;
import costtracker.ui.DialogueHelper;

public class CategoryManager implements Editor, Adder, Deactivator, Activator {

	private CategoryHandler categoryHandler;
	private CategoryModelFactory categoryModelFactory;
	boolean submit;

	public CategoryManager() {
		this.categoryHandler = new CategoryHandler();
		this.categoryModelFactory = new CategoryModelFactory();
	}
	@Override
	public void add() throws SQLException {
		boolean created = false;
		DialogueHelper.startDialogue("Enter Taste drücken zum Hinzufügen einer neuen Kategorie");
		String newCategoryName = DialogueHelper.inputDialogue("Name");	
		submit = DialogueHelper.submitEntry();
		
		if(submit && !newCategoryName.isEmpty()) {
			created = categoryHandler.create(new Category(0, newCategoryName));
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

		DialogueHelper.printCategories(categoryModelFactory.createCategoryModels(categoryHandler.getAll()));

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

	@Override
	public void deactivate() throws SQLException {
		DialogueHelper.startDialogue("Enter Taste drücken zum Anzeigen aller existierenden Einträge von Kategorien");
		DialogueHelper.printCategories(categoryModelFactory.createCategoryModels(categoryHandler.getEnabled()));
		Category category = getCategoryToEdit();
		if (category != null) {
			List<Category> categoryToDisable= new ArrayList<Category>();
			categoryToDisable.add(category);
			DialogueHelper.printCategories(categoryModelFactory.createCategoryModels(categoryToDisable));
			if(DialogueHelper.validateDeleteOrDeactivation("Erfolgreich deaktiviert!")) {
				categoryHandler.disable(category.getId());
			}			
		}
	}
	
	@Override
	public void activate() {
		
	}
	
	private Category getCategoryToEdit() throws SQLException {	
		Category category;
		try {
			int id = DialogueHelper.getIntDialogue("ID der Kategorie auswählen, die Sie bearbeiten möchten");
			category = categoryHandler.getById(id);	
		}catch(Exception e) {
			
			category = null;
		}
		return category;
	}
}

package costtracker.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import costtracker.buisnesslogic.CategoryHandler;
import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;
import costtracker.businessobjects.IncorrectEntryException;
import costtracker.ui.interfaces.Activator;
import costtracker.ui.interfaces.Adder;
import costtracker.ui.interfaces.Deactivator;
import costtracker.ui.interfaces.Editor;
import costtracker.ui.DialogueHelper;

public class CategoryManager implements Editor, Adder, Deactivator, Activator {

	private boolean submit;
	
	@Override
	public void add() throws SQLException {
		boolean created = false;
		CategoryHandler categoryHandler = new CategoryHandler();
		String addCategory = "Enter Taste drücken zum Hinzufügen einer neuen Kategorie";
		DialogueHelper.startDialogue(addCategory);
		String name = "Name";
		String newCategoryName = DialogueHelper.inputDialogue(name);	
		submit = DialogueHelper.submitEntry();
		try {
			Category category = Category.CategoryBuilder
					.withName(newCategoryName)
					.withId(0)
					.build();
			created = categoryHandler.create(category);		
		} catch (IncorrectEntryException e) {
			String insufficientInput = "Unzureichende Eingabe!";
			DialogueHelper.println(insufficientInput);
		}
		if(submit && created) {
			String succesful = "Angelegt!";
			String unsuccesful = "Anlegen fehlgeschlagen!";
			DialogueHelper.validateCreation(created, succesful, unsuccesful);
		}
	}

	@Override
	public void edit() throws SQLException {
		boolean updated = false;
		CategoryModelFactory categoryModelFactory = new CategoryModelFactory();
		CategoryHandler categoryHandler = new CategoryHandler();
		String printCategories = "Enter Taste drücken zum Anzeigen aller existierenden Einträge von Kategorien";
		DialogueHelper.startDialogue(printCategories);
		List<Category> allCategories = categoryHandler.getAll();
		List<CategoryModel> allCategoryModels = categoryModelFactory.createCategoryModels(allCategories);
		DialogueHelper.printCategories(allCategoryModels);
		Category category = getCategoryToEdit(categoryHandler);
		String categoryName = category.getName();
		String name = "Name";
		String editedName = DialogueHelper.changeDialogue(name, categoryName);
		boolean nameIsEmpty = editedName.isEmpty();
		if(nameIsEmpty) {
			editedName = category.getName();
		}
		else{
			category.setName(editedName);
		}
		submit = DialogueHelper.submitEntry();
		try {
			category = Category.CategoryBuilder
					.withName(editedName)
					.withId(0)
					.build();
			updated = categoryHandler.update(category);		
		} catch (IncorrectEntryException e) {
			String error = "Fehler!";
			DialogueHelper.println(error);
		}
		if(submit) {
			String succesful = "Erfolgreich bearbeitet!";
			String unsuccesful = "Bearbeiten fehlgeschlagen!";
			DialogueHelper.validateCreation(updated, succesful, unsuccesful);					
		}
	}

	@Override
	public void deactivate() throws SQLException {
		CategoryModelFactory categoryModelFactory = new CategoryModelFactory();
		CategoryHandler categoryHandler = new CategoryHandler();
		String printAllCategories = "Enter Taste drücken zum Anzeigen aller existierenden Einträge von Kategorien";
		DialogueHelper.startDialogue(printAllCategories);
		List<Category> enabledCategories = categoryHandler.getEnabled();
		List<CategoryModel> enabledCategoryModels = categoryModelFactory.createCategoryModels(enabledCategories);
		DialogueHelper.printCategories(enabledCategoryModels);
		Category category = getCategoryToEdit(categoryHandler);
		if (category != null) {
			// Hier weiter machen mit schön machen!
			List<Category> categoryToDisable= new ArrayList<Category>();
			categoryToDisable.add(category);
			DialogueHelper.printCategories(categoryModelFactory.createCategoryModels(categoryToDisable));
			succesfulDeactivated(category, categoryHandler);	
		}
	}
	
	private void succesfulDeactivated(Category category, CategoryHandler categoryHandler) throws SQLException {
		String succes = "Erfolgreich deaktiviert!";
		if(DialogueHelper.validateDeleteOrDeactivation(succes)) {
			int id = category.getId();
			categoryHandler.disable(id);
		}		
	}
	
	@Override
	public void activate() throws SQLException {
		CategoryModelFactory categoryModelFactory = new CategoryModelFactory();
		CategoryHandler categoryHandler = new CategoryHandler();
		String deactivatedCategories = "Enter Taste drücken zum Anzeigen aller existierenden Einträge von deaktivierten Kategorien";
		DialogueHelper.startDialogue(deactivatedCategories);
		List<Category> disabledCategories = categoryHandler.getDisabled();
		List<CategoryModel> disabledCategoryModels = categoryModelFactory.createCategoryModels(disabledCategories);
		DialogueHelper.printCategories(disabledCategoryModels);
		Category category = getCategoryToEdit(categoryHandler);
		if (category != null) {
			List<Category> categoryToEnable = new ArrayList<Category>();
			categoryToEnable.add(category);
			DialogueHelper.printCategories(categoryModelFactory.createCategoryModels(categoryToEnable));
			succesfulActivated(category, categoryHandler);
		}
	}
	
	private void succesfulActivated(Category category, CategoryHandler categoryHandler) throws SQLException {
		if(DialogueHelper.validateEnable("Erfolgreich aktiviert!")) {
			categoryHandler.enable(category.getId());
		}	
	}
	
	private Category getCategoryToEdit(CategoryHandler categoryHandler) throws SQLException {	
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

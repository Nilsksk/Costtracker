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
import costtracker.ui.CategoryModel.CategoryModelBuilder;
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
		boolean correct = submit && created;
		if(correct) {
			String succesful = "Angelegt!";
			String unsuccesful = "Anlegen fehlgeschlagen!";
			DialogueHelper.validateCreation(created, succesful, unsuccesful);
		}
	}

	@Override
	public void edit() throws SQLException {
		CategoryModelFactory categoryModelFactory = new CategoryModelFactory();
		CategoryHandler categoryHandler = new CategoryHandler();
		String printCategories = "Enter Taste drücken zum Anzeigen aller existierenden Einträge von Kategorien";
		DialogueHelper.startDialogue(printCategories);
		List<Category> allCategories = categoryHandler.getAll();
		List<CategoryModel> allCategoryModels = categoryModelFactory.createCategoryModels(allCategories);
		boolean moreThenZeroModels = allCategoryModels.size() > 0;
		if (moreThenZeroModels) {
			editCategory(allCategoryModels, categoryHandler);
		}
	}

	private void editCategory(List<CategoryModel> allCategoryModels, CategoryHandler categoryHandler) throws SQLException {
		DialogueHelper.printCategories(allCategoryModels);
		String idCategory = "ID der Kategorie die Sie bearbeiten möchten";
		Category category = getCategoryToEdit(categoryHandler, idCategory);
		boolean isNotNull = category != null;
		if (isNotNull) {
			editIfNotNull(category, categoryHandler);
		}
	}
	
	private void editIfNotNull(Category category, CategoryHandler categoryHandler) throws SQLException {
		boolean updated = false;
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
			int id = category.getId();
			category = Category.CategoryBuilder
					.withName(editedName)
					.withId(id)
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
		boolean moreThenZeroModels = enabledCategoryModels.size() > 0;
		if (moreThenZeroModels) {
			DialogueHelper.printCategories(enabledCategoryModels);
			String idCategory = "ID der Kategorie die sie deaktiviveren möchten";
			Category category = getCategoryToEdit(categoryHandler, idCategory);
			deactivateCategory(category, categoryModelFactory, categoryHandler);
		}
	}
	
	private void deactivateCategory(Category category, CategoryModelFactory categoryModelFactory, CategoryHandler categoryHandler) throws SQLException {
		boolean categoryIsNotNull = category != null;
		if (categoryIsNotNull) {
			List<Category> categoryToDisable= new ArrayList<Category>();
			categoryToDisable.add(category);
			List<CategoryModel> categoriesToDisable = categoryModelFactory.createCategoryModels(categoryToDisable);
			DialogueHelper.printCategories(categoriesToDisable);
			succesfulDeactivated(category, categoryHandler);	
		}
	}
	
	private void succesfulDeactivated(Category category, CategoryHandler categoryHandler) throws SQLException {
		String succes = "Erfolgreich deaktiviert!";
		boolean hasSucceded = DialogueHelper.validateDeleteOrDeactivation(succes);
		if(hasSucceded) {
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
		boolean moreThenZeroModels = disabledCategoryModels.size() > 0;
		if(moreThenZeroModels) {
			DialogueHelper.printCategories(disabledCategoryModels);
			String idCategory = "ID der Firma die Sie aktivieren möchten";
			Category category = getCategoryToEdit(categoryHandler, idCategory);
			activateCategory(category, categoryModelFactory, categoryHandler);	
		}
	}
	
	private void activateCategory(Category category, CategoryModelFactory categoryModelFactory, CategoryHandler categoryHandler) throws SQLException {
		boolean categoryIsNotNull = category != null;
		if (categoryIsNotNull) {
			List<Category> categoryToEnable = new ArrayList<Category>();
			categoryToEnable.add(category);
			List<CategoryModel> categoriesToEnable = categoryModelFactory.createCategoryModels(categoryToEnable);
			DialogueHelper.printCategories(categoriesToEnable);
			succesfulActivated(category, categoryHandler);
		}	
	}
	
	private void succesfulActivated(Category category, CategoryHandler categoryHandler) throws SQLException {
		boolean activated = DialogueHelper.validateEnable("Erfolgreich aktiviert!"); 
		if(activated) {
			int id = category.getId();
			categoryHandler.enable(id);
		}	
	}
	
	private Category getCategoryToEdit(CategoryHandler categoryHandler, String input) throws SQLException {	
		Category category;
		try {
			int id = DialogueHelper.getIntDialogue(input);
			category = categoryHandler.getById(id);	
		}catch(Exception e) {
			category = null;
		}
		return category;
	}
}

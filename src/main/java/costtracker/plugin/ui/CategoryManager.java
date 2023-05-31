package costtracker.plugin.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import costtracker.application.handlers.CategoryHandler;
import costtracker.domain.businessobjects.Category;
import costtracker.domain.businessobjects.IncorrectEntryException;
import costtracker.plugin.ui.interfaces.Activator;
import costtracker.plugin.ui.interfaces.Adder;
import costtracker.plugin.ui.interfaces.Deactivator;
import costtracker.plugin.ui.interfaces.Editor;


public class CategoryManager implements Editor, Adder, Deactivator, Activator {

	private boolean submit;
	
	@Override
	public void add() throws SQLException {
		boolean created = false;
		CategoryHandler categoryHandler = new CategoryHandler();
		String addCategory = "Enter Taste drücken zum Hinzufügen einer neuen Kategorie";
		DialogueHelper.printStartDialogueWith(addCategory);
		String name = "Name";
		String newCategoryName = DialogueHelper.printInputDialogueWith(name);	
		submit = DialogueHelper.submitEntry();
		try {
			Category category = Category.CategoryBuilder
					.withName(newCategoryName)
					.withId(0)
					.build();
			created = categoryHandler.create(category);		
		} catch (IncorrectEntryException e) {
			String insufficientInput = "Unzureichende Eingabe!";
			DialogueHelper.printLine(insufficientInput);
		}
		boolean correct = submit && created;
		if(correct) {
			String succesful = "Angelegt!";
			String unsuccesful = "Anlegen fehlgeschlagen!";
			DialogueHelper.printCreationDialogueWith(created, succesful, unsuccesful);
		}
	}

	@Override
	public void edit() throws SQLException {
		CategoryModelFactory categoryModelFactory = new CategoryModelFactory();
		CategoryHandler categoryHandler = new CategoryHandler();
		String printCategories = "Enter Taste drücken zum Anzeigen aller existierenden Einträge von Kategorien";
		DialogueHelper.printStartDialogueWith(printCategories);
		List<Category> allCategories = categoryHandler.getAll();
		List<CategoryModel> allCategoryModels = categoryModelFactory.createCategoryModels(allCategories);
		boolean moreThenZeroModels = allCategoryModels.size() > 0;
		if (moreThenZeroModels) {
			editCategory(allCategoryModels, categoryHandler);
		}
	}

	private void editCategory(List<CategoryModel> allCategoryModels, CategoryHandler categoryHandler) throws SQLException {
		CategoryPrinter.printCategories(allCategoryModels);
		String idCategory = "ID der Kategorie die Sie bearbeiten möchten";
		Category category = getCategoryToEdit(allCategoryModels, idCategory);
		boolean isNotNull = category != null;
		if (isNotNull) {
			editIfNotNull(category, categoryHandler);
		}
	}
	
	private void editIfNotNull(Category category, CategoryHandler categoryHandler) throws SQLException {
		boolean updated = false;
		String categoryName = category.getName();
		String name = "Name";
		String editedName = DialogueHelper.printChangeDialogueWith(name, categoryName);
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
			DialogueHelper.printLine(error);
		}
		if(submit) {
			String succesful = "Erfolgreich bearbeitet!";
			String unsuccesful = "Bearbeiten fehlgeschlagen!";
			DialogueHelper.printCreationDialogueWith(updated, succesful, unsuccesful);					
		}
	}
	
	@Override
	public void deactivate() throws SQLException {
		CategoryModelFactory categoryModelFactory = new CategoryModelFactory();
		CategoryHandler categoryHandler = new CategoryHandler();
		String printAllCategories = "Enter Taste drücken zum Anzeigen aller existierenden Einträge von Kategorien";
		DialogueHelper.printStartDialogueWith(printAllCategories);
		List<Category> enabledCategories = categoryHandler.getEnabled();
		List<CategoryModel> enabledCategoryModels = categoryModelFactory.createCategoryModels(enabledCategories);
		boolean moreThenZeroModels = enabledCategoryModels.size() > 0;
		if (moreThenZeroModels) {
			deactivateCategory(categoryModelFactory, categoryHandler, enabledCategoryModels);
		}
	}
	
	private void deactivateCategory(CategoryModelFactory categoryModelFactory, CategoryHandler categoryHandler, List<CategoryModel> enabledCategoryModels) throws SQLException {
		CategoryPrinter.printCategories(enabledCategoryModels);
		String idCategory = "ID der Kategorie die sie deaktiviveren möchten";
		Category category = getCategoryToEdit(enabledCategoryModels, idCategory);
		boolean categoryIsNotNull = category != null;
		if (categoryIsNotNull) {
			List<Category> categoryToDisable= new ArrayList<>();
			categoryToDisable.add(category);
			List<CategoryModel> categoriesToDisable = categoryModelFactory.createCategoryModels(categoryToDisable);
			CategoryPrinter.printCategories(categoriesToDisable);
			succesfulDeactivated(category, categoryHandler);	
		}
	}
	
	private void succesfulDeactivated(Category category, CategoryHandler categoryHandler) throws SQLException {
		String succes = "Erfolgreich deaktiviert!";
		boolean hasSucceded = DialogueHelper.printDeleteOrDeactivateDialogueWith(succes);
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
		DialogueHelper.printStartDialogueWith(deactivatedCategories);
		List<Category> disabledCategories = categoryHandler.getDisabled();
		List<CategoryModel> disabledCategoryModels = categoryModelFactory.createCategoryModels(disabledCategories);
		boolean moreThenZeroModels = disabledCategoryModels.size() > 0;
		if(moreThenZeroModels) {
			CategoryPrinter.printCategories(disabledCategoryModels);
			String idCategory = "ID der Firma die Sie aktivieren möchten";
			Category category = getCategoryToEdit(disabledCategoryModels, idCategory);
			activateCategory(category, categoryModelFactory, categoryHandler);	
		}
	}
	
	private void activateCategory(Category category, CategoryModelFactory categoryModelFactory, CategoryHandler categoryHandler) throws SQLException {
		boolean categoryIsNotNull = category != null;
		if (categoryIsNotNull) {
			List<Category> categoryToEnable = new ArrayList<Category>();
			categoryToEnable.add(category);
			List<CategoryModel> categoriesToEnable = categoryModelFactory.createCategoryModels(categoryToEnable);
			CategoryPrinter.printCategories(categoriesToEnable);
			succesfulActivated(category, categoryHandler);
		}	
	}
	
	private void succesfulActivated(Category category, CategoryHandler categoryHandler) throws SQLException {
		boolean activated = DialogueHelper.printEnableDialogueWith("Erfolgreich aktiviert!"); 
		if(activated) {
			int id = category.getId();
			categoryHandler.enable(id);
		}	
	}
	
	private Category getCategoryToEdit(List<CategoryModel> categoryModels, String input) throws SQLException {	
		Category category;
		try {
			int id = DialogueHelper.printGetIdDialogueWith(input);
			CategoryModel categoryModel = categoryModels.stream().filter(c -> c.getPosition() == id).findAny().orElse(null);
			category = categoryModel.getCategory();
		}catch(Exception e) {
			category = null;
		}
		return category;
	}
}

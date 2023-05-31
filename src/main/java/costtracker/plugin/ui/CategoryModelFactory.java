package costtracker.plugin.ui;

import java.util.ArrayList;
import java.util.List;

import costtracker.domain.businessobjects.Category;

public class CategoryModelFactory {

	private List<CategoryModel> categoryModels = new ArrayList<>();
	
	public List<CategoryModel> createCategoryModels(List<Category> categories){
		try {
			return CreateModels(categories);		
		}catch(Exception e) {
			categoryModels.clear();
			return null;
		}
	}
	
	private List<CategoryModel> CreateModels(List<Category> categories) {
		for (int i = 1; i <= categories.size(); i++) {
			addCategoryToList(categories, i);
		}
		return categoryModels;
	}
	
	private void addCategoryToList (List<Category> categories, int i) {
		try {
			CategoryModel categoryModel = CategoryModel.CategoryModelBuilder.withCategory(categories.get(i-1)).withPosition(i).build();
			categoryModels.add(categoryModel);
		} catch (Exception e) {
			
			DialogueHelper.println("Fehler!");
		}
	}
}

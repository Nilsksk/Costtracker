package costtracker.ui;

import java.util.ArrayList;
import java.util.List;

import costtracker.businessobjects.Category;

public class CategoryModelFactory {

	private List<CategoryModel> categoryModels;
	
	public CategoryModelFactory() {
		this.categoryModels = new ArrayList<>();
	}
	
	public List<CategoryModel> createCategoryModels(List<Category> categories){
		try {
			for (int i = 1; i <= categories.size(); i++) {
				CategoryModel categoryModel = new CategoryModel(i, categories.get(i-1));
				categoryModels.add(categoryModel);
			}
			return categoryModels;			
		}catch(Exception e) {
			categoryModels.clear();
			return null;
		}
	}
}

package costtracker.ui;

import costtracker.businessobjects.Category;

public class CategoryModel {

	private Category category;
	private int position;
	
	public CategoryModel(int position, Category category) {
		this.position = position;
		this.category = category;
	}
	
	public int getPosition(){
		return position;
	}
	
	public Category getCategory() {
		return category;
	}
}

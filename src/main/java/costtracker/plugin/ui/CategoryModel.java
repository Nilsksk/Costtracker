package costtracker.plugin.ui;

import costtracker.domain.businessobjects.Category;

public class CategoryModel {

	private Category category;
	private int position;
	
	public int getPosition(){
		return position;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public CategoryModel(CategoryModelBuilder categoryModelBuilder) {
		this.category = categoryModelBuilder.category;
		this.position = categoryModelBuilder.position;
	}
	
	public static class CategoryModelBuilder {
		
		private Category category;
		private int position;
		
		public CategoryModelBuilder(Category category) {
			this.category = category;
		}

		public static CategoryModelBuilder withCategory(Category category) {
			return new CategoryModelBuilder(category);
		}
		
		public CategoryModelBuilder withPosition(int position) {
			this.position = position;
			return this;
		}
		
		public CategoryModel build() {
			return new CategoryModel(this);
		}
	}
}



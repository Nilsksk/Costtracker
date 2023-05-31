package costtracker.plugin.ui;

import java.util.List;

public class CategoryPrinter {

	public static void printCategories(List<CategoryModel> categories) {
		boolean moreThenZeroCategories = categories.size() > 0;
		if (moreThenZeroCategories) {
			int longestCategoryId = categories
								.stream()
								.mapToInt(c -> String
								.valueOf(c.getPosition())
								.length()).max()
								.getAsInt();
			categoryPrinter(categories, longestCategoryId);
		}
	}
	
	private static void categoryPrinter(List<CategoryModel> categories, int longestCategoryId) {
		for (CategoryModel categoryModel : categories) {
			int numberWhitespaces = longestCategoryId - String
													.valueOf(categoryModel
													.getPosition())
													.length();
			String printId = " " + categoryModel.getPosition() + DialogueHelper.addWhitespaces(numberWhitespaces) + " | ";
			DialogueHelper.print(printId);
			String printName = categoryModel.getCategory().getName();
			DialogueHelper.printLine(printName);
		}
	}
}

package costtracker.ut.ui;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import costtracker.businessobjects.Category;
import costtracker.ui.CategoryManager;

public class CategoryManagerTest {

	@Test
	void test() {
		CategoryManager cm = new CategoryManager();
		
		List<Category> categories = new ArrayList<Category>();
		categories.add(new Category(1, "Essen"));
		categories.add(new Category(2, "Alkohol"));
		categories.add(new Category(3, "Lego"));
		categories.add(new Category(4, "Lautre"));
		
		cm.setCategories(categories);
		
		cm.edit();
	}
}

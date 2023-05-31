package costtracker.ut.ui;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import costtracker.domain.businessobjects.Category;
import costtracker.domain.businessobjects.IncorrectEntryException;

public class CategoryManagerTest {

	@Test
	void test() throws IncorrectEntryException {
		//CategoryManager cm = new CategoryManager();
		
		List<Category> categories = new ArrayList<Category>();
		categories.add(Category.CategoryBuilder.withName("Essen").withId(1).build());
		categories.add(Category.CategoryBuilder.withName("Alkohol").withId(2).build());
		categories.add(Category.CategoryBuilder.withName("Lego").withId(3).build());
		categories.add(Category.CategoryBuilder.withName("Lautre").withId(4).build());
		
		//cm.setCategories(categories);
		
		//cm.edit();
	}
}

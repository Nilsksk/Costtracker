package costtracker.ut.businessobjects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import costtracker.businessobjects.Category;
import costtracker.db.entities.CategoryEntity;

class CategoryUnitTest {

	@Test
	void testFromEntity() {
		int id = 1;
		String name = "name";
		CategoryEntity entity = new CategoryEntity(id, name); 
		Category category = Category.fromEntity(entity);
		
		assertEquals(id, category.getId());
		assertEquals(name, category.getName());
	}
	
	@Test
	void testToEntity() {
		int id = 1;
		String name = "name";
		Category category = new Category(id, name); 
		CategoryEntity entity = category.toEntity();
		
		assertEquals(id, entity.getId());
		assertEquals(name, entity.getName());
	}

}

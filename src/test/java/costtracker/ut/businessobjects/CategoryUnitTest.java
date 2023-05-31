package costtracker.ut.businessobjects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import costtracker.adapter.entities.CategoryEntity;
import costtracker.adapter.mappers.EntityMapper;
import costtracker.domain.businessobjects.Category;
import costtracker.domain.businessobjects.IncorrectEntryException;

class CategoryUnitTest {

	@Test
	void testFromEntity() {
		int id = 1;
		String name = "name";
		CategoryEntity entity = new CategoryEntity(id, name); 
		Category category = EntityMapper.toBo(entity);
		
		assertEquals(id, category.getId());
		assertEquals(name, category.getName());
	}
	
	@Test
	void testToEntity() throws IncorrectEntryException {
		int id = 1;
		String name = "name";
		Category category = Category.CategoryBuilder
				.withName(name)
				.withId(id)
				.build();
		CategoryEntity entity = EntityMapper.toEntity(category);
		
		assertEquals(id, entity.getId());
		assertEquals(name, entity.getName());
	}

}

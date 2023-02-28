package costtracker.businessobjects;

import costtracker.db.entities.CategoryEntity;

public class Category {
	
	private int id;	
	private String name;

	public Category(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static Category fromEntity(CategoryEntity entity) {
		return new Category(entity.getId(), entity.getName());
	}
	
	public CategoryEntity toEntity() {
		return new CategoryEntity(this.id, this.name);
	}
}

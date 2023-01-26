package costtracker.db.entities;

public class CategoryEntity extends EntityBase {
	
	private String name;

	public CategoryEntity(String name) {
		this(0,name);
	}

	public CategoryEntity(int id) {
		this(id, null);
	}
	
	public CategoryEntity(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

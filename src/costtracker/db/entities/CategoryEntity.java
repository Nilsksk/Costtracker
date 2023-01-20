package costtracker.db.entities;

public class CategoryEntity extends EntityBase {
	
	private String name;

	public CategoryEntity(String name) {
		super();
		this.name = name;
	}

	public CategoryEntity(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

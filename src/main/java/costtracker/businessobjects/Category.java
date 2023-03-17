package costtracker.businessobjects;

import costtracker.db.entities.CategoryEntity;
import org.json.JSONObject;

public class Category {
	
	private int id;	
	private String name;
	private String isEnabled;

	public Category(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Category(String name) {
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

	@Override
	public String toString() {
		return "Category{" + "id=" + id + ", name='" + name + '}';
	}

	public JSONObject toJSON(){
		return new JSONObject().put("id", id).put("name", name);
	}

	public static Category fromJSONToCategory(JSONObject object){
		return new Category(
				Integer.parseInt(object.getJSONObject("category").get("id").toString()),
				object.getJSONObject("category").get("name").toString()
		);
	}
}

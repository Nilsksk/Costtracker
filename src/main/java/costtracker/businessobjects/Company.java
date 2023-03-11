package costtracker.businessobjects;

import costtracker.db.entities.CompanyEntity;
import org.json.JSONObject;

public class Company {
	
	private int id;
	private String name;
	private String location;
	
	public Company(int id, String name, String location) {
		this.id = id;
		this.name = name;
		this.location = location;
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
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public static Company fromEntity(CompanyEntity entity) {
		return new Company(entity.getId(), entity.getName(), entity.getLocation());
	}
	
	public CompanyEntity toEntity() {
		return new CompanyEntity(id, name, location);
	}

	@Override
	public String toString() {
		return "Company{" + "id=" + id + ", name='" + name + ", location='" + location + '}';
	}

	public JSONObject toJSON(){
		return new JSONObject().put("id", id).put("name", name).put("location", location);
	}

	public static Company fromJSONToCompany(JSONObject object){
		return new Company(
				Integer.parseInt(object.getJSONObject("company").get("id").toString()),
				object.getJSONObject("company").get("name").toString(),
				object.getJSONObject("company").get("location").toString()
		);
	}
}

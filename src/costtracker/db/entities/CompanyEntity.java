package costtracker.db.entities;

public class CompanyEntity extends EntityBase {
	
	private String name;
	private String location;

	public CompanyEntity(String name, String location) {
		this.name = name;
		this.location = location;
	}
	
	public CompanyEntity(int id) {
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
}

package costtracker.plugin.db.entities;

public class CompanyEntity extends EntityBase {

	private String name;
	private String location;

	public CompanyEntity(String name) {
		this(name, null);
	}

	public CompanyEntity(int id) {
		this.id = id;
	}

	public CompanyEntity(String name, String location) {
		this.name = name;
		this.location = location;
	}

	public CompanyEntity(int id, String name) {
		this(name, null);
		this.id = id;
	}

	public CompanyEntity(int id, String name, String location) {
		this(name, location);
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

package costtracker.domain.businessobjects;

import org.json.JSONException;
import org.json.JSONObject;

import costtracker.plugin.db.entities.CompanyEntity;

public class Company {

	private int id;
	private String name;
	private String location;

	@Deprecated
	/**
	 * Please use the builder pattern instead
	 * @param id
	 * @param name
	 * @param location
	 */
	public Company(int id, String name, String location) {
		this.id = id;
		this.name = name;
		this.location = location;
	}

	public Company(CompanyBuilder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.location = builder.location;
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
		try {
			return CompanyBuilder
					.withName(entity.getName())
					.withId(entity.getId())
					.withLocation(entity.getLocation())
					.build();
		} catch (IncorrectEntryException e) {
			return null;
		}
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
		try {
			return  CompanyBuilder.withName(object.getJSONObject("company").get("name").toString())
					.withId(Integer.parseInt(object.getJSONObject("company").get("id").toString()))
					.withLocation(object.getJSONObject("company").get("location").toString())
					.build();
		} catch (NumberFormatException e) {
			return null;
		} catch (JSONException e) {
			return null;
		} catch (IncorrectEntryException e) {
			return null;
		}
	}

	public static class CompanyBuilder{
		private String name;
		private String location;
		private int id;

		private CompanyBuilder(String name) {
			this.name=name;
		}

		public static CompanyBuilder withName(String name) {
			return new CompanyBuilder(name);
		}

		public CompanyBuilder withId(int id) {
			this.id = id;
			return this;
		}

		public CompanyBuilder withLocation(String location) {
			this.location = location;
			return this;
		}

		public Company build() throws IncorrectEntryException {
			Company company = new Company(this);
			validateCompany(company);
			return company;
		}

		private void validateCompany(Company company) throws IncorrectEntryException {
			if(name == null||name.isBlank())
				throw new IncorrectEntryException("Incorrect entry for Name: " + name + "!");

		}
	}
}
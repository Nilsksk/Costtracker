package costtracker.businessobjects;

import costtracker.db.entities.CategoryEntity;

import org.json.JSONException;
import org.json.JSONObject;

public class Category {

	private int id;
	private String name;

	@Deprecated
	/**
	 * Please use the builder pattern
	 * @param id
	 * @param name
	 */
	public Category(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Category(CategoryBuilder builder) {
		this.id = builder.id;
		this.name = builder.name;
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
		try {
			return CategoryBuilder
					.withName(entity.getName())
					.withId(entity.getId())
					.build();
		} catch (IncorrectEntryException e) {
			return null;
		}
	}

	public CategoryEntity toEntity() {
		return new CategoryEntity(this.id, this.name);
	}

	@Override
	public String toString() {
		return "Category{" + "id=" + id + ", name='" + name + '}';
	}

	public JSONObject toJSON() {
		return new JSONObject().put("id", id).put("name", name);
	}

	public static Category fromJSONToCategory(JSONObject object) {
		try {
			return CategoryBuilder
					.withName(object.getJSONObject("category").get("name").toString())
					.withId(Integer.parseInt(object.getJSONObject("category").get("id").toString()))
					.build();
		} catch (NumberFormatException e) {
			return null;
		} catch (JSONException e) {
			return null;
		} catch (IncorrectEntryException e) {
			return null;
		}
	}

	public static class CategoryBuilder {
		private String name;
		private int id;

		private CategoryBuilder(String name) {
			this.name = name;
		}

		public static CategoryBuilder withName(String name) {
			return new CategoryBuilder(name);
		}

		public CategoryBuilder withId(int id) {
			this.id = id;
			return this;
		}

		public Category build() throws IncorrectEntryException {
			Category category = new Category(this);
			validateCategory(category);
			return category;
		}

		private void validateCategory(Category category) throws IncorrectEntryException {
			if (name == null || name.isBlank())
				throw new IncorrectEntryException("Incorrect entry for Name: " + name + "!");

		}
	}
}
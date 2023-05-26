package costtracker.businessobjects;

import java.sql.Date;
import java.time.LocalDate;

import costtracker.db.entities.PurchaseEntity;
import org.json.JSONObject;

public class Purchase {

	private int id;
	private String name;
	private String description;
	private LocalDate date;
	private double price;
	private Company company;
	private Category category;

	@Deprecated()
	/**
	 * Please use the builder pattern instead
	 * 
	 * @param id
	 * @param name
	 * @param description
	 * @param date
	 * @param price
	 * @param company
	 * @param category
	 */
	public Purchase(int id, String name, String description, LocalDate date, double price, Company company,
			Category category) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.date = date;
		this.price = price;
		this.company = company;
		this.category = category;
	}

	private Purchase(PurchaseBuilder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.description = builder.description;
		this.price = builder.price;
		this.date = builder.date;
		this.company = builder.company;
		this.category = builder.category;
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
		if (name != null && !name.isBlank())
			this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		if (date != null)
			this.date = date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		if (price > 0)
			this.price = price;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		if (category != null)
			this.category = category;
	}

	public String getDateString() {
		return date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
	}

	public static Purchase fromEntity(PurchaseEntity entity) {
		try {
			return PurchaseBuilder
					.withValues(entity.getName(), entity.getDate().toLocalDate(), entity.getPrice())
					.withId(entity.getId())
					.withCategory(Category.fromEntity(entity.getCategory()))
					.withCompany(entity.getCompany() != null ? Company.fromEntity(entity.getCompany()) : null)
					.withDescription(entity.getDescription()).build();
		} catch (IncorrectEntryException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public PurchaseEntity toEntity() {
		return new PurchaseEntity(id, company != null ? company.toEntity() : null, category.toEntity(), price, name,
				description, Date.valueOf(date));
	}

	public JSONObject toJSON() {
		return new JSONObject().put("id", id).put("name", name).put("description", description).put("date", date)
				.put("price", price).put("category", category.toJSON()).put("company", company.toJSON());
	}

	public static class PurchaseBuilder {

		public static PurchaseBuilder withValues(String name, LocalDate date, double price) {
			return new PurchaseBuilder(name, date, price);
		}

		public PurchaseBuilder withId(int id) {
			this.id = id;
			return this;
		}

		public PurchaseBuilder withCategory(Category category) {
			this.category = category;
			return this;
		}

		public PurchaseBuilder withDescription(String description) {
			this.description = description;
			return this;
		}

		public PurchaseBuilder withCompany(Company company) {
			this.company = company;
			return this;
		}

		private int id;
		private String name;
		private String description;
		private LocalDate date;
		private double price;
		private Category category;
		private Company company;

		private PurchaseBuilder(String name, LocalDate date, double price) {
			this.name = name;
			this.date = date;
			this.price = price;
		}

		public Purchase build() throws IncorrectEntryException {
			Purchase purchase = new Purchase(this);
			validateObject(purchase);
			return purchase;
		}

		private void validateObject(Purchase purchase) throws IncorrectEntryException {
			if (name == null || name.isBlank())
				throw new IncorrectEntryException("Incorrect entry for Name: " + name + "!");
			if (price <= 0.0)
				throw new IncorrectEntryException("Price can not be equal or lower than zero!");
			if (date == null)
				throw new IncorrectEntryException("Date is in wrong format!");
			if (category == null)
				throw new IncorrectEntryException("No valid category entered!");
		}

	}
}

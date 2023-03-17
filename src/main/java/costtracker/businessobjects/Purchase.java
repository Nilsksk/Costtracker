package costtracker.businessobjects;

import java.sql.Date;
import java.time.LocalDate;

import costtracker.db.entities.PurchaseEntity;
import org.json.JSONArray;
import org.json.JSONObject;

public class Purchase {

	private int id;
	private String name;
	private String description;
	private LocalDate date;
	private double price;
	private Company company;
	private Category category;

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
	
	public Purchase(String name, String description, LocalDate date, double price, Company company, Category category) {
		this.name = name;
		this.description = description;
		this.date = date;
		this.price = price;
		this.company = company;
		this.category = category;
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
		this.date = date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
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
		this.category = category;
	}

	public String getDateString() {
		return date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
	}

	public static Purchase fromEntity(PurchaseEntity entity) {
		return new Purchase(entity.getId(), entity.getName(), entity.getDescription(), entity.getDate().toLocalDate(),
				entity.getPrice(), entity.getCompany() != null ? Company.fromEntity(entity.getCompany()) : null,
				Category.fromEntity(entity.getCategory()));
	}

	public PurchaseEntity toEntity() {
		return new PurchaseEntity(id, company != null ? company.toEntity() : null, category.toEntity(), price, name,
				description, Date.valueOf(date));
	}

	public JSONObject toJSON() {
		return new JSONObject().put("id", id).put("name", name).put("description", description).put("date", date).put("price", price)
						.put("category", category.toJSON()).put("company", company.toJSON());
	}
}

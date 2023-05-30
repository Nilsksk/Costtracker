package costtracker.plugin.db.entities;

import java.sql.Date;

public class PurchaseEntity extends EntityBase {

	private CompanyEntity company;
	private CategoryEntity category;
	private double price;
	private String name;
	private String description;
	private Date date;

	
	public PurchaseEntity(int id, int categoryid, double price, String name, Date date) {
		this(id, new CategoryEntity(categoryid), price, name, date);
	}

	public PurchaseEntity(int id, CategoryEntity category, double price, String name, Date date) {
		this(id, null, category, price, name, null, date);
	}
	
	public PurchaseEntity(int id, CategoryEntity category, double price, String name, String description, Date date) {
		this(id, null, category, price, name, description, date);
	}

	public PurchaseEntity(int id, int companyid, int categoryid, double price, String name, Date date) {
		this(id, new CompanyEntity(companyid), new CategoryEntity(categoryid), price, name, date);
	}
	
	public PurchaseEntity(int id, CompanyEntity companyEntity, CategoryEntity categoryEntity, double price, String name,
			Date date) {
		this(id, companyEntity, categoryEntity, price, name, null, date);
	}

	public PurchaseEntity(int id, int companyid, int categoryid, double price, String name, String description,
			Date date) {
		this(id, new CompanyEntity(companyid), new CategoryEntity(categoryid), price, name, description, date);
	}
	
	public PurchaseEntity(int id, CompanyEntity company, CategoryEntity category, double price, String name,
			String description, Date date) {
		this(company, category, price, name, description, date);
		this.id = id;
	}
	
	public PurchaseEntity(int categoryid, double price, String name, Date date) {
		this(new CategoryEntity(categoryid), price, name, date);
	}
	public PurchaseEntity(CategoryEntity category, double price, String name, String description, Date date) {
		this(null, category, price, name, description, date);
	}

	public PurchaseEntity(int categoryid, double price, String name, String description, Date date) {
		this(new CategoryEntity(categoryid), price, name, description, date);
	}

	public PurchaseEntity(CategoryEntity category, double price, String name, Date date) {
		this(null, category, price, name, null, date);
	}

//	TODO
//	public PurchaseEntity(int id, int categoryid, double price, String name, String description, Date date) {
//		this(id, new CategoryEntity(categoryid),price, name, description,date);
//	}

	public PurchaseEntity(int companyid, int categoryid, double price, String name, String description, Date date) {
		this(new CompanyEntity(companyid), new CategoryEntity(categoryid), price, name, description, date);
	}
	

	public PurchaseEntity(CompanyEntity companyEntity, CategoryEntity categoryEntity, double price, String name,
			Date date) {
		this(companyEntity, categoryEntity, price, name, null, date);
	}


	public PurchaseEntity(CompanyEntity company, CategoryEntity category, double price, String name, String description,
			Date date) {
		this.company = company;
		this.category = category;
		this.price = price;
		this.name = name;
		this.description = description;
		this.date = date;
	}
	
	public CompanyEntity getCompany() {
		return company;
	}

	public void setCompany(CompanyEntity company) {
		this.company = company;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}

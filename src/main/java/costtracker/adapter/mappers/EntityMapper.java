package costtracker.adapter.mappers;

import java.sql.Date;

import costtracker.adapter.entities.CategoryEntity;
import costtracker.adapter.entities.CompanyEntity;
import costtracker.adapter.entities.PurchaseEntity;
import costtracker.domain.businessobjects.Category;
import costtracker.domain.businessobjects.Company;
import costtracker.domain.businessobjects.IncorrectEntryException;
import costtracker.domain.businessobjects.Purchase;
import costtracker.domain.businessobjects.Purchase.PurchaseBuilder;

public class EntityMapper {
	public static CategoryEntity toEntity(Category category) {
		return new CategoryEntity(category.getId(), category.getName());
	}
	
	public static Category toBo(CategoryEntity categoryEntity) {
		Category category = null;
		try {
			category = Category.CategoryBuilder.withName(categoryEntity.getName()).withId(categoryEntity.getId()).build();
		} catch (IncorrectEntryException e) {
			//TODO add logging
		}
		return category;
	}
	
	public static CompanyEntity toEntity(Company company) {
		return new CompanyEntity(company.getId(), company.getName(), company.getLocation());
	}
	
	public static Company toBo(CompanyEntity companyEntity) {
		Company company = null;
		try {
			company = Company.CompanyBuilder.withName(companyEntity.getName()).withId(companyEntity.getId()).withLocation(companyEntity.getLocation()).build();
		} catch (IncorrectEntryException e) {
			//TODO add logging
		}
		return company;
	}
	
	public static PurchaseEntity toEntity(Purchase purchase) {
		return new PurchaseEntity(purchase.getId(), purchase.getCompany() != null ? toEntity(purchase.getCompany()) : null, toEntity(purchase.getCategory()), purchase.getPrice(), purchase.getName(),
				purchase.getDescription(), Date.valueOf(purchase.getDate()));
	}
	
	public static Purchase toBo(PurchaseEntity purchaseEntity) {
		Purchase purchase = null;
		try {
			purchase = PurchaseBuilder
					.withValues(purchaseEntity.getName(), purchaseEntity.getDate().toLocalDate(), purchaseEntity.getPrice())
					.withId(purchaseEntity.getId())
					.withCategory(toBo(purchaseEntity.getCategory()))
					.withCompany(purchaseEntity.getCompany() != null ? toBo(purchaseEntity.getCompany()) : null)
					.withDescription(purchaseEntity.getDescription()).build();
		} catch (IncorrectEntryException e) {
			//TODO add logging
		}
		return purchase;
	}
}

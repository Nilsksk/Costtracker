package costtracker.adapter.persistence;

import costtracker.adapter.entities.CategoryEntity;
import costtracker.adapter.entities.CompanyEntity;
import costtracker.adapter.entities.PurchaseEntity;

public interface UnitOfWork extends AutoCloseable {
	
	public void save();

	public BaseDataRepository<CategoryEntity> getCategoryRepository();
	
	public DataRepository<PurchaseEntity> getPurchaseRepository();
	
	public BaseDataRepository<CompanyEntity> getCompanyRepository();

	public void ensureCreated();
	
	@Override
	public void close();

}

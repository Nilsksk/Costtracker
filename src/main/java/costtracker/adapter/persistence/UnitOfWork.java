package costtracker.adapter.persistence;

import java.sql.SQLException;

import costtracker.adapter.entities.CategoryEntity;
import costtracker.adapter.entities.CompanyEntity;
import costtracker.adapter.entities.PurchaseEntity;

public interface UnitOfWork extends AutoCloseable {
	
	public void save();

	public BaseDataRepository<CategoryEntity> getCategoryRepository();
	
	public DataRepository<PurchaseEntity> getPurchaseRepository();
	
	public BaseDataRepository<CompanyEntity> getCompanyRepository();
	
	@Override
	void close() throws SQLException;
}

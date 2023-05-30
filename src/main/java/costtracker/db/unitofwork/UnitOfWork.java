package costtracker.db.unitofwork;

import java.sql.SQLException;

import costtracker.db.repositories.CategoryRepository;
import costtracker.db.repositories.CompanyRepository;
import costtracker.db.repositories.PurchaseRepository;

public interface UnitOfWork extends AutoCloseable {
	
	public void Save();

	public CategoryRepository getCategoryRepository();
	
	public PurchaseRepository getPurchaseRepository();
	
	public CompanyRepository getCompanyRepository();
	
	@Override
	void close() throws SQLException;
}

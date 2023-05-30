package costtracker.plugin.db.unitofwork;

import java.sql.SQLException;

import costtracker.plugin.db.repositories.CategoryRepository;
import costtracker.plugin.db.repositories.CompanyRepository;
import costtracker.plugin.db.repositories.PurchaseRepository;

public interface UnitOfWork extends AutoCloseable {
	
	public void Save();

	public CategoryRepository getCategoryRepository();
	
	public PurchaseRepository getPurchaseRepository();
	
	public CompanyRepository getCompanyRepository();
	
	@Override
	void close() throws SQLException;
}

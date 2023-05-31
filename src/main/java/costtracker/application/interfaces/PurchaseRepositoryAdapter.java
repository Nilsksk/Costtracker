package costtracker.application.interfaces;

import java.time.LocalDate;
import java.util.List;

import costtracker.domain.businessobjects.Category;
import costtracker.domain.businessobjects.Company;
import costtracker.domain.businessobjects.Purchase;

public interface PurchaseRepositoryAdapter {
	public Purchase get(int id);

	public boolean update(Purchase purchase);

	public boolean insert(Purchase purchase);

	public boolean delete(int id);

	public List<Purchase> getAll();

	public List<Purchase> getByTimespan(LocalDate start, LocalDate end);

	public List<Purchase> getByCategoryByTimespan(Category category, LocalDate start, LocalDate end);

	public List<Purchase> getByCompanyByTimespan(Company company, LocalDate start, LocalDate end);
}

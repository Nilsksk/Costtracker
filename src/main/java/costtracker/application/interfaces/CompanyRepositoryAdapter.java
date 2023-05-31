package costtracker.application.interfaces;

import java.util.List;

import costtracker.domain.businessobjects.Company;

public interface CompanyRepositoryAdapter {
	
	public Company get(int id);

	public boolean update(Company company);

	public boolean insert(Company company);

	public boolean delete(int id);
	
	public List<Company> getAll();

	public boolean enable(int id);

	public boolean disable(int id);

	public List<Company> getEnabled();

	public List<Company> getDisabled();
}

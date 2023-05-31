package costtracker.application.handlers;

import costtracker.application.handlers.interfaces.DatabaseHandler;
import costtracker.application.handlers.interfaces.StateHandler;
import costtracker.application.interfaces.CompanyRepositoryAdapter;
import costtracker.domain.businessobjects.Company;
import costtracker.domain.dependencyinjection.DependencyContainer;

import java.util.List;

public class CompanyHandler implements DatabaseHandler<Company>, StateHandler<Company> {

	private CompanyRepositoryAdapter companyRepositoryAdapter = DependencyContainer.getInstance().getDependency(CompanyRepositoryAdapter.class);
	
	@Override
	public Company getById(int id) {
		return companyRepositoryAdapter.get(id);
	}

	@Override
	public List<Company> getAll() {
		return companyRepositoryAdapter.getAll();
	}

	@Override
	public boolean deleteById(int id) {
		return companyRepositoryAdapter.delete(id);
	}

	@Override
	public boolean update(Company company) {
		return companyRepositoryAdapter.update(company);
	}

	@Override
	public boolean create(Company company) {
		return companyRepositoryAdapter.insert(company);
	}

	@Override
	public boolean enable(int id) {
		return companyRepositoryAdapter.enable(id);
	}

	@Override
	public boolean disable(int id) {
		return companyRepositoryAdapter.disable(id);
	}
	@Override
	public List<Company> getEnabled() {
		return companyRepositoryAdapter.getEnabled();
	}

	@Override
	public List<Company> getDisabled() {
		return companyRepositoryAdapter.getDisabled();
	}
}

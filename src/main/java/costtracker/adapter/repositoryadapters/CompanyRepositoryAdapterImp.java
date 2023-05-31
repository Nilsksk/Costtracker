package costtracker.adapter.repositoryadapters;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import costtracker.adapter.entities.CompanyEntity;
import costtracker.adapter.mappers.EntityMapper;
import costtracker.adapter.persistence.UnitOfWork;
import costtracker.application.interfaces.CompanyRepositoryAdapter;
import costtracker.domain.businessobjects.Company;
import costtracker.domain.dependencyinjection.DependencyContainer;

public class CompanyRepositoryAdapterImp implements CompanyRepositoryAdapter {

	private UnitOfWork uow = DependencyContainer.getInstance().getDependency(UnitOfWork.class);
	public CompanyRepositoryAdapterImp() {
	
	}

	@Override
	public Company get(int id) {
		Company company = null;
		try {
			CompanyEntity companyEntity = uow.getCompanyRepository().get(id);
			company = EntityMapper.toBo(companyEntity);
		} catch (SQLException e) {
			// TODO Addlogging
		}
		return company;
	}

	@Override
	public boolean update(Company company) {
		CompanyEntity companyEntity = EntityMapper.toEntity(company);
		boolean ret = false;
		try {
			ret = uow.getCompanyRepository().update(companyEntity);
			if(ret)
				uow.save();
		} catch (SQLException e) {
			// TODO Addlogging
		}
		return ret;
	}

	@Override
	public boolean insert(Company company) {
		CompanyEntity companyEntity = EntityMapper.toEntity(company);
		boolean ret = false;
		try {
			ret = uow.getCompanyRepository().insert(companyEntity);
			if(ret)
				uow.save();
		} catch (SQLException e) {
			// TODO Addlogging
		}
		return ret;
	}

	@Override
	public boolean delete(int id) {
		boolean ret = false;
		try {
			ret = uow.getCompanyRepository().delete(id);
			if(ret)
				uow.save();
		} catch (SQLException e) {
			// TODO Addlogging
		}
		return ret;
	}

	@Override
	public List<Company> getAll() {
		List<Company> companies = new ArrayList<>();
		try {
			List<CompanyEntity> entities = uow.getCompanyRepository().getAll();
			for (CompanyEntity companyEntity : entities) {
				Company company = EntityMapper.toBo(companyEntity);
				companies.add(company);
			}
		} catch (SQLException e) {
			// TODO Addlogging
		}
		return companies;
	}

	@Override
	public boolean enable(int id) {
		boolean ret = false;
		try {
			ret = uow.getCompanyRepository().enable(id);
			if(ret)
				uow.save();
		} catch (SQLException e) {
			// TODO Addlogging
		}
		return ret;
	}

	@Override
	public boolean disable(int id) {
		boolean ret = false;
		try {
			ret = uow.getCompanyRepository().disable(id);
			if(ret)
				uow.save();
		} catch (SQLException e) {
			// TODO Addlogging
		}
		return ret;
	}

	@Override
	public List<Company> getEnabled() {
		List<Company> companies = new ArrayList<>();
		try {
			List<CompanyEntity> entities = uow.getCompanyRepository().getEnabled();
			for (CompanyEntity companyEntity : entities) {
				Company company = EntityMapper.toBo(companyEntity);
				companies.add(company);
			}
		} catch (SQLException e) {
			// TODO Addlogging
		}
		return companies;
	}

	@Override
	public List<Company> getDisabled() {
		List<Company> companies = new ArrayList<>();
		try {
			List<CompanyEntity> entities = uow.getCompanyRepository().getDisabled();
			for (CompanyEntity companyEntity : entities) {
				Company company = EntityMapper.toBo(companyEntity);
				companies.add(company);
			}
		} catch (SQLException e) {
			// TODO Addlogging
		}
		return companies;
	}

}

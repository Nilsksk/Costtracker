package costtracker.application.handlers;

import costtracker.application.handlers.interfaces.DatabaseHandler;
import costtracker.application.handlers.interfaces.StateHandler;
import costtracker.domain.businessobjects.Company;
import costtracker.plugin.db.entities.CompanyEntity;
import costtracker.plugin.db.unitofwork.UnitOfWork;
import costtracker.plugin.db.unitofwork.UnitOfWorkImp;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyHandler implements DatabaseHandler<Company>, StateHandler<Company> {

	@Override
	public Company getById(int id) throws SQLException {
		try (UnitOfWork uow = new UnitOfWorkImp()) {

			CompanyEntity companyEntity = uow.getCompanyRepository().get(id);
			return Company.fromEntity(companyEntity);
		}
	}

	@Override
	public List<Company> getAll() throws SQLException {
		try (UnitOfWork uow = new UnitOfWorkImp()) {

			List<CompanyEntity> companyEntityList = uow.getCompanyRepository().getAll();

			return companyEntityList.stream().map(Company::fromEntity).collect(Collectors.toList());
		}
	}

	@Override
	public boolean deleteById(int id) throws SQLException {
		try (UnitOfWork uow = new UnitOfWorkImp()) {

			boolean state = uow.getCompanyRepository().delete(id);

			if (state) {
				uow.Save();
			}

			return state;
		}
	}

	@Override
	public boolean update(Company company) throws SQLException {
		try (UnitOfWork uow = new UnitOfWorkImp()) {

			boolean state = uow.getCompanyRepository().update(company.toEntity());

			if (state) {
				uow.Save();
			}

			return state;
		}
	}

	@Override
	public boolean create(Company company) throws SQLException {
		try (UnitOfWork uow = new UnitOfWorkImp()) {

			boolean state = uow.getCompanyRepository().insert(company.toEntity());

			if (state) {
				uow.Save();
			}

			return state;
		}
	}

	@Override
	public boolean enable(int id) throws SQLException {
		try (UnitOfWork uow = new UnitOfWorkImp()) {

			boolean state = uow.getCompanyRepository().enable(id);

			if (state) {
				uow.Save();
			}

			return state;
		}
	}

	@Override
	public boolean disable(int id) throws SQLException {
		try (UnitOfWork uow = new UnitOfWorkImp()) {

			boolean state = uow.getCompanyRepository().disable(id);

			if (state) {
				uow.Save();
			}

			return state;
		}
	}

	@Override
	public List<Company> getEnabled() throws SQLException {
		try (UnitOfWork uow = new UnitOfWorkImp()) {

			List<CompanyEntity> companyEntityList = uow.getCompanyRepository().getEnabled();

			return companyEntityList.stream().map(Company::fromEntity).collect(Collectors.toList());
		}
	}

	@Override
	public List<Company> getDisabled() throws SQLException {
		try (UnitOfWork uow = new UnitOfWorkImp()) {

			List<CompanyEntity> companyEntityList = uow.getCompanyRepository().getDisabled();

			return companyEntityList.stream().map(Company::fromEntity).collect(Collectors.toList());
		}
	}
}

package costtracker.buisnesslogic;

import costtracker.buisnesslogic.interfaces.DatabaseHandler;
import costtracker.buisnesslogic.interfaces.StateHandler;
import costtracker.businessobjects.Company;
import costtracker.db.entities.CompanyEntity;
import costtracker.db.unitofwork.UnitOfWorkImp;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyHandler implements DatabaseHandler<Company>, StateHandler<Company> {
    UnitOfWorkImp uow = new UnitOfWorkImp();

    @Override
    public Company getById(int id) throws SQLException {
        CompanyEntity companyEntity = uow.getCompanyRepository().get(id);
        return Company.fromEntity(companyEntity);
    }

    @Override
    public List<Company> getAll() throws SQLException {
        List<CompanyEntity> companyEntityList = uow.getCompanyRepository().getAll();

        return companyEntityList.stream().map(Company::fromEntity).collect(Collectors.toList());
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        boolean state = uow.getCompanyRepository().delete(id);

        if (state) {
            uow.Save();
        }

        return state;
    }

    @Override
    public boolean update(Company company) throws SQLException {
        boolean state = uow.getCompanyRepository().update(company.toEntity());

        if (state) {
            uow.Save();
        }

        return state;
    }

    @Override
    public boolean create(Company company) throws SQLException {
        boolean state = uow.getCompanyRepository().insert(company.toEntity());

        if (state) {
            uow.Save();
        }

        return state;
    }

    @Override
    public boolean enable(int id) throws SQLException {
        boolean state = uow.getCompanyRepository().enable(id);

        if (state) {
            uow.Save();
        }

        return state;
    }

    @Override
    public boolean disable(int id) throws SQLException {
        boolean state = uow.getCompanyRepository().disable(id);

        if (state) {
            uow.Save();
        }

        return state;
    }

    @Override
    public List<Company> getEnabled() throws SQLException {
        List<CompanyEntity> companyEntityList = uow.getCompanyRepository().getEnabled();

        return companyEntityList.stream().map(Company::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<Company> getDisabled() throws SQLException {
        List<CompanyEntity> companyEntityList = uow.getCompanyRepository().getDisabled();

        return companyEntityList.stream().map(Company::fromEntity).collect(Collectors.toList());
    }
}

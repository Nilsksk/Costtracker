package costtracker.ut.buisnesslogic;

import costtracker.adapter.persistence.UnitOfWork;
import costtracker.plugin.db.repositories.PurchaseRepository;

import java.sql.SQLException;

public class UnitOfWorkMock implements UnitOfWork {

    @Override
    public void save() {

    }

    @Override
    public CategoryRepositoryMock getCategoryRepository() {
        return new CategoryRepositoryMock();
    }

    @Override
    public PurchaseRepository getPurchaseRepository() {
        return null;
    }

    @Override
    public CompanyRepositoryMock getCompanyRepository() {
        return new CompanyRepositoryMock();
    }

    @Override
    public void close() throws SQLException {

    }
}

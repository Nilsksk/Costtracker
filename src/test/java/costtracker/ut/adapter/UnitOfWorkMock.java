package costtracker.ut.adapter;

import costtracker.adapter.persistence.UnitOfWork;

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
    public PurchaseRepositoryMock getPurchaseRepository() {
        return new PurchaseRepositoryMock();
    }

    @Override
    public CompanyRepositoryMock getCompanyRepository() {
        return new CompanyRepositoryMock();
    }

    @Override
    public void close() throws SQLException {

    }
}

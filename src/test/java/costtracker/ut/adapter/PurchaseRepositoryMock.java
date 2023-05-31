package costtracker.ut.adapter;

import costtracker.adapter.entities.CategoryEntity;
import costtracker.adapter.entities.CompanyEntity;
import costtracker.adapter.entities.PurchaseEntity;
import costtracker.adapter.persistence.DataRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PurchaseRepositoryMock implements DataRepository<PurchaseEntity> {
    CompanyRepositoryMock companyRepositoryMock = new CompanyRepositoryMock();
    CategoryRepositoryMock categoryRepositoryMock = new CategoryRepositoryMock();
    List<PurchaseEntity> purchaseEntities = new ArrayList<>();

    public PurchaseRepositoryMock() {
        purchaseEntities.add(new PurchaseEntity(1, companyRepositoryMock.get(2), categoryRepositoryMock.get(1), 23.5, "TestEinkauf1", Date.valueOf("2023-01-01")));
        purchaseEntities.add(new PurchaseEntity(2, companyRepositoryMock.get(1), categoryRepositoryMock.get(3), 24.5, "TestEinkauf2", Date.valueOf("2023-01-01")));
        purchaseEntities.add(new PurchaseEntity(3, companyRepositoryMock.get(4), categoryRepositoryMock.get(2), 25.5, "TestEinkauf3", Date.valueOf("2023-01-01")));
        purchaseEntities.add(new PurchaseEntity(4, companyRepositoryMock.get(3), categoryRepositoryMock.get(4), 26.5, "TestEinkauf4", Date.valueOf("2023-01-01")));
    }

    @Override
    public PurchaseEntity get(int id) {
        return purchaseEntities.stream().filter(c -> c.getId() == id).findFirst().get();
    }

    @Override
    public boolean update(PurchaseEntity entity) {
        PurchaseEntity updateEntity = purchaseEntities.stream().filter(c -> c.getId() == entity.getId()).findFirst().get();
        purchaseEntities.remove(updateEntity);
        return purchaseEntities.add(entity);
    }

    @Override
    public boolean insert(PurchaseEntity entity) {
        return purchaseEntities.add(entity);
    }

    @Override
    public boolean delete(int id) {
        PurchaseEntity delEntity = purchaseEntities.stream().filter(c -> c.getId() == id).findFirst().get();
        return purchaseEntities.remove(delEntity);
    }

    @Override
    public List<PurchaseEntity> getAll() {
        return purchaseEntities;
    }

    @Override
    public List<PurchaseEntity> getByTimespan(Date start, Date end) {

        return purchaseEntities.stream().filter(c -> c.getDate().after(start) && c.getDate().before(end)).toList();
    }

    @Override
    public List<PurchaseEntity> getByCategoryByTimespan(CategoryEntity category, Date start, Date end) {

        return purchaseEntities.stream().filter(c -> c.getCategory().equals(category) && c.getDate().after(start) && c.getDate().before(end)).toList();
    }

    @Override
    public List<PurchaseEntity> getByCompanyByTimespan(CompanyEntity company, Date start, Date end) {

        return purchaseEntities.stream().filter(c -> c.getCompany().equals(company) && c.getDate().after(start) && c.getDate().before(end)).toList();
    }
}

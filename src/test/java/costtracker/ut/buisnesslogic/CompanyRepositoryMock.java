package costtracker.ut.buisnesslogic;

import costtracker.adapter.entities.CompanyEntity;
import costtracker.adapter.persistence.BaseDataRepository;

import java.util.ArrayList;
import java.util.List;

public class CompanyRepositoryMock implements BaseDataRepository<CompanyEntity> {
    List<CompanyEntity> companyEntities = new ArrayList<>();

    public CompanyRepositoryMock() {
        companyEntities.add(new CompanyEntity(1, "Edeka", "Karlsruhe"));
        companyEntities.add(new CompanyEntity(2, "Rewe", "Karlsruhe"));
        companyEntities.add(new CompanyEntity(3, "FitX", "Remscheid"));
        companyEntities.add(new CompanyEntity(4, "Aral", "Landau"));
    }

    @Override
    public boolean enable(int id) {
        return true;
    }

    @Override
    public boolean disable(int id) {
        return true;
    }

    @Override
    public List<CompanyEntity> getEnabled() {
        return companyEntities;
    }

    @Override
    public List<CompanyEntity> getDisabled() {
        return companyEntities;
    }

    @Override
    public CompanyEntity get(int id) {
        return companyEntities.get(id);
    }

    @Override
    public boolean update(CompanyEntity entity) {
        CompanyEntity updateEntity = companyEntities.stream().filter(c -> c.getId() == entity.getId()).findFirst().get();
        companyEntities.remove(updateEntity);
        return companyEntities.add(entity);
    }

    @Override
    public boolean insert(CompanyEntity entity) {
        return companyEntities.add(entity);
    }

    @Override
    public boolean delete(int id) {
        CompanyEntity delEntity = companyEntities.stream().filter(c -> c.getId() == id).findFirst().get();
        return companyEntities.remove(delEntity);
    }

    @Override
    public List<CompanyEntity> getAll() {
        return companyEntities;
    }
}

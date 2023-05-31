package costtracker.ut.buisnesslogic;

import costtracker.adapter.entities.CategoryEntity;
import costtracker.adapter.persistence.BaseDataRepository;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryMock implements BaseDataRepository<CategoryEntity> {

    List<CategoryEntity> categoryEntities = new ArrayList<>();

    public CategoryRepositoryMock(){
        categoryEntities.add(new CategoryEntity(1, "Trinken"));
        categoryEntities.add(new CategoryEntity(2, "Essen"));
        categoryEntities.add(new CategoryEntity(3, "Tanken"));
        categoryEntities.add(new CategoryEntity(4, "Hobby"));
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
    public List<CategoryEntity> getEnabled() {
        return categoryEntities;
    }

    @Override
    public List<CategoryEntity> getDisabled() {
        return categoryEntities;
    }

    @Override
    public CategoryEntity get(int id) {
        return categoryEntities.get(id);
    }

    @Override
    public boolean update(CategoryEntity entity){
        CategoryEntity updateEntity = categoryEntities.stream().filter(c -> c.getId() == entity.getId()).findFirst().get();
        categoryEntities.remove(updateEntity);
        return categoryEntities.add(entity);
    }

    @Override
    public boolean insert(CategoryEntity entity) {
        return categoryEntities.add(entity);
    }

    @Override
    public boolean delete(int id) {
        CategoryEntity delEntity = categoryEntities.stream().filter(c -> c.getId() == id).findFirst().get();
        return categoryEntities.remove(delEntity);
    }

    @Override
    public List<CategoryEntity> getAll() {
        return categoryEntities;
    }
}

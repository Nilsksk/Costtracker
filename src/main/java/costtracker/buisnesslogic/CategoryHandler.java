package costtracker.buisnesslogic;

import costtracker.buisnesslogic.interfaces.DatabaseHandler;
import costtracker.buisnesslogic.interfaces.StateHandler;
import costtracker.businessobjects.Category;

public class CategoryHandler implements DatabaseHandler<Category>, StateHandler<Category> {
    @Override
    public Category getById(int id) {
        return null;
    }

    @Override
    public Category[] getAll() {
        return new Category[0];
    }

    @Override
    public Category deleteById(int id) {
        return null;
    }

    @Override
    public Category update(Category object) {
        return null;
    }

    @Override
    public Category create(Category object) {
        return null;
    }

    @Override
    public boolean enable() {
        return false;
    }

    @Override
    public boolean disable() {
        return false;
    }

    @Override
    public Category[] getEnabled() {
        return new Category[0];
    }
}

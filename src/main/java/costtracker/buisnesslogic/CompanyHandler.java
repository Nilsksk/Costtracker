package costtracker.buisnesslogic;

import costtracker.buisnesslogic.interfaces.DatabaseHandler;
import costtracker.buisnesslogic.interfaces.StateHandler;
import costtracker.businessobjects.Company;

public class CompanyHandler implements DatabaseHandler<Company>, StateHandler<Company> {
    @Override
    public Company getById(int id) {
        return null;
    }

    @Override
    public Company[] getAll() {
        return new Company[0];
    }

    @Override
    public Company deleteById(int id) {
        return null;
    }

    @Override
    public Company update(Company object) {
        return null;
    }

    @Override
    public Company create(Company object) {
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
    public Company[] getEnabled() {
        return new Company[0];
    }
}

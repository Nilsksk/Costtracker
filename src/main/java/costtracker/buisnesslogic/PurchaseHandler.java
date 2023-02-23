package costtracker.buisnesslogic;

import costtracker.buisnesslogic.interfaces.DatabaseHandler;
import costtracker.buisnesslogic.interfaces.TimestampHandler;
import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;
import costtracker.businessobjects.Purchase;

import java.time.LocalDate;

public class PurchaseHandler implements TimestampHandler, DatabaseHandler<Purchase> {

    @Override
    public Purchase getById(int id) {
        return null;
    }

    @Override
    public Purchase[] getAll() {
        return new Purchase[0];
    }

    @Override
    public Purchase deleteById(int id) {
        return null;
    }

    @Override
    public Purchase update(Purchase object) {
        return null;
    }

    @Override
    public Purchase create(Purchase object) {
        return null;
    }

    @Override
    public Purchase[] getByTimestamp(LocalDate startDate, LocalDate endDate) {
        return new Purchase[0];
    }

    @Override
    public Purchase[] getByCompanyByTimestamp(Company company, LocalDate startDate, LocalDate endDate) {
        return new Purchase[0];
    }

    @Override
    public Purchase[] getByCategoryByTimestamp(Category category, LocalDate startDate, LocalDate endDate) {
        return new Purchase[0];
    }

    @Override
    public Purchase[] getByWeek(int week, int year) {
        return new Purchase[0];
    }

    @Override
    public Purchase[] getByMonth(int month, int year) {
        return new Purchase[0];
    }

    @Override
    public Purchase[] getByYear(int year) {
        return new Purchase[0];
    }
}

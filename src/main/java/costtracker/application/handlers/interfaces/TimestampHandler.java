package costtracker.application.handlers.interfaces;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import costtracker.domain.businessobjects.Category;
import costtracker.domain.businessobjects.Company;
import costtracker.domain.businessobjects.Purchase;

public interface TimestampHandler {

    List<Purchase> getByTimestamp(LocalDate startDate, LocalDate endDate) throws SQLException;

    List<Purchase> getByCompanyByTimestamp(Company company, LocalDate startDate, LocalDate endDate) throws SQLException;

    List<Purchase> getByCategoryByTimestamp(Category category, LocalDate startDate, LocalDate endDate) throws SQLException;

    List<Purchase> getByWeek(int week, int year) throws SQLException;

    List<Purchase> getByMonth(int month, int year) throws SQLException;

    List<Purchase> getByYear(int year) throws SQLException;

}

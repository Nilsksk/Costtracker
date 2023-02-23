package costtracker.buisnesslogic.interfaces;

import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;
import costtracker.businessobjects.Purchase;

import java.time.LocalDate;

public interface TimestampHandler {

    Purchase[] getByTimestamp(LocalDate startDate, LocalDate endDate);

    Purchase[] getByCompanyByTimestamp(Company company, LocalDate startDate, LocalDate endDate);

    Purchase[] getByCategoryByTimestamp(Category category, LocalDate startDate, LocalDate endDate);

    Purchase[] getByWeek(int week, int year);

    Purchase[] getByMonth(int month, int year);

    Purchase[] getByYear(int year);

}

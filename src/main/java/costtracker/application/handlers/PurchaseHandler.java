package costtracker.application.handlers;

import costtracker.adapter.repositoryadapters.PurchaseRepositoryAdapterImp;
import costtracker.application.handlers.interfaces.DatabaseHandler;
import costtracker.application.handlers.interfaces.TimestampHandler;
import costtracker.application.interfaces.PurchaseRepositoryAdapter;
import costtracker.domain.businessobjects.Category;
import costtracker.domain.businessobjects.Company;
import costtracker.domain.businessobjects.Purchase;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PurchaseHandler implements TimestampHandler, DatabaseHandler<Purchase> {

	private PurchaseRepositoryAdapter purchaseRepositoryAdapter = new PurchaseRepositoryAdapterImp();

	public PurchaseHandler() {

	}

	@Override
	public Purchase getById(int id) {
		return purchaseRepositoryAdapter.get(id);
	}

	@Override
	public List<Purchase> getAll() {
		return purchaseRepositoryAdapter.getAll();
	}

	@Override
	public boolean deleteById(int id) {
		return purchaseRepositoryAdapter.delete(id);
	}

	@Override
	public boolean update(Purchase purchase) {
		return purchaseRepositoryAdapter.update(purchase);
	}

	@Override
	public boolean create(Purchase purchase) {
		return purchaseRepositoryAdapter.insert(purchase);
	}

	@Override
	public List<Purchase> getByTimestamp(LocalDate startDate, LocalDate endDate) {
		return purchaseRepositoryAdapter.getByTimespan(startDate, endDate);
	}

	@Override
	public List<Purchase> getByCompanyByTimestamp(Company company, LocalDate startDate, LocalDate endDate){
		return purchaseRepositoryAdapter.getByCompanyByTimespan(company, startDate, endDate);
	}

	@Override
	public List<Purchase> getByCategoryByTimestamp(Category category, LocalDate startDate, LocalDate endDate) {
		return purchaseRepositoryAdapter.getByCategoryByTimespan(category, startDate, endDate);
	}

	@Override
	public List<Purchase> getByWeek(int week, int year) {
		List<Purchase> allPurchases = purchaseRepositoryAdapter.getAll();
		List<Purchase> purchasesByWeek = new ArrayList<>();

		for (Purchase purchase : allPurchases) {
			TemporalField weekOfYear = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
			if (purchase.getDate().get(weekOfYear) == week) {
				if (purchase.getDate().getYear() == year) {
					purchasesByWeek.add(purchase);
				}
			}
		}

		return purchasesByWeek;
	}

	@Override
	public List<Purchase> getByMonth(int month, int year) {
		List<Purchase> allPurchases = purchaseRepositoryAdapter.getAll();
		List<Purchase> purchasesByMonth = new ArrayList<>();

		for (Purchase purchase : allPurchases) {
			if (purchase.getDate().getMonth().getValue() == month) {
				if (purchase.getDate().getYear() == year) {
					purchasesByMonth.add(purchase);
				}
			}
		}

		return purchasesByMonth;
	}

	@Override
	public List<Purchase> getByYear(int year) {
		List<Purchase> allPurchases = purchaseRepositoryAdapter.getAll();
		List<Purchase> purchasesByYear = new ArrayList<>();

		for (Purchase purchase : allPurchases) {
			if (purchase.getDate().getYear() == year) {
				purchasesByYear.add(purchase);
			}
		}

		return purchasesByYear;
	}
}

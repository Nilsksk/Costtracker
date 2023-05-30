package costtracker.application.handlers;

import costtracker.application.handlers.interfaces.DatabaseHandler;
import costtracker.application.handlers.interfaces.TimestampHandler;
import costtracker.domain.businessobjects.Category;
import costtracker.domain.businessobjects.Company;
import costtracker.domain.businessobjects.Purchase;
import costtracker.plugin.db.entities.PurchaseEntity;
import costtracker.plugin.db.unitofwork.UnitOfWork;
import costtracker.plugin.db.unitofwork.UnitOfWorkImp;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class PurchaseHandler implements TimestampHandler, DatabaseHandler<Purchase> {
    @Override
    public Purchase getById(int id) throws SQLException {
		try (UnitOfWork uow = new UnitOfWorkImp()) {

        PurchaseEntity purchaseEntity = uow.getPurchaseRepository().get(id);
        return Purchase.fromEntity(purchaseEntity);
		}
    }

    @Override
    public List<Purchase> getAll() throws SQLException {
		try (UnitOfWork uow = new UnitOfWorkImp()) {

        List<PurchaseEntity> purchaseList = uow.getPurchaseRepository().getAll();

        return purchaseList.stream().map(Purchase::fromEntity).collect(Collectors.toList());
		}
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
		try (UnitOfWork uow = new UnitOfWorkImp()) {

        boolean state = uow.getPurchaseRepository().delete(id);

        if (state) {
            uow.Save();
        }

        return state;
		}
    }

    @Override
    public boolean update(Purchase purchase) throws SQLException {
		try (UnitOfWork uow = new UnitOfWorkImp()) {

        boolean state = uow.getPurchaseRepository().update(purchase.toEntity());

        if (state) {
            uow.Save();
        }

        return state;
		}
    }

    @Override
    public boolean create(Purchase purchase) throws SQLException {
		try (UnitOfWork uow = new UnitOfWorkImp()) {

        boolean state = uow.getPurchaseRepository().insert(purchase.toEntity());

        if (state) {
            uow.Save();
        }

        return state;
		}
    }

    @Override
    public List<Purchase> getByTimestamp(LocalDate startDate, LocalDate endDate) throws SQLException {
    	try (UnitOfWork uow = new UnitOfWorkImp()) {
        // Get Purchases from Database

        Date convertedStartDate = Date.valueOf(startDate);
        Date convertedEndDate = Date.valueOf(endDate);
        List<PurchaseEntity> allEntityPurchases = uow.getPurchaseRepository().getByTimespan(convertedStartDate, convertedEndDate);

        return allEntityPurchases.stream().map(Purchase::fromEntity).toList();
		}
    }

    @Override
    public List<Purchase> getByCompanyByTimestamp(Company company, LocalDate startDate, LocalDate endDate) throws SQLException {
    	try (UnitOfWork uow = new UnitOfWorkImp()) {
        // Get Purchases from Database

        Date convertedStartDate = Date.valueOf(startDate);
        Date convertedEndDate = Date.valueOf(endDate);
        List<PurchaseEntity> allEntityPurchases = uow.getPurchaseRepository().getByCompanyByTimespan(company.toEntity(), convertedStartDate, convertedEndDate);

        return allEntityPurchases.stream().map(Purchase::fromEntity).toList();
		}
    }

    @Override
    public List<Purchase> getByCategoryByTimestamp(Category category, LocalDate startDate, LocalDate endDate) throws SQLException {
    	try (UnitOfWork uow = new UnitOfWorkImp()) {
        // Get Purchases from Database

        Date convertedStartDate = Date.valueOf(startDate);
        Date convertedEndDate = Date.valueOf(endDate);
        List<PurchaseEntity> allEntityPurchases = uow.getPurchaseRepository().getByCategoryByTimespan(category.toEntity(), convertedStartDate, convertedEndDate);

        return allEntityPurchases.stream().map(Purchase::fromEntity).toList();
		}
    }

    @Override
    public List<Purchase> getByWeek(int week, int year) throws SQLException {
    	try (UnitOfWork uow = new UnitOfWorkImp()) {
        // Get Purchases from Database

        List<PurchaseEntity> allEntityPurchases = uow.getPurchaseRepository().getAll();
        // Map Purchases from Entity's to Business objects
        List<Purchase> allPurchases = allEntityPurchases.stream().map(Purchase::fromEntity).toList();
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
    }

    @Override
    public List<Purchase> getByMonth(int month, int year) throws SQLException {
		try (UnitOfWork uow = new UnitOfWorkImp()) {

        // Get Purchases from Database
        List<PurchaseEntity> allEntityPurchases = uow.getPurchaseRepository().getAll();
        // Map Purchases from Entity's to Business objects
        List<Purchase> allPurchases = allEntityPurchases.stream().map(Purchase::fromEntity).toList();
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
    }

    @Override
    public List<Purchase> getByYear(int year) throws SQLException {
		try (UnitOfWork uow = new UnitOfWorkImp()) {

        // Get Purchases from Database
        List<PurchaseEntity> allEntityPurchases = uow.getPurchaseRepository().getAll();
        // Map Purchases from Entity's to Business objects
        List<Purchase> allPurchases = allEntityPurchases.stream().map(Purchase::fromEntity).toList();
        List<Purchase> purchasesByYear = new ArrayList<>();

        for (Purchase purchase : allPurchases) {
            if (purchase.getDate().getYear() == year) {
                purchasesByYear.add(purchase);
            }
        }

        return purchasesByYear;
		}
    }
}

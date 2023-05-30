package costtracker.adapter.repositoryadapters;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import costtracker.adapter.entities.CategoryEntity;
import costtracker.adapter.entities.CompanyEntity;
import costtracker.adapter.entities.PurchaseEntity;
import costtracker.adapter.mappers.EntityMapper;
import costtracker.adapter.persistence.UnitOfWork;
import costtracker.application.interfaces.PurchaseRepositoryAdapter;
import costtracker.domain.businessobjects.Category;
import costtracker.domain.businessobjects.Company;
import costtracker.domain.businessobjects.Purchase;
import costtracker.plugin.db.unitofwork.UnitOfWorkImp;

public class PurchaseRepositoryAdapterImp implements PurchaseRepositoryAdapter {

	private UnitOfWork uow = new UnitOfWorkImp();

	public PurchaseRepositoryAdapterImp() {
	
	}
	
	@Override
	public Purchase get(int id) {
		Purchase purchase = null;
		try {
			PurchaseEntity purchaseEntity = uow.getPurchaseRepository().get(id);
			purchase = EntityMapper.toBo(purchaseEntity);
		} catch (SQLException e) {
			// TODO Addlogging
		}
		return purchase;
	}

	@Override
	public boolean update(Purchase purchase) {
		PurchaseEntity purchaseEntity = EntityMapper.toEntity(purchase);
		boolean ret = false;
		try {
			ret = uow.getPurchaseRepository().update(purchaseEntity);
			if(ret)
				uow.save();
		} catch (SQLException e) {
			// TODO Addlogging
		}
		return ret;
	}

	@Override
	public boolean insert(Purchase purchase) {
		PurchaseEntity purchaseEntity = EntityMapper.toEntity(purchase);
		boolean ret = false;
		try {
			ret = uow.getPurchaseRepository().insert(purchaseEntity);
			if(ret)
				uow.save();
		} catch (SQLException e) {
			// TODO Addlogging
		}
		return ret;
	}

	@Override
	public boolean delete(int id) {
		boolean ret = false;
		try {
			ret = uow.getPurchaseRepository().delete(id);
		} catch (SQLException e) {
			// TODO Addlogging
		}
		return ret;
		}

	@Override
	public List<Purchase> getAll() {
		List<Purchase> purchases = new ArrayList<>();
		try {
			List<PurchaseEntity> entities = uow.getPurchaseRepository().getAll();
			for (PurchaseEntity purchaseEntity : entities) {
				Purchase purchase = EntityMapper.toBo(purchaseEntity);
				purchases.add(purchase);
			}
		} catch (SQLException e) {
			// TODO Addlogging
		}
		return purchases;
	}

	@Override
	public List<Purchase> getByTimespan(LocalDate start, LocalDate end) {
		Date dateStart = Date.valueOf(start);
		Date dateEnd = Date.valueOf(end);
		List<Purchase> purchases = new ArrayList<>();
		try {
			List<PurchaseEntity> entities = uow.getPurchaseRepository().getByTimespan(dateStart, dateEnd);
			for (PurchaseEntity purchaseEntity : entities) {
				Purchase purchase = EntityMapper.toBo(purchaseEntity);
				purchases.add(purchase);
			}
		} catch (SQLException e) {
			// TODO Addlogging
		}
		return purchases;
	}

	@Override
	public List<Purchase> getByCategoryByTimespan(Category category, LocalDate start, LocalDate end) {
		CategoryEntity categoryEntity = EntityMapper.toEntity(category);
		Date dateStart = Date.valueOf(start);
		Date dateEnd = Date.valueOf(end);
		List<Purchase> purchases = new ArrayList<>();
		try {
			List<PurchaseEntity> entities = uow.getPurchaseRepository().getByCategoryByTimespan(categoryEntity, dateStart, dateEnd);
			for (PurchaseEntity purchaseEntity : entities) {
				Purchase purchase = EntityMapper.toBo(purchaseEntity);
				purchases.add(purchase);
			}
		} catch (SQLException e) {
			// TODO Addlogging
		}
		return purchases;
	}
	@Override
	public List<Purchase> getByCompanyByTimespan(Company company, LocalDate start, LocalDate end) {
		CompanyEntity companyEntity = EntityMapper.toEntity(company);
		Date dateStart = Date.valueOf(start);
		Date dateEnd = Date.valueOf(end);
		List<Purchase> purchases = new ArrayList<>();
		try {
			List<PurchaseEntity> entities = uow.getPurchaseRepository().getByCompanyByTimespan(companyEntity, dateStart, dateEnd);
			for (PurchaseEntity purchaseEntity : entities) {
				Purchase purchase = EntityMapper.toBo(purchaseEntity);
				purchases.add(purchase);
			}
		} catch (SQLException e) {
			// TODO Addlogging
		}
		return purchases;
	}

}

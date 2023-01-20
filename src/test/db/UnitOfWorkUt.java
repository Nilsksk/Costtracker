package test.db;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import costtracker.db.repositories.CategoryRepository;
import costtracker.db.repositories.CompanyRepository;
import costtracker.db.repositories.PurchaseRepository;
import costtracker.db.unitofwork.UnitOfWorkImp;

class UnitOfWorkUt {

	@Test
	void TestGetPurchaseRepository() {
		UnitOfWorkImp uow = new UnitOfWorkImp();
		PurchaseRepository repo = uow.getPurchaseRepository();
		assertNotNull(repo);
	}
	
	@Test
	void TestGetCompanyRepository() {
		UnitOfWorkImp uow = new UnitOfWorkImp();
		CompanyRepository repo = uow.getCompanyRepository();
		assertNotNull(repo);
	}
	
	@Test
	void TestGetCategoryRepository() {
		UnitOfWorkImp uow = new UnitOfWorkImp();
		CategoryRepository repo = uow.getCategoryRepository();
		assertNotNull(repo);
	}

	@Test
	void TestSAve() {
		UnitOfWorkImp uow = new UnitOfWorkImp();
		uow.Save();
		
	}

}

package test.db;

import costtracker.db.repositories.CategoryRepository;
import costtracker.db.repositories.CompanyRepository;
import costtracker.db.repositories.PurchaseRepository;
import costtracker.db.unitofwork.UnitOfWorkImp;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UnitOfWorkUnitTest {

	@Test
	public void TestGetPurchaseRepository() {
		UnitOfWorkImp uow = new UnitOfWorkImp();
		PurchaseRepository repo = uow.getPurchaseRepository();
		assertNotNull(repo);
	}
	
	@Test
	public void TestGetCompanyRepository() {
		UnitOfWorkImp uow = new UnitOfWorkImp();
		CompanyRepository repo = uow.getCompanyRepository();
		assertNotNull(repo);
	}
	
	@Test
	 public void TestGetCategoryRepository() {
		UnitOfWorkImp uow = new UnitOfWorkImp();
		CategoryRepository repo = uow.getCategoryRepository();
		assertNotNull(repo);
	}

	@Test
	public void TestSAve() {
		UnitOfWorkImp uow = new UnitOfWorkImp();
		uow.Save();
		
	}

}

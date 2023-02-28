package costtracker.ut.db;

import costtracker.db.repositories.CategoryRepository;
import costtracker.db.repositories.CompanyRepository;
import costtracker.db.repositories.PurchaseRepository;
import costtracker.db.unitofwork.UnitOfWorkImp;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

class UnitOfWorkUnitTest {

	@Test
	void TestGetPurchaseRepository() throws IOException {
		PurchaseRepository repo;
		try(UnitOfWorkImp uow = new UnitOfWorkImp()){
			repo = uow.getPurchaseRepository();
		}
		assertNotNull(repo);
	}
	
	@Test
	void TestGetCompanyRepository() throws IOException {
		CompanyRepository repo;
		try(UnitOfWorkImp uow = new UnitOfWorkImp()){
		repo = uow.getCompanyRepository();
		}
		assertNotNull(repo);
	}
	
	@Test
	void TestGetCategoryRepository() throws IOException {
		CategoryRepository repo;
		try(UnitOfWorkImp uow = new UnitOfWorkImp()){
		repo = uow.getCategoryRepository();
		}
		assertNotNull(repo);
	}

	@Test
	void TestSave() throws IOException {
		
		try(UnitOfWorkImp uow = new UnitOfWorkImp()){
			uow.Save();
		}
		
	}

}

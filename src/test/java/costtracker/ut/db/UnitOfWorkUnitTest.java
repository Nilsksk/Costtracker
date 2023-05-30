package costtracker.ut.db;

import costtracker.plugin.db.repositories.CategoryRepository;
import costtracker.plugin.db.repositories.CompanyRepository;
import costtracker.plugin.db.repositories.PurchaseRepository;
import costtracker.plugin.db.unitofwork.UnitOfWorkImp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.sql.SQLException;

class UnitOfWorkUnitTest {

	@Test
	void TestGetPurchaseRepository() throws IOException, SQLException {
		PurchaseRepository repo;
		try(UnitOfWorkImp uow = new UnitOfWorkImp()){
			repo = uow.getPurchaseRepository();
		}
		assertNotNull(repo);
	}
	
	@Test
	void TestGetCompanyRepository() throws IOException, SQLException {
		CompanyRepository repo;
		try(UnitOfWorkImp uow = new UnitOfWorkImp()){
		repo = uow.getCompanyRepository();
		}
		assertNotNull(repo);
	}
	
	@Test
	void TestGetCategoryRepository() throws IOException, SQLException {
		CategoryRepository repo;
		try(UnitOfWorkImp uow = new UnitOfWorkImp()){
		repo = uow.getCategoryRepository();
		}
		assertNotNull(repo);
	}

	@Test
	void TestSave() throws IOException, SQLException {	
		try(UnitOfWorkImp uow = new UnitOfWorkImp()){
			uow.save();
		}
		
	}

}

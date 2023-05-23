package costtracker.db.unitofwork;

import costtracker.db.repositories.CategoryRepository;
import costtracker.db.repositories.CompanyRepository;
import costtracker.db.repositories.PurchaseRepository;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UnitOfWorkImp implements UnitOfWork, Closeable {

	private CompanyRepository companyRepository;
	private CategoryRepository categoryRepository;
	private PurchaseRepository purchaseRepository;
	
	private Connection connection;
	
	public UnitOfWorkImp() {
		try {
			connection = DriverManager.getConnection("jdbc:h2:~/testdb", "sa", "test");
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@Override
	public void Save() {
		try {
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public PurchaseRepository getPurchaseRepository() {
		if(purchaseRepository == null)
			purchaseRepository = new PurchaseRepository(connection);
		return purchaseRepository;
	}

	public CategoryRepository getCategoryRepository() {
		if(categoryRepository == null)
			categoryRepository = new CategoryRepository(connection);
		return categoryRepository;
	}

	public CompanyRepository getCompanyRepository() {
		if(companyRepository == null)
			companyRepository = new CompanyRepository(connection);
		return companyRepository;
	}

	@Override
	public void close() throws IOException {
		try {
			this.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

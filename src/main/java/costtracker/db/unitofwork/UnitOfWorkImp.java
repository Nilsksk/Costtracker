package costtracker.db.unitofwork;

import costtracker.db.repositories.CategoryRepository;
import costtracker.db.repositories.CompanyRepository;
import costtracker.db.repositories.PurchaseRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UnitOfWorkImp implements UnitOfWork {

	private CompanyRepository companyRepository;
	private CategoryRepository categoryRepository;
	private PurchaseRepository purchaseRepository;
	
	private Connection connection;
	
	public UnitOfWorkImp() {
		try {
			connection = DriverManager.getConnection("jdbc:h2:~/costtracker", "sa", "ct2023!");
			connection.setAutoCommit(false);
			Path path = Paths.get("CreateDatabase.sql");
			String sql = Files.readString(path);
			Statement stmt = connection.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
	public void close() throws SQLException{
			this.connection.close();		
	}

}

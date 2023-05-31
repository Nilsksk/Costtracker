package costtracker.plugin.db.unitofwork;

import costtracker.adapter.persistence.UnitOfWork;
import costtracker.plugin.db.repositories.CategoryRepository;
import costtracker.plugin.db.repositories.CompanyRepository;
import costtracker.plugin.db.repositories.PurchaseRepository;

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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void save() {
		try {
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public PurchaseRepository getPurchaseRepository() {
		if (purchaseRepository == null)
			purchaseRepository = new PurchaseRepository(connection);
		return purchaseRepository;
	}

	public CategoryRepository getCategoryRepository() {
		if (categoryRepository == null)
			categoryRepository = new CategoryRepository(connection);
		return categoryRepository;
	}

	public CompanyRepository getCompanyRepository() {
		if (companyRepository == null)
			companyRepository = new CompanyRepository(connection);
		return companyRepository;
	}

	@Override
	public void close() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void ensureCreated() {
		try {
			String sql = "CREATE TABLE IF NOT EXISTS category\n" +
					"(id IDENTITY NOT NULL,\n" +
					"name varchar(50) NOT NULL,\n" +
					"isenabled boolean NOT NULL);\n" +
					"\n" +
					"CREATE TABLE IF NOT EXISTS company\n" +
					"(id IDENTITY NOT NULL,\n" +
					"name varchar(50) NOT NULL,\n" +
					"location varchar(50),\n" +
					"isenabled boolean NOT NULL);\n" +
					"\n" +
					"CREATE TABLE IF NOT EXISTS purchase\n" +
					"(id IDENTITY NOT NULL,\n" +
					"name varchar(50) NOT NULL,\n" +
					"description varchar(50),\n" +
					"date date NOT NULL,\n" +
					"category int,\n" +
					"company int,\n" +
					"price numeric(8,2) NOT NULL,\n" +
					"CONSTRAINT FK_CATEGORY FOREIGN KEY (category) references category(id) ON DELETE CASCADE,\n" +
					"CONSTRAINT FK_COMPANY FOREIGN KEY (company) references company(id) ON DELETE SET NULL);";

			Statement stmt = connection.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

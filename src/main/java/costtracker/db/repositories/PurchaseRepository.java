package costtracker.db.repositories;

import costtracker.db.entities.CategoryEntity;
import costtracker.db.entities.CompanyEntity;
import costtracker.db.entities.PurchaseEntity;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseRepository extends RepositoryBase implements DataRepository<PurchaseEntity> {

	public PurchaseRepository(Connection connection) {
		super(connection);
	}

	@Override
	public PurchaseEntity get(int id) throws SQLException {
		PurchaseEntity entity = null;
		String sql = "select p.id, p.name, p.description, p.price, p.date, com.id, com.name, com.location, cat.id, cat.name"
				+ " from purchase p join company com on p.company = com.id "
				+ "join category cat on p.category = cat.id where p.id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);

		ResultSet result = stmt.executeQuery();
		if (result.next()) {
			entity = new PurchaseEntity(result.getInt(1),
					new CompanyEntity(result.getInt(6), result.getString(7), result.getString(8)),
					new CategoryEntity(result.getInt(9), result.getString(10)), result.getDouble(4),
					result.getString(2), result.getString(3), result.getDate(5));
		}

		return entity;
	}

	@Override
	public boolean update(PurchaseEntity entity) throws SQLException {
		String sql = "update purchase set name = ?, description = ?, price = ?, date = ?, company = ?, category = ? where id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, entity.getName());
		stmt.setString(2, entity.getDescription());
		stmt.setDouble(3, entity.getPrice());
		stmt.setDate(4, entity.getDate());
		if (entity.getCompany() != null)
			stmt.setInt(5, entity.getCompany().getId());
		else
			stmt.setObject(5, null);
		stmt.setInt(6, entity.getCategory().getId());
		stmt.setInt(7, entity.getId());

		int result = stmt.executeUpdate();

		return result == 1;
	}

	@Override
	public boolean insert(PurchaseEntity entity) throws SQLException {
		String sql = "insert into purchase(name, description, price, date, company, category) values (?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, entity.getName());
		stmt.setString(2, entity.getDescription());
		stmt.setDouble(3, entity.getPrice());
		stmt.setDate(4, entity.getDate());
		if (entity.getCompany() != null)
			stmt.setInt(5, entity.getCompany().getId());
		else
			stmt.setObject(5, null);
		stmt.setInt(6, entity.getCategory().getId());

		int result = stmt.executeUpdate();

		return result == 1;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		String sql = "delete from purchase where id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);

		int result = stmt.executeUpdate();

		return result == 1;
	}

	@Override
	public List<PurchaseEntity> getAll() throws SQLException {
		String sql = "select p.id, p.name, p.description, p.price, p.date, com.id, com.name, com.location, cat.id, cat.name"
				+ " from purchase p join company com on p.company = com.id"
				+ " join category cat on p.category = cat.id";
		PreparedStatement stmt = connection.prepareStatement(sql);

		ResultSet result = stmt.executeQuery();

		List<PurchaseEntity> purchases = new ArrayList<PurchaseEntity>();

		while (result.next()) {
			PurchaseEntity entity = new PurchaseEntity(result.getInt(1),
					new CompanyEntity(result.getInt(6), result.getString(7), result.getString(8)),
					new CategoryEntity(result.getInt(9), result.getString(10)), result.getDouble(4),
					result.getString(2), result.getString(3), result.getDate(5));
			purchases.add(entity);
		}

		return purchases;
	}

	@Override
	public List<PurchaseEntity> getByTimespan(Date start, Date end) throws SQLException {
		String sql = "select p.id, p.name, p.description, p.price, p.date, com.id, com.name, com.location, cat.id, cat.name"
				+ " from purchase p join company com on p.company = com.id"
				+ " join category cat on p.category = cat.id" + " where p.date > ? and p.date < ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setDate(1, start);
		stmt.setDate(2, end);
		ResultSet result = stmt.executeQuery();

		List<PurchaseEntity> purchases = new ArrayList<PurchaseEntity>();

		while (result.next()) {
			PurchaseEntity entity = new PurchaseEntity(result.getInt(1),
					new CompanyEntity(result.getInt(6), result.getString(7), result.getString(8)),
					new CategoryEntity(result.getInt(9), result.getString(10)), result.getDouble(4),
					result.getString(2), result.getString(3), result.getDate(5));
			purchases.add(entity);
		}

		return purchases;
	}

	@Override
	public List<PurchaseEntity> getByCategoryByTimespan(CategoryEntity category, Date start, Date end) throws SQLException {
		String sql = "select p.id, p.name, p.description, p.price, p.date, com.id, com.name, com.location, cat.id, cat.name"
				+ " from purchase p join company com on p.company = com.id"
				+ " join category cat on p.category = cat.id" 
				+ " where cat.id = ? and p.date > ? and p.date < ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, category.getId());
		stmt.setDate(2, start);
		stmt.setDate(3, end);
		ResultSet result = stmt.executeQuery();

		List<PurchaseEntity> purchases = new ArrayList<PurchaseEntity>();

		while (result.next()) {
			PurchaseEntity entity = new PurchaseEntity(result.getInt(1),
					new CompanyEntity(result.getInt(6), result.getString(7), result.getString(8)),
					new CategoryEntity(result.getInt(9), result.getString(10)), result.getDouble(4),
					result.getString(2), result.getString(3), result.getDate(5));
			purchases.add(entity);
		}

		return purchases;
	}

	@Override
	public List<PurchaseEntity> getByCompanyByTimespan(CompanyEntity company, Date start, Date end) throws SQLException {
		String sql = "select p.id, p.name, p.description, p.price, p.date, com.id, com.name, com.location, cat.id, cat.name"
				+ " from purchase p join company com on p.company = com.id"
				+ " join category cat on p.category = cat.id" 
				+ " where com.id = ? and p.date > ? and p.date < ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, company.getId());
		stmt.setDate(2, start);
		stmt.setDate(3, end);
		ResultSet result = stmt.executeQuery();

		List<PurchaseEntity> purchases = new ArrayList<PurchaseEntity>();

		while (result.next()) {
			PurchaseEntity entity = new PurchaseEntity(result.getInt(1),
					new CompanyEntity(result.getInt(6), result.getString(7), result.getString(8)),
					new CategoryEntity(result.getInt(9), result.getString(10)), result.getDouble(4),
					result.getString(2), result.getString(3), result.getDate(5));
			purchases.add(entity);
		}

		return purchases;
	}
}

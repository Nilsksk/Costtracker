package costtracker.db.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import costtracker.db.entities.CategoryEntity;
import costtracker.db.entities.CompanyEntity;
import costtracker.db.entities.PurchaseEntity;

public class PurchaseRepository extends RepositoryBase implements Repository<PurchaseEntity> {

	public PurchaseRepository(Connection connection) {
		super(connection);
	}

	@Override
	public PurchaseEntity select(int id) throws SQLException {
		PurchaseEntity entity = null;
		String sql = "select p.id, p.name, p.description, p.price, p.date, com.id, com.name, com.location, cat.id, cat.name"
					+ " from purchase p join company com on p.company = com.id "
					+ "join category cat on p.category = cat.id where p.id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);

		var result = stmt.executeQuery();
		if (result.next()) {
			entity = new PurchaseEntity(result.getInt(1),
					new CompanyEntity(result.getInt(6), result.getString(7),
							result.getString(8)),
					new CategoryEntity(result.getInt(9), result.getString(10)),
					result.getDouble(4), result.getString(2), result.getString(3),
					result.getDate(5));
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

		var result = stmt.executeUpdate();

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

		var result = stmt.executeUpdate();

		return result == 1;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		String sql = "delete from purchase where id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);

		var result = stmt.executeUpdate();

		return result == 1;
	}

	@Override
	public boolean delete(PurchaseEntity entity) throws SQLException {
		return this.delete(entity.getId());
	}

}

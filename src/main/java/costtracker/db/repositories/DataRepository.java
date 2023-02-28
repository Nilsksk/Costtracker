package costtracker.db.repositories;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import costtracker.db.entities.CategoryEntity;
import costtracker.db.entities.CompanyEntity;

public interface DataRepository<T> extends Repository<T> {
	public List<T> getByTimespan(Date start, Date end) throws SQLException;	
	public List<T> getByCategoryByTimespan(CategoryEntity category, Date start, Date end) throws SQLException;
	public List<T> getByCompanyByTimespan(CompanyEntity company, Date start, Date end) throws SQLException;
}

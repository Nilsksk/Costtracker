package costtracker.ut.businessobjects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import costtracker.businessobjects.Company;
import costtracker.db.entities.CompanyEntity;

class CompanyUnitTest {

	@Test
	void testFromEntity() {
		int id = 1;
		String name = "name";
		String location = "location";
		CompanyEntity entity = new CompanyEntity(id, name, location); 
		Company company = Company.fromEntity(entity);
		
		assertEquals(id, company.getId());
		assertEquals(name, company.getName());
		assertEquals(location, company.getLocation());
	}
	
	@Test
	void testToEntity() {
		int id = 1;
		String name = "name";
		String location = "location";
		Company company = new Company(id, name, location); 
		CompanyEntity entity = company.toEntity();
		
		assertEquals(id, entity.getId());
		assertEquals(name, entity.getName());
		assertEquals(location, entity.getLocation());
	}

}
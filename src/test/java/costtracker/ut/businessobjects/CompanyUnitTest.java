package costtracker.ut.businessobjects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import costtracker.adapter.entities.CompanyEntity;
import costtracker.adapter.mappers.EntityMapper;
import costtracker.domain.businessobjects.Company;
import costtracker.domain.businessobjects.IncorrectEntryException;

class CompanyUnitTest {

	@Test
	void testFromEntity() {
		int id = 1;
		String name = "name";
		String location = "location";
		CompanyEntity entity = new CompanyEntity(id, name, location); 
		Company company = EntityMapper.toBo(entity);
		
		assertEquals(id, company.getId());
		assertEquals(name, company.getName());
		assertEquals(location, company.getLocation());
	}
	
	@Test
	void testToEntity() throws IncorrectEntryException {
		int id = 1;
		String name = "name";
		String location = "location";
		Company company = Company.CompanyBuilder.withName(name).withId(id).withLocation(location).build();
		CompanyEntity entity = EntityMapper.toEntity(company);
		
		assertEquals(id, entity.getId());
		assertEquals(name, entity.getName());
		assertEquals(location, entity.getLocation());
	}

}

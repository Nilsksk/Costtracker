package costtracker.ut.ui;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import costtracker.domain.businessobjects.Company;
import costtracker.domain.businessobjects.IncorrectEntryException;

class CompanyManagerTest {

	@Test
	void test() throws IncorrectEntryException {
		//CompanyManager cm = new CompanyManager();
		
		List<Company> companies = new ArrayList<Company>();
		companies.add(Company.CompanyBuilder.withName("Netto").withLocation("Offenbach").withId(1).build());
		companies.add(Company.CompanyBuilder.withName("Penny").withLocation("Offenbach").withId(2).build());
		companies.add(Company.CompanyBuilder.withName("Globus").withLocation("Neutstadt").withId(3).build());
		companies.add(Company.CompanyBuilder.withName("Dogan").withLocation("Offenbach").withId(4).build());
		
		//cm.setCompanies(companies);
		
		//cm.edit();
	}

}

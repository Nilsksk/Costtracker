package costtracker.ut.ui;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import costtracker.domain.businessobjects.Company;
import costtracker.plugin.ui.CompanyManager;

class CompanyManagerTest {

	@Test
	void test() {
		CompanyManager cm = new CompanyManager();
		
		List<Company> companies = new ArrayList<Company>();
		companies.add(new Company(1, "Netto", "Offenbach"));
		companies.add(new Company(2, "Penny", "Offenbach"));
		companies.add(new Company(3, "Globus", "Neustadt"));
		companies.add(new Company(4, "Dogan", "Offenbach"));
		
		//cm.setCompanies(companies);
		
		//cm.edit();
	}

}

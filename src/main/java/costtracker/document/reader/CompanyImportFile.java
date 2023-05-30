package costtracker.document.reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import costtracker.businessobjects.Company;
import costtracker.businessobjects.IncorrectEntryException;

public class CompanyImportFile {
		private ImportFile importFile;

		public CompanyImportFile(ImportFile importFile) {
			this.importFile = importFile;
		}
		
		public List<Company> getCompanies() {
			List<Company> companies = new ArrayList<>();
			while (this.importFile.hasNextEntry()) {
				HashMap<String, String> entry = importFile.getNextEntry();
				String name = entry.get("name");
				String location = entry.get("location");
				Company company;
				try {
					company = Company.CompanyBuilder.withName(name).withId(0).withLocation(location).build();
					companies.add(company);
				} catch (IncorrectEntryException e) {
					System.err.println("Wrong format for company: name = " + name +", location = " + location);
				}
			}
			return companies;
		}
}

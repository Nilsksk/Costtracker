package costtracker.document.reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import costtracker.businessobjects.Company;

public class CompanyImportFile {
		private CSVImportFile importFile;

		public CompanyImportFile(CSVImportFile importFile) {
			this.importFile = importFile;
		}
		
		public List<Company> getCompanies() {
			List<Company> companies = new ArrayList<>();
			while (this.importFile.hasNextEntry()) {
				HashMap<String, String> entry = importFile.getNextEntry();
				String name = entry.get("name");
				String location = entry.get("location");
				Company category = new Company(0, name, location);
				companies.add(category);
			}
			return companies;
		}
}

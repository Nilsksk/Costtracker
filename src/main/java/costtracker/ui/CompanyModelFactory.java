package costtracker.ui;

import java.util.ArrayList;
import java.util.List;

import costtracker.businessobjects.Company;

public class CompanyModelFactory {

	private List<CompanyModel> companyModels;
	
	public CompanyModelFactory() {
		this.companyModels = new ArrayList<>();
	}
	
	public List<CompanyModel> createCompanyModels(List<Company> companies){
		try {
			for (int i = 1; i <= companies.size(); i++) {
				CompanyModel companyModel = new CompanyModel(i, companies.get(i-1));
				companyModels.add(companyModel);
			}
			return companyModels;			
		}catch(Exception e) {
			companyModels.clear();
			return null;
		}
	}
}

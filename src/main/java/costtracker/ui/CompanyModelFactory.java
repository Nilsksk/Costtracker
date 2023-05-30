package costtracker.ui;

import java.util.ArrayList;
import java.util.List;

import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;

public class CompanyModelFactory {

	private List<CompanyModel> companyModels = new ArrayList<>();
	
	public List<CompanyModel> createCompanyModels(List<Company> companies){
		try {
			return createModel(companies);			
		}catch(Exception e) {
			companyModels.clear();
			return null;
		}
	}
	
	private List<CompanyModel> createModel(List<Company> companies){
		for (int i = 1; i <= companies.size(); i++) {
			addCompanyToList(companies, i);
		}
		return companyModels;
	}
	
	private void addCompanyToList (List<Company> companies, int i) {
		try {
			CompanyModel companyModel = CompanyModel.CompanyModelBuilder.withCompany(companies.get(i-1)).withPosition(i).build();
			companyModels.add(companyModel);
		} catch (Exception e) {
			
			DialogueHelper.println("Fehler!");
		}
	}
}

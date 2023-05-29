package costtracker.ui;

import costtracker.businessobjects.Company;

public class CompanyModel {

	private Company company;
	private int position;
	
	public CompanyModel(int position, Company company) {
		this.position = position;
		this.company = company;
	}
	
	public int getPosition(){
		return position;
	}
	
	public Company getCompany() {
		return company;
	}
}

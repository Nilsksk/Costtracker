package costtracker.ui;

import costtracker.businessobjects.Company;

public class CompanyModel {

	private Company company;
	private int position;
	
	public int getPosition(){
		return position;
	}
	
	public Company getCompany() {
		return company;
	}
	
	public CompanyModel(CompanyModelBuilder companyModelBuilder) {
		this.company = companyModelBuilder.company;
		this.position = companyModelBuilder.position;
	}
	
	public static class CompanyModelBuilder {
		
		private Company company;
		private int position;
		
		public CompanyModelBuilder(Company company) {
			this.company = company;
		}

		public static CompanyModelBuilder withCompany(Company company) {
			return new CompanyModelBuilder(company);
		}
		
		public CompanyModelBuilder withPosition(int position) {
			this.position = position;
			return this;
		}
		
		public CompanyModel build() {
			return new CompanyModel(this);
		}
	}
}

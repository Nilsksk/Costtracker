package costtracker.document.elements;

import java.util.ArrayList;

import costtracker.businessobjects.Company;
import costtracker.businessobjects.Purchase;

public class CompanyHistoryElement extends HistoryElementBase implements HistoryElement {

	private Company company;
	
		
	public CompanyHistoryElement(Company company) {
		this.company = company;
		this.purchases = new ArrayList<Purchase>();
	}

	@Override
	public String getHeader() {
		// TODO Auto-generated method stub
		return company.getName();
	}

}

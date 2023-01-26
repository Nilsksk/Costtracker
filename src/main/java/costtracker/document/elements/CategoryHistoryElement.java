package costtracker.document.elements;

import java.util.ArrayList;

import costtracker.businessobjects.Category;
import costtracker.businessobjects.Purchase;

public class CategoryHistoryElement extends HistoryElementBase implements HistoryElement {
	
	private Category category;
	
	
	public CategoryHistoryElement(Category category) {
		this.category = category;
		this.purchases = new ArrayList<Purchase>();
	}

	@Override
	public String getHeader() {
		return this.category.getName();
	}
	
	
	
	

}

package costtracker.domain.in;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import costtracker.domain.businessobjects.Category;
import costtracker.domain.businessobjects.Company;
import costtracker.domain.businessobjects.Purchase;
import costtracker.domain.in.enums.ElementType;

/**
 * This class is an internal class. Don't use, it might not work!
 * @author Florian Felix
 *
 */
public class HistoryElementsCreator {

	private List<Purchase> purchases;
	private ElementType elementType;
	
	public HistoryElementsCreator(List<Purchase> purchases, ElementType elementType) {
		this.purchases = purchases;
		this.elementType = elementType;
	}
	
	public List<HistoryElement> createHistoryElements(){
		if(elementType.equals(ElementType.Category))
			return createHistoryElementsSortedByCategory();
		else if(elementType.equals(ElementType.Company))
			return createHistoryElementsSortedByCompany();
		else if(elementType.equals(ElementType.Date))
			return createHistoryElementsSortedByDate();
		else 
			throw new InvalidParameterException("No Element Type of this kind");
	}
	
	public List<HistoryElement> createHistoryElementsSortedByCompany() {
		List<Company> companies = getAllCompaniesUsedIn(purchases);
		List<HistoryElement> historyElements = new ArrayList<>();
		for (Company company : companies) {
			historyElements.add(createHistoryElementByCompany(company));
		}
		return historyElements;
	}
	
	public List<HistoryElement> createHistoryElementsSortedByCategory() {
		List<Category> categories = getAllCategoriesUsedIn(purchases);
		List<HistoryElement> historyElements = new ArrayList<>();
		for (Category category : categories) {
			historyElements.add(createHistoryElementByCategory(category));
		}
		return historyElements;
	}
	
	public List<HistoryElement> createHistoryElementsSortedByDate(){
		List<LocalDate> dates = getAllDatesUsedIn(purchases);
		List<HistoryElement> historyElements = new ArrayList<>();
		for (LocalDate date : dates) {
			historyElements.add(createHistoryElementByDate(date));
		}
		return historyElements;
	}


	private List<LocalDate> getAllDatesUsedIn(List<Purchase> purchases) {
		return purchases.stream().map(p -> p.getDate()).distinct().toList();
	}

	private List<Company> getAllCompaniesUsedIn(List<Purchase> purchases) {
		return purchases.stream().map(p -> p.getCompany()).distinct().toList();
	}

	private List<Category> getAllCategoriesUsedIn(List<Purchase> purchases) {
		return purchases.stream().map(p -> p.getCategory()).distinct().toList();
	}
	
	private HistoryElement createHistoryElementByCompany(Company company){
		List<Purchase> purchasesPerCompany = purchases.stream().filter(p -> p.getCompany().equals(company)).toList();
		return new HistoryElementImp(company.getName(), purchasesPerCompany, elementType);
	}
	
	private HistoryElement createHistoryElementByCategory(Category category){
		List<Purchase> purchasesPerCategory = purchases.stream().filter(p -> p.getCategory().equals(category)).toList();
		return new HistoryElementImp(category.getName(), purchasesPerCategory, elementType);
	}

	private HistoryElement createHistoryElementByDate(LocalDate date) {
		List<Purchase> purchasesPerDate = purchases.stream().filter(p -> p.getDate().equals(date)).toList();
		return new HistoryElementImp(date.toString(), purchasesPerDate, elementType);
	}
}

package costtracker.adapter.document;

import java.time.LocalDate;
import java.util.List;

import costtracker.application.interfaces.HistoryDocumentAdapter;
import costtracker.application.interfaces.PurchaseRepositoryAdapter;
import costtracker.domain.businessobjects.IncorrectEntryException;
import costtracker.domain.businessobjects.Purchase;
import costtracker.domain.dependencyinjection.DependencyContainer;
import costtracker.domain.in.HistoryDocument;
import costtracker.domain.in.HistoryDocumentBase;
import costtracker.domain.in.enums.ElementType;

public class HistoryDocumentRepository implements HistoryDocumentAdapter {
	
	private PurchaseRepositoryAdapter purchaseRepositoryAdapter = DependencyContainer.getInstance().getDependency(PurchaseRepositoryAdapter.class);
	
	public HistoryDocumentRepository() {
	}
	
	public HistoryDocument getCSVHistoryDocumentSortedByCategory(String path, String name, LocalDate dateStart, LocalDate dateEnd, String description) {
		List<Purchase> purchases = purchaseRepositoryAdapter.getByTimespan(dateStart, dateEnd);
		try {
			return HistoryDocumentBase.HistoryDocumentBuilder
					.asCSV()
					.withKpi(ElementType.Category)
					.withName(name)
					.withPath(path)
					.withTimespan(dateStart, dateEnd)
					.withDescription(description)
					.withData(purchases).build();
		} catch (IncorrectEntryException e) {
			return null;
		}
	}
	
	public HistoryDocument getCSVHistoryDocumentSortedByCompany(String path, String name, LocalDate dateStart, LocalDate dateEnd, String description) {
		List<Purchase> purchases = purchaseRepositoryAdapter.getByTimespan(dateStart, dateEnd);
		try {
			return HistoryDocumentBase.HistoryDocumentBuilder
					.asCSV()
					.withKpi(ElementType.Company)
					.withName(name)
					.withPath(path)
					.withTimespan(dateStart, dateEnd)
					.withDescription(description)
					.withData(purchases).build();
		} catch (IncorrectEntryException e) {
			return null;
		}
	}
	
	public HistoryDocument getCSVHistoryDocumentSortedByDate(String path, String name, LocalDate dateStart, LocalDate dateEnd, String description) {
		List<Purchase> purchases = purchaseRepositoryAdapter.getByTimespan(dateStart, dateEnd);
		try {
			return HistoryDocumentBase.HistoryDocumentBuilder
					.asCSV()
					.withKpi(ElementType.Date)
					.withName(name)
					.withPath(path)
					.withTimespan(dateStart, dateEnd)
					.withDescription(description)
					.withData(purchases).build();
		} catch (IncorrectEntryException e) {
			return null;
		}
	}
	public HistoryDocument getXMLHistoryDocumentSortedByCategory(String path, String name, LocalDate dateStart, LocalDate dateEnd, String description) {
		List<Purchase> purchases = purchaseRepositoryAdapter.getByTimespan(dateStart, dateEnd);
		try {
			return HistoryDocumentBase.HistoryDocumentBuilder
					.asXML()
					.withKpi(ElementType.Category)
					.withName(name)
					.withPath(path)
					.withTimespan(dateStart, dateEnd)
					.withDescription(description)
					.withData(purchases).build();
		} catch (IncorrectEntryException e) {
			return null;
		}
	}
	
	public HistoryDocument getXMLHistoryDocumentSortedByCompany(String path, String name, LocalDate dateStart, LocalDate dateEnd, String description) {
		List<Purchase> purchases = purchaseRepositoryAdapter.getByTimespan(dateStart, dateEnd);
		try {
			return HistoryDocumentBase.HistoryDocumentBuilder
					.asXML()
					.withKpi(ElementType.Company)
					.withName(name)
					.withPath(path)
					.withTimespan(dateStart, dateEnd)
					.withDescription(description)
					.withData(purchases).build();
		} catch (IncorrectEntryException e) {
			return null;
		}
	}
	
	public HistoryDocument getXMLHistoryDocumentSortedByDate(String path, String name, LocalDate dateStart, LocalDate dateEnd, String description) {
		List<Purchase> purchases = purchaseRepositoryAdapter.getByTimespan(dateStart, dateEnd);
		try {
			return HistoryDocumentBase.HistoryDocumentBuilder
					.asXML()
					.withKpi(ElementType.Date)
					.withName(name)
					.withPath(path)
					.withTimespan(dateStart, dateEnd)
					.withDescription(description)
					.withData(purchases).build();
		} catch (IncorrectEntryException e) {
			return null;
		}
	}
	public HistoryDocument getJSONHistoryDocumentSortedByCategory(String path, String name, LocalDate dateStart, LocalDate dateEnd, String description) {
		List<Purchase> purchases = purchaseRepositoryAdapter.getByTimespan(dateStart, dateEnd);
		try {
			return HistoryDocumentBase.HistoryDocumentBuilder
					.asJSON()
					.withKpi(ElementType.Category)
					.withName(name)
					.withPath(path)
					.withTimespan(dateStart, dateEnd)
					.withDescription(description)
					.withData(purchases).build();
		} catch (IncorrectEntryException e) {
			return null;
		}
	}
	
	public HistoryDocument getJSONHistoryDocumentSortedByCompany(String path, String name, LocalDate dateStart, LocalDate dateEnd, String description) {
		List<Purchase> purchases = purchaseRepositoryAdapter.getByTimespan(dateStart, dateEnd);
		try {
			return HistoryDocumentBase.HistoryDocumentBuilder
					.asJSON()
					.withKpi(ElementType.Company)
					.withName(name)
					.withPath(path)
					.withTimespan(dateStart, dateEnd)
					.withDescription(description)
					.withData(purchases).build();
		} catch (IncorrectEntryException e) {
			return null;
		}
	}
	
	public HistoryDocument getJSONHistoryDocumentSortedByDate(String path, String name, LocalDate dateStart, LocalDate dateEnd, String description) {
		List<Purchase> purchases = purchaseRepositoryAdapter.getByTimespan(dateStart, dateEnd);
		try {
			return HistoryDocumentBase.HistoryDocumentBuilder
					.asJSON()
					.withKpi(ElementType.Date)
					.withName(name)
					.withPath(path)
					.withTimespan(dateStart, dateEnd)
					.withDescription(description)
					.withData(purchases).build();
		} catch (IncorrectEntryException e) {
			return null;
		}
	}
}

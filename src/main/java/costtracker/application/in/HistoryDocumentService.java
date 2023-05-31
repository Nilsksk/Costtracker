package costtracker.application.in;

import java.time.LocalDate;

import costtracker.application.interfaces.HistoryDocumentAdapter;
import costtracker.domain.dependencyinjection.DependencyContainer;
import costtracker.domain.in.HistoryDocument;
import costtracker.domain.in.enums.ElementType;

public class HistoryDocumentService {

	private HistoryDocumentDialogAdapter dialogAdapter = DependencyContainer.getInstance()
			.getDependency(HistoryDocumentDialogAdapter.class);
	private HistoryDocumentAdapter documentAdapter = DependencyContainer.getInstance()
			.getDependency(HistoryDocumentAdapter.class);

	private ElementType type;
	private LocalDate dateStart;
	private LocalDate dateEnd;
	private String path;
	private String name;
	private String description;
	private DocumentType docType;
	
	public void createHistoryDocument() {

		type = dialogAdapter.askForElementType();
		dateStart = dialogAdapter.askForDateStart();
		dateEnd = dialogAdapter.askForDateEnd();
		path = dialogAdapter.askForPath();
		name = dialogAdapter.askForName();
		description = dialogAdapter.askForDescription();
		docType = dialogAdapter.askForDocumentType();
		HistoryDocument document = null;
		if (type == ElementType.Category) {
			document = createHistoryDocumentSortedByCategory();
		} else if (type == ElementType.Date) {
			document = createHistoryDocumentSortedByDate();
		} else if (type == ElementType.Company) {
			document =createHistoryDocumentSortedByCompany();
		}
		if (document != null)
			document.print();

	}

	private HistoryDocument createHistoryDocumentSortedByCategory() {
		if (docType == DocumentType.CSV) {
			return documentAdapter.getCSVHistoryDocumentSortedByCategory(path, name, dateStart, dateEnd, description);
		}
		if (docType == DocumentType.XML) {
			return documentAdapter.getXMLHistoryDocumentSortedByCategory(path, name, dateStart, dateEnd, description);
		}
		if (docType == DocumentType.JSON) {
			return documentAdapter.getJSONHistoryDocumentSortedByCategory(path, name, dateStart, dateEnd, description);
		}
		return null;
	}

	private HistoryDocument createHistoryDocumentSortedByDate() {
		if (docType == DocumentType.CSV) {
			return documentAdapter.getCSVHistoryDocumentSortedByDate(path, name, dateStart, dateEnd, description);
		}
		if (docType == DocumentType.XML) {
			return documentAdapter.getXMLHistoryDocumentSortedByDate(path, name, dateStart, dateEnd, description);
		}
		if (docType == DocumentType.JSON) {
			return documentAdapter.getJSONHistoryDocumentSortedByDate(path, name, dateStart, dateEnd, description);
		}
		return null;
	}

	private HistoryDocument createHistoryDocumentSortedByCompany() {
		if (docType == DocumentType.CSV) {
			return documentAdapter.getCSVHistoryDocumentSortedByCompany(path, name, dateStart, dateEnd, description);
		}
		if (docType == DocumentType.XML) {
			return documentAdapter.getXMLHistoryDocumentSortedByCompany(path, name, dateStart, dateEnd, description);
		}
		if (docType == DocumentType.JSON) {
			return documentAdapter.getJSONHistoryDocumentSortedByCompany(path, name, dateStart, dateEnd, description);
		}
		return null;
	}

}

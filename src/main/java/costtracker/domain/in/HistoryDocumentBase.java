package costtracker.domain.in;

import costtracker.domain.businessobjects.IncorrectEntryException;
import costtracker.domain.businessobjects.Purchase;
import costtracker.domain.in.csv.CSVHistoryDocument.CSVHistoryDocumentBuilder;
import costtracker.domain.in.enums.ElementType;
import costtracker.domain.in.json.JSONHistoryDocument.JSONHistoryDocumentBuilder;
import costtracker.domain.in.xml.XMLHistoryDocument.XMLHistoryDocumentBuilder;

import java.io.File;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

public abstract class HistoryDocumentBase {

	protected HistoryDocumentHeader header;
	protected String name;
	protected String path;
	protected List<HistoryElement> historyElements;
	protected File file;
	protected DocumentPrinter printer;
	
	public static class HistoryDocumentBuilder<T extends HistoryDocumentBuilder<T>> {
		protected String name;
		protected String path;
		protected List<Purchase> purchases;
		protected ElementType elementType;
		protected LocalDate dateStart;
		protected LocalDate dateEnd;
		protected String description;

		public static CSVHistoryDocumentBuilder asCSV() {
			return new CSVHistoryDocumentBuilder();
		}
		
		public static XMLHistoryDocumentBuilder asXML() {
			return new XMLHistoryDocumentBuilder();
		}

		public static JSONHistoryDocumentBuilder asJSON() {
			return new JSONHistoryDocumentBuilder();
		}

		public T withName(String name) {
			this.name = name;
			return self();
		}

		public T withPath(String path) {
			this.path = path;
			return self();
		}

		public T withData(List<Purchase> purchases) {
			this.purchases = purchases;
			return self();
		}

		public T withKpi(ElementType elementType) {
			this.elementType = elementType;
			return self();
		}

		public T withTimespan(LocalDate dateStart, LocalDate dateEnd) {
			this.dateStart = dateStart;
			this.dateEnd = dateEnd;
			return self();
		}

		public T withDescription(String description) {
			this.description = description;
			return self();
		}

		@SuppressWarnings("unchecked")
		private T self() {
			return (T) this;
		}
		
		protected void validateDocument() throws IncorrectEntryException {
			Path p = Path.of(path);
			if(dateStart == null)
				throw new IncorrectEntryException("No start date!");
			else if(dateEnd == null)
				throw new IncorrectEntryException("No end date!");
			else if(!p.toFile().isDirectory())
				throw new IncorrectEntryException("Invalid path!");
			else if(purchases == null || purchases.size() == 0)
				throw new IncorrectEntryException("No purchases available to create history!");
			else if(name == null || name.equals(""))
				throw new IncorrectEntryException("No name!");
		}
	}

	public abstract void print();
}

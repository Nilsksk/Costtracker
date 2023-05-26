package costtracker.document;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import costtracker.businessobjects.Purchase;
import costtracker.document.CSVHistoryDocument.CSVHistoryDocumentBuilder;
import costtracker.document.elements.HistoryDocumentHeader;
import costtracker.document.elements.HistoryElement;
import costtracker.document.type.ElementType;

public abstract class HistoryDocumentBase {

	protected HistoryDocumentHeader header;
	protected List<HistoryElement> elements;
	protected String name;
	protected String path;
	protected List<HistoryElement> historyElements;
	protected File file;


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
			// TODO Auto-generated method stub
			return (T) this;
		}
	}

}

package costtracker.application.interfaces;

import java.time.LocalDate;

import costtracker.domain.in.HistoryDocument;

public interface HistoryDocumentAdapter {
	public HistoryDocument getCSVHistoryDocumentSortedByCategory(String path, String name, LocalDate dateStart, LocalDate dateEnd, String description);
	
	public HistoryDocument getCSVHistoryDocumentSortedByCompany(String path, String name, LocalDate dateStart, LocalDate dateEnd, String description);
	
	public HistoryDocument getCSVHistoryDocumentSortedByDate(String path, String name, LocalDate dateStart, LocalDate dateEnd, String description);
	
	public HistoryDocument getXMLHistoryDocumentSortedByCategory(String path, String name, LocalDate dateStart, LocalDate dateEnd, String description);
	
	public HistoryDocument getXMLHistoryDocumentSortedByCompany(String path, String name, LocalDate dateStart, LocalDate dateEnd, String description);
	
	public HistoryDocument getXMLHistoryDocumentSortedByDate(String path, String name, LocalDate dateStart, LocalDate dateEnd, String description);
	
	public HistoryDocument getJSONHistoryDocumentSortedByCategory(String path, String name, LocalDate dateStart, LocalDate dateEnd, String description);
	
	public HistoryDocument getJSONHistoryDocumentSortedByCompany(String path, String name, LocalDate dateStart, LocalDate dateEnd, String description);
	
	public HistoryDocument getJSONHistoryDocumentSortedByDate(String path, String name, LocalDate dateStart, LocalDate dateEnd, String description);
}

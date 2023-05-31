package costtracker.application.in;

import java.time.LocalDate;

import costtracker.domain.in.enums.ElementType;

public interface HistoryDocumentDialogAdapter {

	String askForPath();

	String askForName();

	LocalDate askForDateStart();

	String askForDescription();

	LocalDate askForDateEnd();

	ElementType askForElementType();

	DocumentType askForDocumentType();
	
}

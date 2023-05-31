package costtracker.adapter.document;

import java.time.LocalDate;

import costtracker.adapter.dialog.DialogDisplay;
import costtracker.application.in.DocumentType;
import costtracker.application.in.HistoryDocumentDialogAdapter;
import costtracker.domain.dependencyinjection.DependencyContainer;
import costtracker.domain.in.enums.ElementType;
import costtracker.plugin.ui.DialogueHelper;

public class HistoryDocumentDialog implements HistoryDocumentDialogAdapter {

	public DialogDisplay display = DependencyContainer.getInstance()
			.getDependency(DialogDisplay.class);

	@Override
	public String askForPath() {
		return display.askQuestionWithReturn("Bitte geben Sie den Pfad des Dokuments ein:");
	}

	@Override
	public String askForName() {
		return display.askQuestionWithReturn("Bitte geben Sie den Namen des Dokuments ein:");
	}

	@Override
	public LocalDate askForDateStart() {
		String date = display.askQuestionWithReturn(
				"Bitte geben Sie das Startdatum ein, ab dem die Einkaufhistorie erstellt werden soll (DD:MM:YYYY):");
		return convertDate(date);

	}

	@Override
	public LocalDate askForDateEnd() {
		String date = display.askQuestionWithReturn(
				"Bitte geben Sie das Enddatum ein, bis zu dem die Einkaufhistorie erstellt werden soll (DD:MM:YYYY):");
		return convertDate(date);
	}

	@Override
	public String askForDescription() {
		return display.askQuestionWithReturn("Bitte geben Sie die Beschreibung des Dokuments ein:");
	}

	@Override
	public ElementType askForElementType() {
		String elementType = display.askQuestionWithReturn(
				"Bitte geben Sie den Sortiertyp an, nachdem die Einkäufe der Historie gefiltert werden sollen:\n"
						+ "Nach Datum = 1, nach Firma = 2 oder nach Kategorie = 3");
		return convertElementType(elementType);
	}

	@Override
	public DocumentType askForDocumentType() {
		String documenttype = display.askQuestionWithReturn("Bitte geben Sie den Dokumententyp an, in dem das Document erstellt werden soll:\n"
				+ "als CSV = 1, als XML = 2 oder als JSON = 3");
		return convertDocumentType(documenttype);
	}

	private ElementType convertElementType(String input) {
		int i = Integer.parseInt(input);
		ElementType type = switch (i) {
		case 1: {
			yield ElementType.Date;
		}
		case 2: {
			yield ElementType.Company;
		}
		case 3: {
			yield ElementType.Category;
		}
		default:
			yield null;
		};
		return type;
	}

	private DocumentType convertDocumentType(String input) {
		int i = Integer.parseInt(input);
		DocumentType type = switch (i) {
		case 1: {
			yield DocumentType.CSV;
		}
		case 2: {
			yield DocumentType.XML;
		}
		case 3: {
			yield DocumentType.JSON;
		}
		default:
			yield null;
		};
		return type;
	}

	private LocalDate convertDate(String input) {
		LocalDate date;
		String[] dateValues = input.split(":");
		try {
			int day = Integer.parseInt(dateValues[0]);
			int month = Integer.parseInt(dateValues[1]);
			int year = Integer.parseInt(dateValues[2]);
			date = LocalDate.of(year, month, day);
		} catch (Exception e) {
			date = null;
		}
		return date;
	}
}

package costtracker.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import costtracker.businessobjects.Company;

public class CompanyManager implements Editor, Adder {

	private List<Company> companies;

	@Override
	public void add() {
		DialogueHelper.startDialogue("Enter Taste drücken um neue  Firma und optional dessen Standort hinzuzufügen");

		String newCompanyName = DialogueHelper.inputDialogue("Name");
		String newCompanyLocation = DialogueHelper.inputDialogue("Standort");
		// Hinzufügen der neuen Firma zur Datenbank

	}

	@Override
	public void edit() {
		DialogueHelper.startDialogue("Enter Taste drücken zum Anzeigen aller existierenden Einträge von Firmen");
		// this.companies = new ArrayList<Company>();

		DialogueHelper.printCompanies(companies);

		Company company = getCompanyToEdit();

		String editedName = DialogueHelper.changeDialogue(company.getName());
		if (!editedName.isEmpty()) {
			company.setName(editedName);
		}
		String editedLocation = DialogueHelper.changeDialogue(company.getLocation());
		if (!editedLocation.isEmpty()) {
			company.setLocation(editedLocation);
		}

		// Firma mit editierter Firma überschreiben

	}

	private Company getCompanyToEdit() {
		Optional<Company> company0;

		do {
			int id = DialogueHelper.getIdDialogue("ID der Firma auswählen, die Sie bearbeiten möchten: ");
			final int idCopy = id;
			company0 = companies.stream().filter(c -> c.getId() == idCopy).findFirst();
			if (id == -1) {
				DialogueHelper.println("Falsche Eingabe!");
				continue;
			}
			if (company0.isEmpty()) {
				DialogueHelper.println("ID " + id + " nicht vorhanden.");
			}

		} while (company0.isEmpty());

		return company0.get();
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

}

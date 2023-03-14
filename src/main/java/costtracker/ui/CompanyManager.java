package costtracker.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import costtracker.buisnesslogic.CompanyHandler;
import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;
import costtracker.ui.interfaces.Adder;
import costtracker.ui.interfaces.Editor;

public class CompanyManager implements Editor, Adder {

	private CompanyHandler companyHandler;
	boolean submit;

	@Override
	public void add() throws SQLException {
		boolean created = false;
		DialogueHelper.startDialogue("Enter Taste drücken um neue  Firma und optional dessen Standort hinzuzufügen");

		String newCompanyName = DialogueHelper.inputDialogue("Name");
		String newCompanyLocation = DialogueHelper.inputDialogue("Standort");
		submit = DialogueHelper.submitEntry();
		
		if(submit) {
			created = companyHandler.create(new Company(0, newCompanyName, newCompanyLocation));		
		}

		DialogueHelper.validateCreation(created);
		//Testen!
	}

	@Override
	public void edit() throws SQLException {
		boolean updated = false;
		DialogueHelper.startDialogue("Enter Taste drücken zum Anzeigen aller existierenden Einträge von Firmen");
		// this.companies = new ArrayList<Company>();

		DialogueHelper.printCompanies(companyHandler.getAll());

		Company company = getCompanyToEdit();

		String editedName = DialogueHelper.changeDialogue(company.getName());
		if (!editedName.isEmpty()) {
			company.setName(editedName);
		}
		String editedLocation = DialogueHelper.changeDialogue(company.getLocation());
		if (!editedLocation.isEmpty()) {
			company.setLocation(editedLocation);
		}

		submit = DialogueHelper.submitEntry();
		
		if(submit) {
			updated = companyHandler.update(new Company(0, editedName, editedLocation));
		}

		DialogueHelper.validateCreation(updated);
	}

	private Company getCompanyToEdit() throws SQLException {
		Company company;

		do {
			int id = DialogueHelper.getIdDialogue("ID der Firma auswählen, die Sie bearbeiten möchten: ");
			company = GetBusinessObject.getById(id, companyHandler.getAll(), Company::getId);

		} while (company.equals(null));

		return company;
	}
}

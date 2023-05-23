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

	private CompanyHandler companyHandler = new CompanyHandler();
	boolean submit;

	public CompanyManager() {
		this.companyHandler = new CompanyHandler();
	}
	@Override
	public void add() throws SQLException {
		boolean created = false;
		DialogueHelper.startDialogue("Enter Taste drücken um neue  Firma und optional dessen Standort hinzuzufügen");

		String newCompanyName = DialogueHelper.inputDialogue("Name");			
		String newCompanyLocation = DialogueHelper.inputDialogue("Standort");
		submit = DialogueHelper.submitEntry();
		
		if(submit && !newCompanyName.isEmpty() && !newCompanyLocation.isEmpty()) {
			created = companyHandler.create(new Company(newCompanyName, newCompanyLocation));		
			DialogueHelper.validateCreation(created);
		}
		else{
			DialogueHelper.print("Unzureichende Eingaben!");
		}
	}

	@Override
	public void edit() throws SQLException {
		boolean updated = false;
		DialogueHelper.startDialogue("Enter Taste drücken zum Anzeigen aller existierenden Einträge von Firmen");

		DialogueHelper.printCompanies(companyHandler.getAll());

		Company company = getCompanyToEdit();

		String editedName = DialogueHelper.changeDialogue("Name", company.getName());
		if (editedName.isEmpty()) {
			editedName = company.getName();
		}
		else{
			company.setName(editedName);
		}
		String editedLocation = DialogueHelper.changeDialogue("Standort", company.getLocation());
		if (editedLocation.isEmpty()) {
			editedLocation = company.getLocation();
		}
		else {
			company.setLocation(editedLocation);
		}

		submit = DialogueHelper.submitEntry();
		
		if(submit) {
			updated = companyHandler.update(new Company(company.getId(),editedName, editedLocation));
		}

		DialogueHelper.validateCreation(updated);
	}

	private Company getCompanyToEdit() throws SQLException {
		int id;
		do {
			id = DialogueHelper.getIdDialogue("ID der Firma auswählen, die Sie bearbeiten möchten: ");
		}while(id == -1);
		Company company = GetBusinessObject.getById(id, companyHandler.getAll(), Company::getId);


		return company;
	}
}

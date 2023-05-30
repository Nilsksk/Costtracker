package costtracker.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import costtracker.buisnesslogic.CompanyHandler;
import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;
import costtracker.businessobjects.IncorrectEntryException;
import costtracker.businessobjects.Purchase;
import costtracker.ui.interfaces.Activator;
import costtracker.ui.interfaces.Adder;
import costtracker.ui.interfaces.Deactivator;
import costtracker.ui.interfaces.Editor;

public class CompanyManager implements Editor, Adder, Deactivator, Activator {

	boolean submit;

	@Override
	public void add() throws SQLException {
		boolean created = false;
		CompanyHandler companyHandler = new CompanyHandler();
		DialogueHelper.startDialogue("Enter Taste drücken um neue  Firma und optional dessen Standort hinzuzufügen");
		String newCompanyName = DialogueHelper.inputDialogue("Name");			
		String newCompanyLocation = DialogueHelper.inputDialogue("Standort");
		submit = DialogueHelper.submitEntry();
		try {
			Company company = Company.CompanyBuilder
					.withName(newCompanyName)
					.withId(0)
					.withLocation(newCompanyLocation)
					.build();
			created = companyHandler.create(company);		
		} catch (IncorrectEntryException e) {
			DialogueHelper.println("Unzureichende Eingabe!");
		}
		if(submit && created) {
			String succesful = "Angelegt!";
			String unsuccesful = "Anlegen fehlgeschlagen!";
			DialogueHelper.validateCreation(created, succesful, unsuccesful);
		}
	}

	@Override
	public void edit() throws SQLException {
		boolean updated = false;
		CompanyModelFactory companyModelFactory = new CompanyModelFactory();
		CompanyHandler companyHandler = new CompanyHandler();
		DialogueHelper.startDialogue("Enter Taste drücken zum Anzeigen aller existierenden Einträge von Firmen");
		DialogueHelper.printCompanies(companyModelFactory.createCompanyModels(companyHandler.getEnabled()));
		Company company = getCompanyToEdit(companyHandler);
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
		try {
			company = Company.CompanyBuilder
					.withName(editedName)
					.withId(0)
					.withLocation(editedLocation)
					.build();
			updated = companyHandler.update(company);		
		} catch (IncorrectEntryException e) {
			DialogueHelper.println("Fehler!");
		}
		if(submit) {
			String succesful = "Erfolgreich bearbeitet!";
			String unsuccesful = "Bearbeiten fehlgeschlagen!";
			DialogueHelper.validateCreation(updated, succesful, unsuccesful);
		}
	}
	
	@Override
	public void deactivate() throws SQLException {
		CompanyModelFactory companyModelFactory = new CompanyModelFactory();
		CompanyHandler companyHandler = new CompanyHandler();
		DialogueHelper.startDialogue("Enter Taste drücken zum Anzeigen aller existierenden Einträge von aktivierten Firmen");
		DialogueHelper.printCompanies(companyModelFactory.createCompanyModels(companyHandler.getEnabled()));
		Company company = getCompanyToEdit(companyHandler);
		if (company != null) {
			List<Company> companyToDisable= new ArrayList<Company>();
			companyToDisable.add(company);
			DialogueHelper.printCompanies(companyModelFactory.createCompanyModels(companyToDisable));
			succesfulDeactivated(company, companyHandler);
		}
	}
	
	private void succesfulDeactivated(Company company, CompanyHandler companyHandler) throws SQLException {
		if(DialogueHelper.validateDeleteOrDeactivation("Erfolgreich deaktiviert!")) {
			companyHandler.disable(company.getId());
		}	
	}
	
	@Override
	public void activate() throws SQLException {
		CompanyModelFactory companyModelFactory = new CompanyModelFactory();
		CompanyHandler companyHandler = new CompanyHandler();
		DialogueHelper.startDialogue("Enter Taste drücken zum Anzeigen aller existierenden Einträge von deaktivierten Firmen");
		DialogueHelper.printCompanies(companyModelFactory.createCompanyModels(companyHandler.getDisabled()));
		Company company = getCompanyToEdit(companyHandler);
		if (company != null) {
			List<Company> companyToEnable= new ArrayList<Company>();
			companyToEnable.add(company);
			DialogueHelper.printCompanies(companyModelFactory.createCompanyModels(companyToEnable));
			succesfulActivated(company, companyHandler);
		}
	}
	
	private void succesfulActivated(Company company, CompanyHandler companyHandler) throws SQLException {
		if(DialogueHelper.validateEnable("Erfolgreich aktiviert!")) {
			companyHandler.enable(company.getId());
		}	
	}

	private Company getCompanyToEdit(CompanyHandler companyHandler) throws SQLException {
		Company company;
		try {
			int id = DialogueHelper.getIntDialogue("ID der Firma auswählen, die Sie bearbeiten möchten");
			company = companyHandler.getById(id);
		}catch(Exception e) {
			company = null;
		}
		return company;
	}
}

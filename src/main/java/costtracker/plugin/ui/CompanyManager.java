package costtracker.plugin.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import costtracker.application.handlers.CompanyHandler;
import costtracker.domain.businessobjects.Company;
import costtracker.domain.businessobjects.IncorrectEntryException;
import costtracker.plugin.ui.interfaces.Activator;
import costtracker.plugin.ui.interfaces.Adder;
import costtracker.plugin.ui.interfaces.Deactivator;
import costtracker.plugin.ui.interfaces.Editor;

public class CompanyManager implements Editor, Adder, Deactivator, Activator {

	boolean submit;

	@Override
	public void add() throws SQLException {
		boolean created = false;
		CompanyHandler companyHandler = new CompanyHandler();
		String addCompany = "Enter Taste drücken um neue  Firma und optional dessen Standort hinzuzufügen";
		DialogueHelper.startDialogue(addCompany);
		String name = "Name";
		String newCompanyName = DialogueHelper.inputDialogue(name);
		String location = "Standort";
		String newCompanyLocation = DialogueHelper.inputDialogue(location);
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
		boolean correct = submit && created; 
		if(correct) {
			String succesful = "Angelegt!";
			String unsuccesful = "Anlegen fehlgeschlagen!";
			DialogueHelper.validateCreation(created, succesful, unsuccesful);
		}
	}

	@Override
	public void edit() throws SQLException {
		CompanyModelFactory companyModelFactory = new CompanyModelFactory();
		CompanyHandler companyHandler = new CompanyHandler();
		String printAllCompanies = "Enter Taste drücken zum Anzeigen aller existierenden Einträge von Firmen";
		DialogueHelper.startDialogue(printAllCompanies);
		List<Company> enabledCompanies = companyHandler.getEnabled();
		List<CompanyModel> enabledCompanyModels = companyModelFactory.createCompanyModels(enabledCompanies);
		boolean moreThenZeroModels = enabledCompanyModels.size() > 0;
		if (moreThenZeroModels) {
			editCompany(enabledCompanyModels, companyHandler);
		}
	}
	
	private void editCompany(List<CompanyModel> enabledCompanyModels, CompanyHandler companyHandler) throws SQLException {
		CompanyPrinter.printCompanies(enabledCompanyModels);
		String idCompany = "ID der Firma die Sie bearbeiten möchten";
		Company company = getCompanyToEdit(enabledCompanyModels, idCompany);
		boolean companyNotNull = company != null;
		if (companyNotNull) {	
			editIfNotNull(company, companyHandler);
		}
	}
	
	private void editIfNotNull(Company company, CompanyHandler companyHandler) throws SQLException {
		boolean updated = false;
		String name = "Name";
		String companyName = company.getName();
		String editedName = DialogueHelper.changeDialogue(name, companyName);
		boolean emptyName = editedName.isEmpty();
		if (emptyName) {
			editedName = company.getName();
		}
		else{
			company.setName(editedName);
		}
		String location = "Standort";
		String companyLocation = company.getLocation();
		String editedLocation = DialogueHelper.changeDialogue(location, companyLocation);
		boolean emptyLocation = editedLocation.isEmpty();
		if (emptyLocation) {
			editedLocation = company.getLocation();
		}
		else {
			company.setLocation(editedLocation);
		}
		submit = DialogueHelper.submitEntry();
		try {
			int id = company.getId();
			company = Company.CompanyBuilder
					.withName(editedName)
					.withId(id)
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
		String printActiveCompanies = "Enter Taste drücken zum Anzeigen aller existierenden Einträge von aktivierten Firmen";
		DialogueHelper.startDialogue(printActiveCompanies);
		List<Company> enabledCompanies = companyHandler.getEnabled();
		List<CompanyModel> enabledCompanyModels = companyModelFactory.createCompanyModels(enabledCompanies);
		boolean moreThenZeroModels = enabledCompanyModels.size() > 0;
		if (moreThenZeroModels) {
			deactivateCompany(enabledCompanyModels, companyHandler, companyModelFactory);
		}
	}
	
	private void deactivateCompany(List<CompanyModel> enabledCompanyModels, CompanyHandler companyHandler, CompanyModelFactory companyModelFactory) throws SQLException {
		CompanyPrinter.printCompanies(enabledCompanyModels);
		String idCompany = "ID der Firma die sie deaktivieren möchten";
		Company company = getCompanyToEdit(enabledCompanyModels, idCompany);
		boolean companyIsNotNull = company != null;
		if (companyIsNotNull) {
			List<Company> companyToDisable= new ArrayList<Company>();
			companyToDisable.add(company);
			List<CompanyModel> companiesToDisable = companyModelFactory.createCompanyModels(companyToDisable);
			CompanyPrinter.printCompanies(companiesToDisable);
			succesfulDeactivated(company, companyHandler);
		}	
	}
	
	private void succesfulDeactivated(Company company, CompanyHandler companyHandler) throws SQLException {
		String deactivated = "Erfolgreich deaktiviert!";
		boolean isDeactivated = DialogueHelper.validateDeleteOrDeactivation(deactivated);
		if(isDeactivated) {
			int id = company.getId();
			companyHandler.disable(id);
		}	
	}
	
	@Override
	public void activate() throws SQLException {
		CompanyModelFactory companyModelFactory = new CompanyModelFactory();
		CompanyHandler companyHandler = new CompanyHandler();
		DialogueHelper.startDialogue("Enter Taste drücken zum Anzeigen aller existierenden Einträge von deaktivierten Firmen");
		List<Company> disabledCompanies = companyHandler.getDisabled();
		List<CompanyModel> disabledComapnyModels = companyModelFactory.createCompanyModels(disabledCompanies);
		if (disabledComapnyModels.size() > 0) {
			activateCategory(disabledComapnyModels, companyHandler, companyModelFactory);
		}
	}
	
	private void activateCategory(List<CompanyModel> disabledComapnyModels, CompanyHandler companyHandler, CompanyModelFactory companyModelFactory) throws SQLException {
		CompanyPrinter.printCompanies(disabledComapnyModels);
		String idCompany = "ID der Firma die Sie aktivieren möchten";
		Company company = getCompanyToEdit(disabledComapnyModels, idCompany);
		boolean companyIsNotNull = company != null;
		if (companyIsNotNull) {
			List<Company> companyToEnable= new ArrayList<Company>();
			companyToEnable.add(company);
			List<CompanyModel> companiesToEnable = companyModelFactory.createCompanyModels(companyToEnable);
			CompanyPrinter.printCompanies(companiesToEnable);
			succesfulActivated(company, companyHandler);
		}	
	}
	
	private void succesfulActivated(Company company, CompanyHandler companyHandler) throws SQLException {
		String activated = "Erfolgreich aktiviert!";
		boolean isActivated = DialogueHelper.validateEnable(activated);
		if(isActivated) {
			int id = company.getId();
			companyHandler.enable(id);
		}	
	}

	private Company getCompanyToEdit(List<CompanyModel> companyModels, String input) throws SQLException {
		Company company;
		try {
			int id = DialogueHelper.getIntDialogue(input);
			CompanyModel companyModel = companyModels.stream().filter(c -> c.getPosition() == id).findAny().orElse(null);
			company = companyModel.getCompany();
		}catch(Exception e) {
			company = null;
		}
		return company;
	}
}

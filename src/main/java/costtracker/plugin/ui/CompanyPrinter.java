package costtracker.plugin.ui;

import java.util.List;

public class CompanyPrinter {

	public static void printCompanies(List<CompanyModel> companies) {
		boolean moreThenZeroCompanies = companies.size() > 0;
		if(moreThenZeroCompanies) {	
			int longestCompanyId = companies
								.stream()
								.mapToInt(c -> String
								.valueOf(c.getPosition())
								.length())
								.max()
								.getAsInt();
			int longestCompanyName = companies
								.stream()
								.mapToInt(c -> c.getCompany()
								.getName()
								.length())
								.max()
								.getAsInt();
			companyPrinter(companies, longestCompanyId, longestCompanyName);
		}
	}

	private static void companyPrinter(List<CompanyModel> companies, int longestCompanyId, int longestCompanyName) {
		for (CompanyModel companyModel : companies) {
			int numberWhitespaces = (longestCompanyId - String.valueOf(companyModel.getPosition()).length());
			String printId = " " + companyModel.getPosition() + DialogueHelper.addWhitespaces(numberWhitespaces) + " | ";
			DialogueHelper.print(printId);
			numberWhitespaces = (longestCompanyName - companyModel
													.getCompany()
													.getName()
													.length());
			String printName = companyModel.getCompany().getName() + DialogueHelper.addWhitespaces(numberWhitespaces) + " | ";
			DialogueHelper.print(printName);
			String printLocation = companyModel.getCompany().getLocation();
			DialogueHelper.printLine(printLocation);
		}
	}
}

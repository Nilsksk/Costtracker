package costtracker.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import costtracker.businessobjects.Category;
import costtracker.businessobjects.Company;

public class ReSet {

	public static void setNewString(String input, Consumer<String> consumer) {
		if (!input.isEmpty()) {
			consumer.accept(input);
		}
	}
	
	public static void setNewDouble(String input, Consumer<Double> consumer) {
		if (!input.isEmpty()) {
			double convertedInput = Double.parseDouble(input);
			consumer.accept(convertedInput);
		}
	}

	public static void setNewDate(String input, Consumer<LocalDate> consumer) {
		if (!input.isEmpty()) {
			String[] dateInput = input.split(":");
			LocalDate localDate = LocalDate.of(Integer.valueOf(dateInput[2]), Integer.valueOf(dateInput[1]),Integer.valueOf(dateInput[0]));
			consumer.accept(localDate);
		}
	}

	public static void setNewCompany(String input, Consumer<Company> consumer, List<Company> companies) {
		if (!input.isEmpty()) {
			int convertedInput = Integer.parseInt(input);
			final int id = convertedInput;
			Optional<Company> company0 = companies.stream().filter(c -> c.getId() == id).findFirst();
			if (company0.isPresent()) {
				Company company = company0.get();
				consumer.accept(company);
			}
		}
	}
	
	public static void setNewCategory(String input, Consumer<Category> consumer, List<Category> categories) {
		if(!input.isEmpty()) {
			int convertedInput = Integer.parseInt(input);
			final int id = convertedInput;
			Optional<Category> category0 = categories.stream().filter(c -> c.getId() == id).findFirst();
			if(category0.isPresent()) {
				Category category = category0.get();
				consumer.accept(category);
			}
		}
	}
}

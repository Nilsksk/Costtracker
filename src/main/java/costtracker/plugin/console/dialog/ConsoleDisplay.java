package costtracker.plugin.console.dialog;

import java.util.Scanner;

import costtracker.adapter.dialog.DialogDisplay;
import costtracker.domain.dependencyinjection.DependencyContainer;

public class ConsoleDisplay implements DialogDisplay {

	Scanner scanner = DependencyContainer.getInstance().getDependency(Scanner.class);

	@Override
	public String askQuestionWithReturn(String question) {
		System.out.println(question);
		return scanner.nextLine();
	}
}

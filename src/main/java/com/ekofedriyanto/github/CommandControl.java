package com.ekofedriyanto.github;

import com.ekofedriyanto.github.command.Command;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class CommandControl {
	final private List<Command> commands;
	final static private Scanner scanner = new Scanner(System.in);

	public static String askMenuString = "Select menu : ";
	public static String menuNotRegisteredString = "Menu selection not registered";
	public static String errorMessageString = "Failed to execute : ";
	public static String separatorString = "==========================================";

	public CommandControl(List<Command> commands) {
		this.commands = commands;
	}

	public void printMenu() {
		commands.forEach(Command::printMenu);
		System.out.println(separatorString);
		System.out.println(askMenuString);
		String menu = scanner.nextLine();

		executeCommand(menu);
	}

	public void executeCommand(String menu) {
		Stream<Command> streamCommands = commands.stream()
				.filter(c -> c.canHandle(menu));

		if (streamCommands.count() == 0) {
			System.out.println(separatorString);
			System.out.println(menuNotRegisteredString);
			System.out.println(separatorString);
		} else {
			commands.stream()
					.filter(c -> c.canHandle(menu))
					.forEach(c -> {
						c.printConfirmation();
						String input = scanner.nextLine();
						try {
							System.out.println(separatorString);
							c.execute(input);
							System.out.println(separatorString);
						} catch (Exception e) {
							System.out.println(separatorString);
							System.out.println(errorMessageString + e.getMessage());
							System.out.println(separatorString);
						}
					});

		}

		printMenu();
	}
}

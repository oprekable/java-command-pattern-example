package com.ekofedriyanto.github;

import com.ekofedriyanto.github.command.Command;

import java.io.InputStream;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class CommandControl {
	final private List<Command> commands;

	final public static String askMenuString = "Select menu : ";
	final public static String menuNotRegisteredString = "Menu selection not registered";
	final public static String errorMessageString = "Failed to execute : ";
	final public static String separatorString = "==========================================";

	public CommandControl(List<Command> commands) {
		this.commands = commands;
	}

	public void printMenu() {
		commands.forEach(Command::printMenu);
		System.out.println(separatorString);
		System.out.println(askMenuString);
	}

	public void executeCommand(String menu, InputStream in) {
		final Supplier<Stream<Command>> streamCommands = () -> commands.stream()
				.filter(c -> c.canHandle(menu));

		if (streamCommands.get().count() == 0) {
			System.out.println(separatorString);
			System.out.println(menuNotRegisteredString);
			System.out.println(separatorString);
		} else {
			final Scanner scanner = new Scanner(in);

			streamCommands.get().forEach(c -> {
						c.printConfirmation();
						try {
							System.out.println(separatorString);
							c.execute(scanner.nextLine());
							System.out.println(separatorString);
						} catch (Exception e) {
							System.out.println(separatorString);
							System.out.println(errorMessageString + e.getMessage());
							System.out.println(separatorString);

							if(e instanceof NoSuchElementException) {
								System.exit(0);
							}
						}
					});

		}
	}
}

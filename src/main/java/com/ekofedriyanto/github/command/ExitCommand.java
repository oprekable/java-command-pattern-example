package com.ekofedriyanto.github.command;

import java.util.Arrays;

public class ExitCommand implements Command {
	final private String[] commandString = new String[]{"x","X"};

	public static String menuString = "to quit!!";
	public static String menuStringFormat = "* Enter \"%s\" : %s";
	public static String commandStringJoinDelimiter = "\" or \"";
	public static String confirmationString = "Killing the application !!";


	public ExitCommand() {
	}

	@Override
	public void execute(String input) {
		System.exit(0);
	}

	@Override
	public long[] calculate(int[] arr) {
		return new long[0];
	}

	@Override
	public boolean canHandle(String command) {
		return Arrays.asList(commandString).contains(command);
	}

	@Override
	public void printMenu() {
		System.out.println(String.format(menuStringFormat, String.join(commandStringJoinDelimiter, commandString), menuString));
	}

	@Override
	public void printConfirmation() {
		System.out.println(confirmationString);
	}

	@Override
	public void printResult(int[] inputInt, long[] calculate) {

	}
}

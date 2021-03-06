package com.ekofedriyanto.github.command;

import com.ekofedriyanto.github.utility.InputOutputUtility;

import java.util.Arrays;

public class MultiplyCommand implements Command {
	final private String[] commandString = new String[]{"m","M"};
	final private String inputDelimiter = ",";

	final public static String menuString = "to multiply X & Y & ..., and print the result";
	final public static String menuStringFormat = "* Enter \"%s\" : %s";
	final public static String commandStringJoinDelimiter = "\" or \"";
	final public static String confirmationString = "Please enter list of numbers separated by \",\" (example : 1, 2, 3) : ";
	final public static String resultStringFormat = "Result of multiply \"%s\" : %s";

	public MultiplyCommand() {
	}

	@SuppressWarnings("Duplicates")
	@Override
	public void execute(String input) {
		int[] inputInt = new int[0];
		try {
			inputInt = InputOutputUtility.stringToIntArray(input, inputDelimiter);
		} catch (Exception e) {
		}

		if(inputInt.length < 2)
			throw new RuntimeException("Invalid Data");

		final long[] calculate = calculate(inputInt);

		printResult(inputInt, calculate);
	}

	@Override
	public long[] calculate(int[] arr) {
		final long multiply = Arrays.stream(arr).asLongStream().reduce(1, (a, b) -> a * b);
		return new long[]{multiply};
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
		final String[] arrayInputString = Arrays.stream(inputInt)
				.mapToObj(String::valueOf)
				.toArray(String[]::new);

		final String resultString = String.format(resultStringFormat, String.join(" * ", arrayInputString), Arrays.toString(calculate));

		System.out.println(resultString);
	}
}

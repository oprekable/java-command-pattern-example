package com.ekofedriyanto.github.command;

import com.ekofedriyanto.github.utility.InputOutputUtility;

import java.util.Arrays;
import java.util.stream.Stream;

public class FibonacciCommand implements Command {
	final private String[] commandString = new String[]{"f","F"};
	final private String inputDelimiter = ",";

	final public static String menuString = "to find first N \"Fibonacci Sequence\", and print the result";
	final public static String menuStringFormat = "* Enter \"%s\" : %s";
	final public static String commandStringJoinDelimiter = "\" or \"";
	final public static String confirmationString = "Please enter a number (example : 4) : ";
	final public static String resultStringFormat = "Result of first \"%s\" Fibonacci Sequence : %s";

	public FibonacciCommand() {
	}

	@SuppressWarnings("Duplicates")
	@Override
	public void execute(String input) {
		int[] inputInt = new int[0];
		try {
			inputInt = InputOutputUtility.stringToIntArray(input, inputDelimiter);
		} catch (Exception e) {
		}

		if(inputInt.length != 1)
			throw new RuntimeException("Invalid Data");

		final long[] calculate = calculate(inputInt);

		printResult(inputInt, calculate);
	}

	@Override
	public long[] calculate(int[] arr) {
		final int limit = arr[0];
		return Stream.iterate(new long[]{0, 1}, i -> new long[]{i[1], i[0] + i[1]})
				.limit(limit)
				.mapToLong(s -> s[0]).toArray();
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
		final String resultString = String.format(resultStringFormat, Arrays.toString(inputInt), Arrays.toString(calculate));

		System.out.println(resultString);
	}
}

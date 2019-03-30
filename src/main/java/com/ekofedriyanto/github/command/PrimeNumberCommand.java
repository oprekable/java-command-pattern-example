package com.ekofedriyanto.github.command;

import com.ekofedriyanto.github.utility.InputOutputUtility;

import java.util.Arrays;
import java.util.stream.LongStream;

public class PrimeNumberCommand implements Command {
	final private String[] commandString = new String[]{"p","P"};
	final private String inputDelimiter = ",";

	final public static String menuString = "to find first N prime number, and print the result";
	final public static String menuStringFormat = "* Enter \"%s\" : %s";
	final public static String commandStringJoinDelimiter = "\" or \"";
	final public static String confirmationString = "Please enter a number (example : 4) : ";
	final public static String resultStringFormat = "Result of first \"%s\" prime number : %s";

	public PrimeNumberCommand() {
	}

	public static boolean isPrime(long n) {
		for (long i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
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
		return LongStream.iterate(2, i -> i + 1)
				.filter(PrimeNumberCommand::isPrime)
				.limit(limit)
				.toArray();
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

package com.ekofedriyanto.github.command;

public interface Command {
	void execute(String input);
	long[] calculate(int[] arr);
	boolean canHandle(String command);
	void printMenu();
	void printConfirmation();
	void printResult(int[] inputInt, long[] calculate);
}

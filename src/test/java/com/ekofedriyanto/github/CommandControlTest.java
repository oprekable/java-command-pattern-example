package com.ekofedriyanto.github;

import com.ekofedriyanto.github.command.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CommandControlTest {

	private CommandControl commandControl;


	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@After
	public void restoreStreams() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}

	@Before
	public void setUp() {
		final Command sumCommand = new SumCommand();
		final Command multiplyCommand = new MultiplyCommand();
		final Command primeNumberCommand = new PrimeNumberCommand();
		final Command fibonacciCommand = new FibonacciCommand();
		final Command exitCommand = new ExitCommand();
		final List<Command> commands = new ArrayList<>();

		commands.add(sumCommand);
		commands.add(multiplyCommand);
		commands.add(primeNumberCommand);
		commands.add(fibonacciCommand);
		commands.add(exitCommand);

		commandControl = new CommandControl(commands);
	}

	@Rule
	public ExpectedSystemExit expectedEx = ExpectedSystemExit.none();

	@Test
	public void printMenuTest() {
		commandControl.printMenu();

		String expectedPrint = "* Enter \"s\" or \"S\" : to sum X & Y & ..., and print the result\n" +
				"* Enter \"m\" or \"M\" : to multiply X & Y & ..., and print the result\n" +
				"* Enter \"p\" or \"P\" : to find first N prime number, and print the result\n" +
				"* Enter \"f\" or \"F\" : to find first N \"Fibonacci Sequence\", and print the result\n" +
				"* Enter \"x\" or \"X\" : to quit!!\n" +
				"==========================================\n" +
				"Select menu : \n";

		assertEquals(expectedPrint, outContent.toString());
	}

	@Test
	public void executeCommandExitTest() {
		expectedEx.expectSystemExit();
		expectedEx.expectSystemExitWithStatus(0);

		final ByteArrayInputStream in = new ByteArrayInputStream("\n".getBytes());

		commandControl.executeCommand("x", in);

		String expectedPrint = "Killing the application !!\n" +
				"==========================================\n" +
				"Bye!!!\n" +
				"==========================================\n" +
				"Failed to execute : Tried to exit with status 0.\n" +
				"==========================================\n";

		assertEquals(expectedPrint, outContent.toString());
	}

	@Test
	public void executeCommandFibonacciTest() {
		final ByteArrayInputStream in = new ByteArrayInputStream("4".getBytes());
		commandControl.executeCommand("f", in);

		String expectedPrint = "Please enter a number (example : 4) : \n" +
				"==========================================\n" +
				"Result of first \"[4]\" Fibonacci Sequence : [0, 1, 1, 2]\n" +
				"==========================================\n";

		assertEquals(expectedPrint, outContent.toString());
	}

	@Test
	public void executeCommandMultiplyTest() {
		final ByteArrayInputStream in = new ByteArrayInputStream("2,3".getBytes());
		commandControl.executeCommand("m", in);

		String expectedPrint = "Please enter list of numbers separated by \",\" (example : 1, 2, 3) : \n" +
				"==========================================\n" +
				"Result of multiply \"2 * 3\" : [6]\n" +
				"==========================================\n";

		assertEquals(expectedPrint, outContent.toString());
	}

	@Test
	public void executeCommandPrimeNumberTest() {
		final ByteArrayInputStream in = new ByteArrayInputStream("4".getBytes());
		commandControl.executeCommand("p", in);

		String expectedPrint = "Please enter a number (example : 4) : \n" +
				"==========================================\n" +
				"Result of first \"[4]\" prime number : [2, 3, 5, 7]\n" +
				"==========================================\n";

		assertEquals(expectedPrint, outContent.toString());
	}

	@Test
	public void executeCommandSumTest() {
		final ByteArrayInputStream in = new ByteArrayInputStream("1,2".getBytes());
		commandControl.executeCommand("s", in);

		String expectedPrint = "Please enter list of numbers separated by \",\" (example : 1, 2, 3) : \n" +
				"==========================================\n" +
				"Result of sum \"1 + 2\" : [3]\n" +
				"==========================================\n";

		assertEquals(expectedPrint, outContent.toString());
	}
}

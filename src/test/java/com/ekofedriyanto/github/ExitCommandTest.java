package com.ekofedriyanto.github;

import com.ekofedriyanto.github.command.Command;
import com.ekofedriyanto.github.command.ExitCommand;
import org.junit.*;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ExitCommandTest {
	private Command exitCommand;
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
		exitCommand = new ExitCommand();
	}

	@Rule
	public ExpectedSystemExit expectedEx = ExpectedSystemExit.none();

	@Test
	public void expectedExitNullArgumentTest() {
		expectedEx.expectSystemExit();
		expectedEx.expectSystemExitWithStatus(0);
		exitCommand.execute(null);
	}

	@Test
	public void expectedExitEmptyStringArgumentTest() {
		expectedEx.expectSystemExit();
		expectedEx.expectSystemExitWithStatus(0);
		exitCommand.execute("");
	}

	@Test
	public void expectedExitAnyStringArgumentTest() {
		expectedEx.expectSystemExit();
		expectedEx.expectSystemExitWithStatus(0);
		exitCommand.execute("asdasda");
	}

	@Test
	public void canHandleTest() {
		assertFalse(exitCommand.canHandle("A"));
		assertFalse(exitCommand.canHandle("asdf"));
		assertTrue(exitCommand.canHandle("x"));
		assertTrue(exitCommand.canHandle("X"));
	}

	@Test
	public void printMenuTest() {
		exitCommand.printMenu();
		assertEquals("* Enter \"x\" or \"X\" : " + ExitCommand.menuString + "\n", outContent.toString());
	}

	@Test
	public void printConfirmationTest() {
		exitCommand.printConfirmation();
		assertEquals(ExitCommand.confirmationString + "\n", outContent.toString());
	}

	@Test
	public void calculateTest() {
		assertArrayEquals(new long[]{}, exitCommand.calculate(new int[]{}));
	}
}

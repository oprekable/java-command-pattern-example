package com.ekofedriyanto.github;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

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

	@Rule
	public ExpectedSystemExit expectedEx = ExpectedSystemExit.none();

	@Test
	public void mainTest() {
		expectedEx.expectSystemExit();
		expectedEx.expectSystemExitWithStatus(0);

		String expectedPrint = "* Enter \"s\" or \"S\" : to sum X & Y & ..., and print the result\n" +
				"* Enter \"m\" or \"M\" : to multiply X & Y & ..., and print the result\n" +
				"* Enter \"p\" or \"P\" : to find first N prime number, and print the result\n" +
				"* Enter \"f\" or \"F\" : to find first N \"Fibonacci Sequence\", and print the result\n" +
				"* Enter \"x\" or \"X\" : to quit!!\n" +
				"==========================================\n" +
				"Select menu : \n" +
				"Killing the application !!\n" +
				"==========================================\n" +
				"==========================================\n" +
				"Failed to execute : No line found\n" +
				"==========================================\n";

		ByteArrayInputStream in = new ByteArrayInputStream("x".getBytes());
		System.setIn(in);

		App.main(new String[]{});

		System.setIn(System.in);

		assertEquals(expectedPrint, outContent.toString());
	}
}

package com.ekofedriyanto.github;

import com.ekofedriyanto.github.command.Command;
import com.ekofedriyanto.github.command.PrimeNumberCommand;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class PrimeNumberCommandTest {
	private Command primeNumberCommand;
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
		primeNumberCommand = new PrimeNumberCommand();
	}

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void expectedExceptionNullArgumentTest() {
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("Invalid Data");
		primeNumberCommand.execute(null);
	}

	@Test
	public void expectedExceptionZeroArgumentTest() {
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("Invalid Data");
		primeNumberCommand.execute("");
	}

	@Test
	public void expectedExceptionNonNumberArgumentTest() {
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("Invalid Data");
		primeNumberCommand.execute("qwe");
	}

	@Test
	public void expectedExceptionMoreThanOneArgumentTest() {
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("Invalid Data");
		primeNumberCommand.execute("1, 2");
	}

	@Test
	public void isPrimeTest() {
		Assert.assertTrue(PrimeNumberCommand.isPrime(2) );
	}

	@Test
	public void commandTest() {
		long[] r1 = primeNumberCommand.calculate(new int[]{4});

		Assert.assertArrayEquals( new long[]{2, 3, 5, 7}, r1);

		long[] r2 = primeNumberCommand.calculate(new int[]{46});

		Assert.assertArrayEquals( new long[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199}, r2);
	}

	@Test
	public void execute1Test() {
		primeNumberCommand.execute("4");
		assertEquals("Result of first \"[4]\" prime number : [2, 3, 5, 7]\n", outContent.toString());
	}

	@Test
	public void execute2Test() {
		primeNumberCommand.execute("46");
		assertEquals("Result of first \"[46]\" prime number : [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199]\n", outContent.toString());
	}

	@Test
	public void canHandleTest() {
		assertFalse(primeNumberCommand.canHandle("A"));
		assertFalse(primeNumberCommand.canHandle("asdf"));
		assertTrue(primeNumberCommand.canHandle("p"));
		assertTrue(primeNumberCommand.canHandle("P"));
	}

	@Test
	public void printMenuTest() {
		primeNumberCommand.printMenu();
		assertEquals("* Enter \"p\" or \"P\" : " + PrimeNumberCommand.menuString + "\n", outContent.toString());
	}

	@Test
	public void printConfirmationTest() {
		primeNumberCommand.printConfirmation();
		assertEquals(PrimeNumberCommand.confirmationString + "\n", outContent.toString());
	}
}

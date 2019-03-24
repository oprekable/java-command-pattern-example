package com.ekofedriyanto.github;

import com.ekofedriyanto.github.command.Command;
import com.ekofedriyanto.github.command.FibonacciCommand;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class FibonacciCommandTest {
	private Command fibonacciCommand;
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
		fibonacciCommand = new FibonacciCommand();
	}

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void expectedExceptionNullArgumentTest() {
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("Invalid Data");
		fibonacciCommand.execute(null);
	}

	@Test
	public void expectedExceptionZeroArgumentTest() {
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("Invalid Data");
		fibonacciCommand.execute("");
	}

	@Test
	public void expectedExceptionNonNumberArgumentTest() {
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("Invalid Data");
		fibonacciCommand.execute("qwe");
	}

	@Test
	public void expectedExceptionMoreThanOneArgumentTest() {
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("Invalid Data");
		fibonacciCommand.execute("1, 2");
	}

	@Test
	public void commandTest() {
		long[] r1 = fibonacciCommand.calculate(new int[]{4});

		Assert.assertArrayEquals( new long[]{0, 1, 1, 2}, r1);

		long[] r2 = fibonacciCommand.calculate(new int[]{29});

		Assert.assertArrayEquals( new long[]{0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811}, r2);
	}

	@Test
	public void execute1Test() {
		fibonacciCommand.execute("4");
		assertEquals("Result of first \"[4]\" Fibonacci Sequence : [0, 1, 1, 2]\n", outContent.toString());
	}

	@Test
	public void execute2Test() {
		fibonacciCommand.execute("29");
		assertEquals("Result of first \"[29]\" Fibonacci Sequence : [0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811]\n", outContent.toString());
	}

	@Test
	public void canHandleTest() {
		assertFalse(fibonacciCommand.canHandle("A"));
		assertFalse(fibonacciCommand.canHandle("asdf"));
		assertTrue(fibonacciCommand.canHandle("f"));
		assertTrue(fibonacciCommand.canHandle("F"));
	}

	@Test
	public void printMenuTest() {
		fibonacciCommand.printMenu();
		assertEquals("* Enter \"f\" or \"F\" : " + FibonacciCommand.menuString + "\n", outContent.toString());
	}

	@Test
	public void printConfirmationTest() {
		fibonacciCommand.printConfirmation();
		assertEquals(FibonacciCommand.confirmationString + "\n", outContent.toString());
	}
}

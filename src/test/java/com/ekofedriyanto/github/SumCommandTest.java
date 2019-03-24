package com.ekofedriyanto.github;

import com.ekofedriyanto.github.command.Command;
import com.ekofedriyanto.github.command.SumCommand;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class SumCommandTest {

	private Command sumCommand;
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
		sumCommand = new SumCommand();
	}

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void expectedExceptionNullArgumentTest() {
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("Invalid Data");
		sumCommand.execute(null);
	}

	@Test
	public void expectedExceptionZeroArgumentTest() {
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("Invalid Data");
		sumCommand.execute("");
	}

	@Test
	public void expectedExceptionNonNumberArgumentTest() {
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("Invalid Data");
		sumCommand.execute("qwe");
	}

	@Test
	public void expectedExceptionOneArgumentTest() {
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("Invalid Data");
		sumCommand.execute("1");
	}

	@Test
	public void commandTest() {
		long[] r1 = sumCommand.calculate(new int[]{1, 2});
		Assert.assertArrayEquals( new long[]{3}, r1 );

		long[] r2 = sumCommand.calculate(new int[]{-1, -2});
		Assert.assertArrayEquals( new long[]{-3}, r2 );

		long[] r3 = sumCommand.calculate(new int[]{-1, -2, -3});
		Assert.assertArrayEquals( new long[]{-6}, r3 );

		long[] r4 = sumCommand.calculate(new int[]{-1, 2, 3});
		Assert.assertArrayEquals( new long[]{4}, r4 );
	}

	@Test
	public void execute1Test() {
		sumCommand.execute("1, 2");
		assertEquals("Result of sum \"1 + 2\" : [3]\n", outContent.toString());
	}

	@Test
	public void execute2Test() {
		sumCommand.execute("-1, -2");
		assertEquals("Result of sum \"-1 + -2\" : [-3]\n", outContent.toString());
	}

	@Test
	public void canHandleTest() {
		assertFalse(sumCommand.canHandle("A"));
		assertFalse(sumCommand.canHandle("asdf"));
		assertTrue(sumCommand.canHandle("s"));
		assertTrue(sumCommand.canHandle("S"));
	}

	@Test
	public void printMenuTest() {
		sumCommand.printMenu();
		assertEquals("* Enter \"s\" or \"S\" : " + SumCommand.menuString + "\n", outContent.toString());
	}

	@Test
	public void printConfirmationTest() {
		sumCommand.printConfirmation();
		assertEquals(SumCommand.confirmationString + "\n", outContent.toString());
	}
}

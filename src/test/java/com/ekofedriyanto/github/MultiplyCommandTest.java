package com.ekofedriyanto.github;

import com.ekofedriyanto.github.command.Command;
import com.ekofedriyanto.github.command.MultiplyCommand;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class MultiplyCommandTest {
	private Command multiplyCommand;
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
		multiplyCommand = new MultiplyCommand();
	}

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void expectedExceptionNullArgumentTest() {
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("Invalid Data");
		multiplyCommand.execute(null);
	}

	@Test
	public void expectedExceptionZeroArgumentTest() {
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("Invalid Data");
		multiplyCommand.execute("");
	}

	@Test
	public void expectedExceptionNonNumberArgumentTest() {
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("Invalid Data");
		multiplyCommand.execute("qwe");
	}

	@Test
	public void expectedExceptioOneArgumentTest() {
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("Invalid Data");
		multiplyCommand.execute("1");
	}

	@Test
	public void commandTest() {
		long[] r1 = multiplyCommand.calculate(new int[]{1, 2});
		Assert.assertArrayEquals( new long[]{2},  r1);

		long[] r2 = multiplyCommand.calculate(new int[]{1, 2, 3});
		Assert.assertArrayEquals( new long[]{6},  r2);

		long[] r3 = multiplyCommand.calculate(new int[]{-1, -2});
		Assert.assertArrayEquals( new long[]{2},  r3);

		long[] r4 = multiplyCommand.calculate(new int[]{-1, -2, -3});
		Assert.assertArrayEquals( new long[]{-6},  r4);

		long[] r5 = multiplyCommand.calculate(new int[]{-1, 2, 3});
		Assert.assertArrayEquals( new long[]{-6},  r5);
	}

	@Test
	public void execute1Test() {
		multiplyCommand.execute("1, 2");
		assertEquals("Result of multiply \"1 * 2\" : [2]\n", outContent.toString());
	}

	@Test
	public void execute2Test() {
		multiplyCommand.execute("1, 2, 3");
		assertEquals("Result of multiply \"1 * 2 * 3\" : [6]\n", outContent.toString());
	}

	@Test
	public void canHandleTest() {
		assertFalse(multiplyCommand.canHandle("A"));
		assertFalse(multiplyCommand.canHandle("asdf"));
		assertTrue(multiplyCommand.canHandle("m"));
		assertTrue(multiplyCommand.canHandle("M"));
	}

	@Test
	public void printMenuTest() {
		multiplyCommand.printMenu();
		assertEquals("* Enter \"m\" or \"M\" : " + MultiplyCommand.menuString + "\n", outContent.toString());
	}

	@Test
	public void printConfirmationTest() {
		multiplyCommand.printConfirmation();
		assertEquals(MultiplyCommand.confirmationString + "\n", outContent.toString());
	}
}

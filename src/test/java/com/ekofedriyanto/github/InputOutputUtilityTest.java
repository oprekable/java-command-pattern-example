package com.ekofedriyanto.github;

import com.ekofedriyanto.github.utility.InputOutputUtility;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

public class InputOutputUtilityTest {

	private String src;
	private String expectedString;
	private String delimiter;

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		src = "31231,asdasd,   f1231, ^**&^*,000";
		delimiter = ",";
		expectedString = "31231,asdasd,f1231,,000";
	}

	@Test
	public void constructorTest() {
		Assert.assertTrue(new InputOutputUtility() instanceof InputOutputUtility);
	}

	@Test
	public void trimStringNullSrcTest() {
		String result = InputOutputUtility.trimString(null, delimiter);
		Assert.assertEquals( "", result);
	}

	@Test
	public void trimStringEmptySrcTest() {
		String result = InputOutputUtility.trimString("", delimiter);
		Assert.assertEquals( "", result);
	}

	@Test
	public void trimStringNullDelimiterTest() {
		String result = InputOutputUtility.trimString(src, null);
		Assert.assertEquals( "31231asdasdf1231000", result);
	}

	@Test
	public void trimStringEmptyDelimiterTest() {
		String result = InputOutputUtility.trimString(src, "");
		Assert.assertEquals( "31231asdasdf1231000", result);
	}

	@Test
	public void trimStringTest() {
		String result = InputOutputUtility.trimString(src, delimiter);
		Assert.assertEquals( expectedString, result);
	}

	@Test
	public void stringToIntNullSrcTest() {
		expectedEx.expect(NumberFormatException.class);
		InputOutputUtility.stringToInt(null);
	}

	@Test
	public void stringToIntEmptySrcTest() {
		expectedEx.expect(NumberFormatException.class);
		InputOutputUtility.stringToInt(null);
	}

	@Test
	public void stringToIntNonNumberSrcTest() {
		expectedEx.expect(NumberFormatException.class);
		InputOutputUtility.stringToInt("aaa");
	}

	@Test
	public void stringToIntTest() {
		int result = InputOutputUtility.stringToInt("100");
		Assert.assertEquals( 100, result);
	}

	@Test
	public void stringToIntArrayNullSrcTest() {
		expectedEx.expect(NumberFormatException.class);
		int[] result = InputOutputUtility.stringToIntArray(null, delimiter);
	}

	@Test
	public void stringToIntArrayEmptySrcTest() {
		expectedEx.expect(NumberFormatException.class);
		int[] result = InputOutputUtility.stringToIntArray("", delimiter);
	}

	@Test
	public void stringToIntArrayAllNumberTest() {
		int[] result = InputOutputUtility.stringToIntArray("1,2,3", delimiter);
		Assert.assertArrayEquals( new int[]{1,2,3}, result);
	}

	@Test
	public void stringToIntArrayNotAllNumberTest() {
		expectedEx.expect(NumberFormatException.class);
		InputOutputUtility.stringToIntArray("1,ass,3", delimiter);
	}

	@Test
	public void stringToIntArrayNullDelimiterTest() {
		int[] result = InputOutputUtility.stringToIntArray("11234", null);
		Assert.assertArrayEquals( new int[]{1,1,2,3,4}, result);
	}




}

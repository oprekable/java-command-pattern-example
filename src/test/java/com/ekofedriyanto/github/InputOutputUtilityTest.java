package com.ekofedriyanto.github;

import com.ekofedriyanto.github.utility.InputOutputUtility;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InputOutputUtilityTest {

	private String src;
	private String expectedString;
	private String delimiter;

	@Before
	public void setUp() throws Exception {
		src = "31231,asdasd,   f1231, ^**&^*,000";
		delimiter = ",";
		expectedString = "31231,asdasd,f1231,,000";
	}

	@Test
	public void trimStringTest() {
		String result = InputOutputUtility.trimString(src, delimiter);
		Assert.assertEquals( expectedString, result);
	}
//
//	@Test
//	public void stringToIntTest() {
//		String result = InputOutputUtility.stringToInt(src, delimiter);
//		Assert.assertEquals( expectedString, result);
//	}
}

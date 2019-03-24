package com.ekofedriyanto.github;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertFalse( false );
    }

	@Test
	public void nameTest() {

		int[] xx = Stream.iterate(new int[]{0, 1}, i -> new int[]{i[1], i[0] + i[1]}).limit(4).mapToInt(s -> s[0]).toArray();

		System.out.println(Arrays.toString(xx));

		assertFalse( false );
	}
}

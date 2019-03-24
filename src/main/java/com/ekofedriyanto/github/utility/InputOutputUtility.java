package com.ekofedriyanto.github.utility;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class InputOutputUtility {

	public static String trimString(String src, String delimiter) {
		final String replaceRegex = (!Objects.isNull(delimiter)) ? "[^a-zA-Z0-9\\-" + delimiter + "]" : "[^a-zA-Z0-9]";
		return (!Objects.isNull(src)) ? src.replaceAll(replaceRegex, "") : "";
	}

	public static int stringToInt(String src) {
		src = (!Objects.isNull(src)) ? src : "";
		final String number = trimString(src,"");
		return Integer.parseInt(number);
	}

	public static int[] stringToIntArray(String src, String delimiter ) {
		src = (!Objects.isNull(src)) ? src : "";
		delimiter = (!Objects.isNull(delimiter)) ? delimiter : "";

		return Arrays.stream(trimString(src, delimiter).split(delimiter))
				.mapToInt(InputOutputUtility::stringToInt)
				.toArray();
	}

	public static String intArrayToString(int[] src, String delimiter ) {
		delimiter = (!Objects.isNull(delimiter)) ? delimiter : "";

		return  Arrays.stream(src)
				.mapToObj(String::valueOf)
				.collect(Collectors.joining(delimiter));
	}

}

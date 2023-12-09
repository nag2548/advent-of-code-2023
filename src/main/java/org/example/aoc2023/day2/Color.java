package org.example.aoc2023.day2;

import java.util.Arrays;

public enum Color
{
	RED("red", 12), BLUE("blue", 14), GREEN("green", 13);

	public final String value;
	public final int maxNumber;

	Color(String value, int maxNumber)
	{
		this.value = value;
		this.maxNumber = maxNumber;
	}

	public static Color fromValue(String value)
	{
		return Arrays.stream(Color.values())
			.filter(c -> c.value.equals(value))
			.findFirst()
			.orElse(null);
	}
}

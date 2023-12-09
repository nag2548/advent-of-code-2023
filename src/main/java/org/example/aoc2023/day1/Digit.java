package org.example.aoc2023.day1;

import java.util.Arrays;
import java.util.List;

public enum Digit
{
	ONE("one", "1"), //
	TWO("two", "2"), //
	THREE("three", "3"), //
	FOUR("four", "4"), //
	FIVE("five", "5"), //
	SIX("six", "6"), //
	SEVEN("seven", "7"), //
	EIGHT("eight", "8"), //
	NINE("nine", "9");

	private final String value;
	private final String number;

	Digit(String value, String number)
	{
		this.value = value;
		this.number = number;
	}

	public String getValue()
	{
		return value;
	}

	public String getNumber()
	{
		return number;
	}

	public static Digit fromValue(String value)
	{
		for (Digit d : Digit.values())
		{
			if (d.value.equals(value))
			{
				return d;
			}
		}
		return null;
	}

	public static List<String> getValues()
	{
		return Arrays.stream(Digit.values())
			.map(Digit::getValue)
			.toList();
	}
}

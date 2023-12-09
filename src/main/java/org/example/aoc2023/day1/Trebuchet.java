package org.example.aoc2023.day1;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Trebuchet
{
	private static final Pattern DIGIT_PATTERN = Pattern.compile("\\d");

	private final List<String> lines;

	public Trebuchet(List<String> input)
	{
		this.lines = input;
	}

	public int getSum()
	{
		int sum = 0;
		for (String line : lines)
		{
			List<String> digits = new ArrayList<>();

			Matcher matcher = DIGIT_PATTERN.matcher(line);
			while (matcher.find())
			{
				digits.add(matcher.group());
			}

			String lineValue = digits.get(0) + digits.get(digits.size() - 1);
			sum += Integer.parseInt(lineValue);
		}
		return sum;
	}
}

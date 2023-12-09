package org.example.aoc2023.day1;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Trebuchet2
{
	private static final Pattern DIGIT_PATTERN = Pattern.compile("(?=([0-9]|" + String.join("|", Digit.getValues()) + "))");

	private final List<String> lines;

	public Trebuchet2(List<String> input)
	{
		this.lines = input;
	}

	public int getSum()
	{
		List<Integer> sums = new ArrayList<>();

		for (String line : lines)
		{
			List<String> digits = new ArrayList<>();
			Matcher matcher = DIGIT_PATTERN.matcher(line);
			while (matcher.find())
			{
				String group = matcher.group(1);
				if (group.length() > 1)
				{
					group = requireNonNull(Digit.fromValue(group)).getNumber();
				}
				digits.add(group);
			}

			String lineValue = digits.get(0) + digits.get(digits.size() - 1);
			sums.add(Integer.valueOf(lineValue));
		}

		return sums.stream()
			.reduce(0, Integer::sum);
	}
}

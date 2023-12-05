package org.example.aoc2023.day4;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Scratchcards
{
	public int getPoints(List<String> input)
	{
		int points = 0;
		for (String line : input)
		{
			String trimmedLine = line.substring(line.indexOf(":") + 1);
			List<String> list = Arrays.stream(StringUtils.split(trimmedLine, '|'))
				.map(StringUtils::trim)
				.toList();
			List<String> cardNumbers = getNumbers(list.get(0));
			List<String> myNumbers = getNumbers(list.get(1));

			int count = 0;
			for (String myNumber : myNumbers)
			{
				if (cardNumbers.contains(myNumber))
				{
					count = count == 0 ? 1 : count * 2;
				}
			}
			points += count;
		}
		return points;
	}

	private List<String> getNumbers(String value)
	{
		return Arrays.stream(StringUtils.split(value, ' ')).map(StringUtils::trim).toList();
	}
}

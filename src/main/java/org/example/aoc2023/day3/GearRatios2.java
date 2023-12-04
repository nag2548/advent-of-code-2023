package org.example.aoc2023.day3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GearRatios2
{
	private static final Pattern IS_DIGIT_PATTERN = Pattern.compile("\\d+");

	private final List<Integer> numbers;

	public GearRatios2()
	{
		this.numbers = new ArrayList<>();
	}

	public int getSum(List<String> input)
	{
		for (int i = 0; i < input.size(); i++)
		{
			String line = input.get(i);
			for (int j = 0; j < line.length(); j++)
			{
				char c = line.charAt(j);
				boolean isSymbol = checkIsSymbol(c);
				if (isSymbol)
				{
					addAdjacentPositions(i, j, input);
				}
			}
		}
		return numbers.stream()
			.reduce(0, Integer::sum);
	}

	private boolean checkIsSymbol(char c)
	{
		return Objects.equals('*', c);
	}

	private void addAdjacentPositions(int i, int j, List<String> input)
	{
		Map<Position, String> tmp = new HashMap<>();
		if (i > 0)
		{
			String previousLine = input.get(i - 1);
			checkLine(i - 1, j, previousLine, tmp);
		}

		String currentLine = input.get(i);
		checkLine(i, j, currentLine, tmp);

		if (i < input.size() - 1)
		{
			String nextLine = input.get(i + 1);
			checkLine(i + 1, j, nextLine, tmp);
		}

		if (tmp.size() == 2)
		{
			numbers.add(tmp.values().stream().map(Integer::valueOf).reduce(1, (a, b) -> a * b));
		}
	}

	private void checkLine(int rowNumber, int symbolIndex, String line, Map<Position, String> tmp)
	{
		Matcher matcher = IS_DIGIT_PATTERN.matcher(line);
		while (matcher.find())
		{
			String matchedValue = matcher.group();
			int start = matcher.start();
			int end = matcher.end() - 1;
			if (start - 1 <= symbolIndex && end + 1 >= symbolIndex)
			{
				tmp.put(new Position(rowNumber, start), matchedValue);
			}
		}
	}
}

package org.example.aoc2023.day4;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

public class Scratchcards2
{
	public int getNumberOfCards(List<String> input)
	{
		int i = 0;
		Map<Integer, Integer> cardMap = IntStream.range(0, input.size())
			.boxed()
			.collect(Collectors.toMap(Function.identity(), n -> 1));

		for (String line : input)
		{
			String trimmedLine = line.substring(line.indexOf(":") + 1);
			List<String> list = Arrays.stream(StringUtils.split(trimmedLine, '|'))
				.map(StringUtils::trim)
				.toList();
			List<String> cardNumbers = getNumbers(list.get(0));
			List<String> myNumbers = getNumbers(list.get(1));

			Integer numberOfCards = cardMap.get(i);
			long matchedCount = myNumbers.stream()
				.filter(cardNumbers::contains)
				.count();
			int j = 1;
			while (matchedCount > 0 && i + j < input.size())
			{
				cardMap.compute(i + j, (k, v) -> v == null ? null : v + numberOfCards);
				matchedCount--;
				j++;
			}

			i++;
		}

		return cardMap.values().stream().reduce(0, Integer::sum);
	}

	private List<String> getNumbers(String value)
	{
		return Arrays.stream(StringUtils.split(value, ' ')).map(StringUtils::trim).toList();
	}
}

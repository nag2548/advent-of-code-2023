package org.example.aoc2023.day7;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class CardComparator2 implements Comparator<Bid>
{
	private static final String ORDER = "J23456789TJQKA";
	private static final char JOKER = 'J';

	private final Map<Bid, Map<Character, Integer>> occurrencesMap;

	public CardComparator2(Map<Bid, Map<Character, Integer>> occurrencesMap)
	{
		this.occurrencesMap = occurrencesMap;
	}

	@Override
	public int compare(Bid o1, Bid o2)
	{
		int score1 = getScore(o1);
		int score2 = getScore(o2);

		if (score1 == score2)
		{
			int compareValue = 0;
			for (int i = 0; i < 5; i++)
			{
				int int1 = ORDER.indexOf(o1.cards().charAt(i));
				int int2 = ORDER.indexOf(o2.cards().charAt(i));
				if (int1 != int2)
				{
					return Integer.compare(int1, int2);
				}
			}
			return compareValue;
		}

		return Integer.compare(score1, score2);
	}

	private int getScore(Bid bid)
	{
		Map<Character, Integer> map = occurrencesMap.get(bid);
		List<Integer> values = map.entrySet().stream()
			.filter(e -> e.getKey() != JOKER)
			.map(Map.Entry::getValue)
			.sorted(Comparator.reverseOrder())
			.toList();
		Integer numberOfJokers = map.getOrDefault(JOKER, 0);
		Integer highestNumberOfOccurrences = !values.isEmpty() ? values.get(0) : 0;

		if (highestNumberOfOccurrences + numberOfJokers >= 5)
		{
			return 7;
		}
		else if (highestNumberOfOccurrences + numberOfJokers >= 4)
		{
			return 6;
		}
		else if (highestNumberOfOccurrences + numberOfJokers >= 3)
		{
			int jokersLeft = highestNumberOfOccurrences + numberOfJokers - 3;
			if (values.get(1) + jokersLeft >= 2)
			{
				return 5;
			}
			return 4;
		}
		else if (highestNumberOfOccurrences + numberOfJokers >= 2)
		{
			int jokersLeft = highestNumberOfOccurrences + numberOfJokers - 2;
			if (values.get(1) + jokersLeft >= 2)
			{
				return 3;
			}
			return 2;
		}
		else if (map.keySet().size() == 5)
		{
			return 1;
		}
		return 0;
	}
}

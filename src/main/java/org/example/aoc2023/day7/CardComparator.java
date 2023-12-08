package org.example.aoc2023.day7;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;

public class CardComparator implements Comparator<Bid>
{
	private static final String ORDER = "23456789TJQKA";

	private final Map<Bid, Map<Character, Integer>> occurrencesMap;

	public CardComparator(Map<Bid, Map<Character, Integer>> occurrencesMap)
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
		Collection<Integer> values = map.values();

		if (values.contains(5))
		{
			return 7;
		}
		else if (values.contains(4))
		{
			return 6;
		}
		else if (values.contains(3))
		{
			if (values.contains(2))
			{
				return 5;
			}
			return 4;
		}
		else if (values.contains(2))
		{
			if (values.stream().filter(v -> v == 2).count() == 2)
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

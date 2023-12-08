package org.example.aoc2023.day7;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class CamelCards
{
	private List<Bid> bids;

	private Map<Bid, Map<Character, Integer>> occurrencesMap = new HashMap<>();

	public CamelCards(List<String> input)
	{
		initRows(input);
	}

	public long getWinnings()
	{
		for (Bid bid : bids)
		{
			setOccurrences(bid);
		}

		List<Bid> sortedBids = bids.stream()
			.sorted(new CardComparator(occurrencesMap))
			.toList();

		int i = 1;
		long sum = 0L;
		for (Bid sortedBid : sortedBids)
		{
			sum += sortedBid.amount() * i++;
		}
		return sum;
	}

	private void setOccurrences(Bid bid)
	{
		Map<Character, Integer> map = new HashMap<>();
		for (char c : bid.cards().toCharArray())
		{
			map.compute(c, (k, v) -> v == null ? 1 : Integer.sum(v, 1));
		}
		occurrencesMap.put(bid, map);
	}

	private void initRows(List<String> input)
	{
		bids = input.stream()
			.map(l -> {
				List<String> split = Arrays.asList(StringUtils.split(l, ' '));
				return new Bid(split.get(0), Long.parseLong(split.get(1)));
			})
			.toList();
	}
}

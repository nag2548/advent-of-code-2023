package org.example.aoc2023.day9;

import static java.util.Arrays.stream;
import static org.apache.commons.lang3.StringUtils.split;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MirageMaintenance2
{
	private final List<List<Integer>> lines;

	public MirageMaintenance2(List<String> input)
	{
		this.lines = input.stream()
			.map(i -> {
				List<Integer> l = stream(split(i, ' '))
					.map(Integer::valueOf)
					.collect(Collectors.toCollection(ArrayList::new));
				Collections.reverse(l);
				return l;
			})
			.toList();
	}

	public int getSum()
	{
		int sum = 0;
		for (List<Integer> line : lines)
		{
			sum += getNextNumber(line);
		}
		return sum;
	}

	private int getNextNumber(List<Integer> line)
	{
		List<List<Integer>> extrapolatedLines = getExtrapolatedLines(line);
		Collections.reverse(extrapolatedLines);
		return getNumberForLines(extrapolatedLines);
	}

	private int getNumberForLines(List<List<Integer>> extrapolatedLines)
	{
		int numberToAdd = 0;
		for (int i = 1; i < extrapolatedLines.size(); i++)
		{
			List<Integer> line = extrapolatedLines.get(i);
			int lastNumber = line.get(line.size() - 1);
			numberToAdd += lastNumber;
		}
		return numberToAdd;
	}

	private List<List<Integer>> getExtrapolatedLines(List<Integer> line)
	{
		List<List<Integer>> extrapolatedLines = new ArrayList<>();
		extrapolatedLines.add(new ArrayList<>(line));

		List<Integer> curr = line;
		while (curr.stream().anyMatch(i -> i != 0))
		{
			List<Integer> tmp = getDeltaLine(curr);
			extrapolatedLines.add(tmp);
			curr = tmp;
		}
		return extrapolatedLines;
	}

	private List<Integer> getDeltaLine(List<Integer> curr)
	{
		List<Integer> newLine = new ArrayList<>();
		int previous = curr.get(0);
		for (int i = 1; i < curr.size(); i++)
		{
			int j = curr.get(i);
			newLine.add(j - previous);
			previous = j;
		}
		return newLine;
	}
}

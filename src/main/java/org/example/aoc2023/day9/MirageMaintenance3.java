package org.example.aoc2023.day9;

import static java.util.Arrays.stream;
import static org.apache.commons.lang3.StringUtils.split;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MirageMaintenance3
{
	private final List<List<Integer>> lines;

	public MirageMaintenance3(List<String> input, boolean part2)
	{
		this.lines = input.stream()
			.map(i -> {
				List<Integer> l = stream(split(i, ' '))
					.map(Integer::valueOf)
					.collect(Collectors.toCollection(ArrayList::new));
				if (part2)
				{
					Collections.reverse(l);
				}
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
		if (line.stream().allMatch(i -> i == 0))
		{
			return 0;
		}

		List<Integer> newLine = new ArrayList<>();
		for (int i = 0; i < line.size() - 1; i++)
		{
			newLine.add(line.get(i + 1) - line.get(i));
		}

		return line.get(line.size() - 1) + getNextNumber(newLine);
	}
}

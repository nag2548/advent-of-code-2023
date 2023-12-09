package org.example.aoc2023.day2;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;
import static org.apache.commons.lang3.StringUtils.split;
import static org.apache.commons.lang3.StringUtils.splitByWholeSeparator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class CubeConundrum2
{
	private List<Game> games;

	public CubeConundrum2(List<String> input)
	{
		initLines(input);
	}

	public int getSum()
	{
		int sum = 0;
		for (Game game : games)
		{
			sum += getProduct(game);
		}
		return sum;
	}

	private int getProduct(Game game)
	{
		Map<Color, Integer> highestNumbers = new HashMap<>();
		List<Map<Color, Integer>> mapList = game.cubeMap();
		for (Map<Color, Integer> colorIntegerMap : mapList)
		{
			colorIntegerMap
				.forEach((key, value) -> highestNumbers.compute(key, (k, v) -> Integer.max(value, v != null ? v : 0)));
		}
		return highestNumbers.values().stream()
			.reduce(1, (a, b) -> a * b);
	}

	private void initLines(List<String> input)
	{
		this.games = input.stream()
			.map(this::buildGame)
			.toList();
	}

	private Game buildGame(String line)
	{
		String[] split = splitByWholeSeparator(line, ": ");
		int gameId = Integer.parseInt(StringUtils.split(split[0], ' ')[1]);
		List<Map<Color, Integer>> cubeMap = stream(splitByWholeSeparator(split[1], "; "))
			.map(s -> {
				String[] split1 = splitByWholeSeparator(s, ", ");
				return stream(split1)
					.map(s2 -> split(s2, ' '))
					.collect(toMap(kv -> Color.fromValue(kv[1]), kv -> Integer.valueOf(kv[0])));
			})
			.toList();

		return new Game(gameId, cubeMap);
	}
}

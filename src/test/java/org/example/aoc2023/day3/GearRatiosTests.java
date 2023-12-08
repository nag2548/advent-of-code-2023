package org.example.aoc2023.day3;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.jupiter.api.Test;

class GearRatiosTests
{
	@Test
	void sample() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day3/sample.txt")))))
		{
			List<String> input = reader.lines().toList();

			GearRatios app = new GearRatios();
			int sum = app.getSum(input);

			assertEquals(4361, sum);
		}
	}

	@Test
	void real() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day3/input.txt")))))
		{
			List<String> input = reader.lines().toList();

			GearRatios app = new GearRatios();
			int sum = app.getSum(input);

			assertEquals(546312, sum);
		}
	}
}

package org.example.aoc2023.day5;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.jupiter.api.Test;

class FertilizerTests
{
	@Test
	void sample() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day5/sample.txt")))))
		{
			List<String> input = reader.lines().toList();

			Fertilizer app = new Fertilizer(input);
			Long lowestLocation = app.getLowestLocation();

			assertEquals(35, lowestLocation);
		}
	}

	@Test
	void real() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day5/input.txt")))))
		{
			List<String> input = reader.lines().toList();

			Fertilizer app = new Fertilizer(input);
			Long lowestLocation = app.getLowestLocation();

			assertEquals(322500873, lowestLocation);
		}
	}
}

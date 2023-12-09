package org.example.aoc2023.day5;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.jupiter.api.Test;

class Fertilizer3Tests
{
	@Test
	void sample() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day5/sample.txt")))))
		{
			List<String> input = reader.lines().toList();

			Fertilizer3 app = new Fertilizer3(input);
			Long lowestLocation = app.getLowestLocation();

			assertEquals(46, lowestLocation);
		}
	}

	@Test
	void real() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day5/input.txt")))))
		{
			List<String> input = reader.lines().toList();

			Fertilizer3 app = new Fertilizer3(input);
			Long lowestLocation = app.getLowestLocation();

			assertEquals(108956227, lowestLocation);
		}
	}

	@Test
	void realLucas() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day5/input2.txt")))))
		{
			List<String> input = reader.lines().toList();

			Fertilizer3 app = new Fertilizer3(input);
			Long lowestLocation = app.getLowestLocation();

			assertEquals(20191102, lowestLocation);
		}
	}
}

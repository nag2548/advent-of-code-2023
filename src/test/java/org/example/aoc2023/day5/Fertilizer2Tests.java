package org.example.aoc2023.day5;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class Fertilizer2Tests
{
	@Test
	void sample() throws IOException, InterruptedException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day5/sample.txt")))))
		{
			List<String> input = reader.lines().toList();

			Fertilizer2 app = new Fertilizer2(input);
			Long lowestLocation = app.getLowestLocation();

			assertEquals(46, lowestLocation);
		}
	}

	@Test
	@Disabled
	void real() throws IOException, InterruptedException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day5/input.txt")))))
		{
			List<String> input = reader.lines().toList();

			Fertilizer2 app = new Fertilizer2(input);
			Long lowestLocation = app.getLowestLocation();

			assertEquals(108956227, lowestLocation);
		}
	}
}

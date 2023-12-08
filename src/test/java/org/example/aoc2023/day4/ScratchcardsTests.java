package org.example.aoc2023.day4;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.jupiter.api.Test;

class ScratchcardsTests
{
	@Test
	void sample() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day4/sample.txt")))))
		{
			List<String> input = reader.lines().toList();

			Scratchcards app = new Scratchcards();
			int sum = app.getPoints(input);

			assertEquals(13, sum);
		}
	}

	@Test
	void real() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day4/input.txt")))))
		{
			List<String> input = reader.lines().toList();

			Scratchcards app = new Scratchcards();
			int sum = app.getPoints(input);

			assertEquals(24733, sum);
		}
	}
}

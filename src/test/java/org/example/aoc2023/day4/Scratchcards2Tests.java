package org.example.aoc2023.day4;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.jupiter.api.Test;

class Scratchcards2Tests
{
	@Test
	void sample() throws URISyntaxException, IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day4/sample.txt")))))
		{
			List<String> input = reader.lines().toList();

			Scratchcards2 app = new Scratchcards2();
			int sum = app.getNumberOfCards(input);

			assertEquals(30, sum);
		}
	}

	@Test
	void real() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day4/input.txt")))))
		{
			List<String> input = reader.lines().toList();

			Scratchcards2 app = new Scratchcards2();
			int sum = app.getNumberOfCards(input);

			assertEquals(5422730, sum);
		}
	}
}

package org.example.aoc2023.day7;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.jupiter.api.Test;

class CamelCardsTests
{
	@Test
	void sample() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day7/sample.txt")))))
		{
			List<String> input = reader.lines().toList();

			CamelCards app = new CamelCards(input);
			long winnings = app.getWinnings();

			assertEquals(6440L, winnings);
		}
	}

	@Test
	void real() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day7/input.txt")))))
		{
			List<String> input = reader.lines().toList();

			CamelCards app = new CamelCards(input);
			long winnings = app.getWinnings();

			assertEquals(249390788L, winnings);
		}
	}
}

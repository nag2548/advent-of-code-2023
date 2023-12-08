package org.example.aoc2023.day7;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.jupiter.api.Test;

class CamelCards2Tests
{
	@Test
	void sample() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day7/sample2.txt")))))
		{
			List<String> input = reader.lines().toList();

			CamelCards2 app = new CamelCards2(input);
			long winnings = app.getWinnings();

			assertEquals(5905L, winnings);
		}
	}

	@Test
	void real() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day7/input2.txt")))))
		{
			List<String> input = reader.lines().toList();

			CamelCards2 app = new CamelCards2(input);
			long winnings = app.getWinnings();

			assertEquals(248750248L, winnings);
		}
	}
}

package org.example.aoc2023.day1;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.jupiter.api.Test;

class Trebuchet2Tests
{
	@Test
	void sample() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day1/sample2.txt")))))
		{
			List<String> input = reader.lines().toList();

			Trebuchet2 app = new Trebuchet2(input);
			int sum = app.getSum();

			assertEquals(281, sum);
		}
	}

	@Test
	void real() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day1/input.txt")))))
		{
			List<String> input = reader.lines().toList();

			Trebuchet2 app = new Trebuchet2(input);
			int sum = app.getSum();

			assertEquals(54925, sum);
		}
	}
}

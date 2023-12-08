package org.example.aoc2023.day8;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.jupiter.api.Test;

class HauntedWasteland2Tests
{
	@Test
	void sample() throws IOException, InterruptedException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day8/sample2.txt")))))
		{
			List<String> input = reader.lines().toList();

			HauntedWasteland2 app = new HauntedWasteland2(input);
			long count = app.getCount();

			assertEquals(6L, count);
		}
	}

	@Test
	void real() throws IOException, InterruptedException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day8/input.txt")))))
		{
			List<String> input = reader.lines().toList();

			HauntedWasteland2 app = new HauntedWasteland2(input);
			long count = app.getCount();

			assertEquals(19185263738117L, count);
		}
	}
}

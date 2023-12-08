package org.example.aoc2023.day8;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.jupiter.api.Test;

class HauntedWastelandTests
{
	@Test
	void sample() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day8/sample.txt")))))
		{
			List<String> input = reader.lines().toList();

			HauntedWasteland app = new HauntedWasteland(input);
			int count = app.getCount();

			assertEquals(6, count);
		}
	}

	@Test
	void real() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day8/input.txt")))))
		{
			List<String> input = reader.lines().toList();

			HauntedWasteland app = new HauntedWasteland(input);
			int count = app.getCount();

			assertEquals(6, count);
		}
	}
}

package org.example.aoc2023.day3;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.jupiter.api.Test;

class GearRatios2Tests
{
	@Test
	void sample() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day3/sample.txt")))))
		{
			List<String> input = reader.lines().toList();

			GearRatios2 app = new GearRatios2();
			int sum = app.getSum(input);

			assertEquals(467835, sum);
		}
	}

	@Test
	void real() throws URISyntaxException, IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day3/input.txt")))))
		{
			List<String> input = reader.lines().toList();

			GearRatios2 app = new GearRatios2();
			int sum = app.getSum(input);

			assertEquals(87449461, sum);
		}
	}
}

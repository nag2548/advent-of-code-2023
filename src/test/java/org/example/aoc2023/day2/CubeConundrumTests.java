package org.example.aoc2023.day2;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.jupiter.api.Test;

class CubeConundrumTests
{
	@Test
	void sample() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day2/sample.txt")))))
		{
			List<String> input = reader.lines().toList();

			CubeConundrum app = new CubeConundrum(input);
			int sum = app.getSum();

			assertEquals(8, sum);
		}
	}

	@Test
	void real() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day2/input.txt")))))
		{
			List<String> input = reader.lines().toList();

			CubeConundrum app = new CubeConundrum(input);
			int sum = app.getSum();

			assertEquals(2879, sum);
		}
	}
}

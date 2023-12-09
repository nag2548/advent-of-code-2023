package org.example.aoc2023.day2;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.jupiter.api.Test;

class CubeConundrum2Tests
{
	@Test
	void sample() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day2/sample.txt")))))
		{
			List<String> input = reader.lines().toList();

			CubeConundrum2 app = new CubeConundrum2(input);
			int sum = app.getSum();

			assertEquals(2286, sum);
		}
	}

	@Test
	void real() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day2/input.txt")))))
		{
			List<String> input = reader.lines().toList();

			CubeConundrum2 app = new CubeConundrum2(input);
			int sum = app.getSum();

			assertEquals(65122, sum);
		}
	}
}

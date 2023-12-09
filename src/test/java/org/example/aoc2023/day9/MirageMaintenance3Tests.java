package org.example.aoc2023.day9;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.jupiter.api.Test;

class MirageMaintenance3Tests
{
	@Test
	void samplePart1() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day9/sample.txt")))))
		{
			List<String> input = reader.lines().toList();

			MirageMaintenance3 app = new MirageMaintenance3(input, false);
			int sum = app.getSum();

			assertEquals(114, sum);
		}
	}

	@Test
	void realPart1() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day9/input.txt")))))
		{
			List<String> input = reader.lines().toList();

			MirageMaintenance3 app = new MirageMaintenance3(input, false);
			int sum = app.getSum();

			assertEquals(1789635132, sum);
		}
	}

	@Test
	void samplePart2() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day9/sample.txt")))))
		{
			List<String> input = reader.lines().toList();

			MirageMaintenance3 app = new MirageMaintenance3(input, true);
			int sum = app.getSum();

			assertEquals(2, sum);
		}
	}

	@Test
	void realPart2() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day9/input.txt")))))
		{
			List<String> input = reader.lines().toList();

			MirageMaintenance3 app = new MirageMaintenance3(input, true);
			int sum = app.getSum();

			assertEquals(913, sum);
		}
	}
}

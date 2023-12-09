package org.example.aoc2023.day9;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.jupiter.api.Test;

class MirageMaintenance2Tests
{
	@Test
	void sample() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day9/sample.txt")))))
		{
			List<String> input = reader.lines().toList();

			MirageMaintenance2 app = new MirageMaintenance2(input);
			int sum = app.getSum();

			assertEquals(2, sum);
		}
	}

	@Test
	void real() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day9/input.txt")))))
		{
			List<String> input = reader.lines().toList();

			MirageMaintenance2 app = new MirageMaintenance2(input);
			int sum = app.getSum();

			assertEquals(913, sum);
		}
	}
}

package org.example.aoc2023.day10;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.jupiter.api.Test;

class PipeMaze2Tests
{
	@Test
	void sample() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day10/sample2.txt")))))
		{
			List<String> input = reader.lines().toList();

			PipeMaze2 app = new PipeMaze2(input);
			int steps = app.getNumberOfEnclosedFields();

			assertEquals(4, steps);
		}
	}

	@Test
	void sample2() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day10/sample3.txt")))))
		{
			List<String> input = reader.lines().toList();

			PipeMaze2 app = new PipeMaze2(input);
			int steps = app.getNumberOfEnclosedFields();

			assertEquals(8, steps);
		}
	}

	@Test
	void sample3() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day10/sample4.txt")))))
		{
			List<String> input = reader.lines().toList();

			PipeMaze2 app = new PipeMaze2(input);
			int steps = app.getNumberOfEnclosedFields();

			assertEquals(10, steps);
		}
	}

	@Test
	void real() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day10/input.txt")))))
		{
			List<String> input = reader.lines().toList();

			PipeMaze2 app = new PipeMaze2(input);
			int steps = app.getNumberOfEnclosedFields();

			assertEquals(325, steps);
		}
	}
}

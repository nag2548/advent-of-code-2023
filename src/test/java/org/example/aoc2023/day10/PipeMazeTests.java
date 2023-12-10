package org.example.aoc2023.day10;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.jupiter.api.Test;

class PipeMazeTests
{
	@Test
	void sample() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day10/sample.txt")))))
		{
			List<String> input = reader.lines().toList();

			PipeMaze app = new PipeMaze(input);
			int steps = app.getHighestNumberOfSteps();

			assertEquals(8, steps);
		}
	}

	@Test
	void real() throws IOException
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(requireNonNull(getClass().getClassLoader().getResourceAsStream("day10/input.txt")))))
		{
			List<String> input = reader.lines().toList();

			PipeMaze app = new PipeMaze(input);
			int steps = app.getHighestNumberOfSteps();

			assertEquals(6846, steps);
		}
	}
}

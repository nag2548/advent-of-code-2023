package org.example.aoc2023.day3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

class GearRatiosTests
{
	@Test
	void sample() throws URISyntaxException, IOException
	{
		List<String> input = Files.lines(Paths.get(getClass().getClassLoader().getResource("day3/sample.txt").toURI()), StandardCharsets.UTF_8).toList();

		GearRatios app = new GearRatios();
		int sum = app.getSum(input);

		assertEquals(4361, sum);
	}

	@Test
	void real() throws URISyntaxException, IOException
	{
		List<String> input = Files.lines(Paths.get(getClass().getClassLoader().getResource("day3/input.txt").toURI()), StandardCharsets.UTF_8).toList();

		GearRatios app = new GearRatios();
		int sum = app.getSum(input);

		assertEquals(546312, sum);
	}
}

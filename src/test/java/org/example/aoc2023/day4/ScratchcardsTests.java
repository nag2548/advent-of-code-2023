package org.example.aoc2023.day4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class ScratchcardsTests
{
	@Test
	void sample() throws URISyntaxException, IOException
	{
		try (Stream<String> lines = Files.lines(Paths.get(getClass().getClassLoader().getResource("day4/sample.txt").toURI()), StandardCharsets.UTF_8))
		{
			List<String> input = lines.toList();

			Scratchcards app = new Scratchcards();
			int sum = app.getPoints(input);

			assertEquals(13, sum);
		}
	}

	@Test
	void real() throws URISyntaxException, IOException
	{
		try (Stream<String> lines = Files.lines(Paths.get(getClass().getClassLoader().getResource("day4/input.txt").toURI()), StandardCharsets.UTF_8))
		{
			List<String> input = lines.toList();

			Scratchcards app = new Scratchcards();
			int sum = app.getPoints(input);

			assertEquals(13, sum);
		}
	}
}

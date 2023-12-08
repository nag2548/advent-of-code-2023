package org.example.aoc2023.day5;

import static java.lang.Thread.MAX_PRIORITY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.StringUtils;

public class Fertilizer2
{
	private static final String SEED_TO_SOIL = "seed-to-soil map:";
	private static final String SOIL_TO_FERTILIZER = "soil-to-fertilizer map:";
	private static final String FERTILIZER_TO_WATER = "fertilizer-to-water map:";
	private static final String WATER_TO_LIGHT = "water-to-light map:";
	private static final String LIGHT_TO_TEMPERATURE = "light-to-temperature map:";
	private static final String TEMPERATURE_TO_HUMIDITY = "temperature-to-humidity map:";
	private static final String HUMIDITY_TO_LOCATION = "humidity-to-location map:";

	private static final List<String> HEADER_NAMES = List.of(
		SEED_TO_SOIL,
		SOIL_TO_FERTILIZER,
		FERTILIZER_TO_WATER,
		WATER_TO_LIGHT,
		LIGHT_TO_TEMPERATURE,
		TEMPERATURE_TO_HUMIDITY,
		HUMIDITY_TO_LOCATION);

	private final List<SeedRange> seeds = new ArrayList<>();
	private final Map<String, List<SourceTargetMapping>> mappingsMap = new HashMap<>();

	private final ExecutorService executorService = Executors.newCachedThreadPool();

	private Long lowestNumber = null;
	private AtomicLong counter = new AtomicLong(0);

	public Fertilizer2(List<String> input)
	{
		initMap(input);
	}

	public Long getLowestLocation() throws InterruptedException
	{
		for (SeedRange seedRange : seeds)
		{
			System.out.println("seedRange: " + seedRange.start());

			for (long i = seedRange.start(); i < seedRange.start() + seedRange.range(); i++)
			{
				Long currentSeed = i;
				executorService.submit(() -> {
					compute(currentSeed);
					long processedSeeds = counter.incrementAndGet();

					if (processedSeeds % 10000 == 0)
					{
						System.out.println(processedSeeds + " seeds processed");
					}
				});
			}

			System.out.println("seedRange done: " + seedRange.start());
		}
		executorService.shutdown();
		executorService.awaitTermination(MAX_PRIORITY, TimeUnit.HOURS);

		return lowestNumber;
	}

	private void compute(Long seed)
	{
		Long newLocation = getLowestLocationSingle(seed);
		synchronized (this)
		{
			if (lowestNumber == null || newLocation < lowestNumber)
			{
				lowestNumber = newLocation;
			}
		}
	}

	private Long getLowestLocationSingle(Long seed)
	{
		Long returnValue = seed;
		for (String headerName : HEADER_NAMES)
		{
			List<SourceTargetMapping> nextMapping = mappingsMap.get(headerName);

			Long tmp = returnValue;
			returnValue = nextMapping.stream()
				.filter(m -> m.source() <= tmp && m.source() + m.range() > tmp)
				.findFirst()
				.map(m -> m.target() + tmp - m.source())
				.orElse(tmp);
		}
		return returnValue;
	}

	private void initMap(List<String> input)
	{
		String currentMap = null;
		for (int i = 0; i < input.size(); i++)
		{
			if (i == 0)
			{
				List<Long> longs = Arrays.stream(StringUtils.split(input.get(i).replace("seeds: ", ""), " "))
					.map(Long::parseLong)
					.toList();
				for (int j = 0; j < longs.size(); j += 2)
				{
					seeds.add(new SeedRange(longs.get(j), longs.get(j + 1)));
				}
				continue;
			}

			String line = input.get(i);
			if (HEADER_NAMES.contains(line))
			{
				currentMap = line;
			}
			else if (StringUtils.isNotBlank(line))
			{
				List<Long> split = Arrays.stream(StringUtils.split(line, " "))
					.map(Long::parseLong)
					.toList();
				mappingsMap.computeIfAbsent(currentMap, k -> new ArrayList<>())
					.add(new SourceTargetMapping(split.get(0), split.get(1), split.get(2)));
			}
		}
	}
}

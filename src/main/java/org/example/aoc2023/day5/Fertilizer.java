package org.example.aoc2023.day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class Fertilizer
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

	private List<Long> seeds;
	private Map<String, List<SourceTargetMapping>> mappingsMap = new HashMap<>();

	public Fertilizer(List<String> input)
	{
		initMap(input);
	}

	public Long getLowestLocation()
	{
		Long lowestNumber = null;
		for (Long seed : seeds)
		{
			Long newLocation = getLowestLocationSingle(seed);
			if (lowestNumber == null || newLocation < lowestNumber)
			{
				lowestNumber = newLocation;
			}
		}
		return lowestNumber;
	}

	private Long getLowestLocationSingle(Long seed)
	{
		Long returnValue = seed;
		for (String headerName : HEADER_NAMES)
		{
			List<SourceTargetMapping> nextMapping = mappingsMap.get(headerName);

			Long tmp = returnValue;
			returnValue = nextMapping.stream()
				.filter(m -> m.source() <= tmp && m.source() + m.range() >= tmp)
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
				seeds = Arrays.stream(StringUtils.split(input.get(i).replace("seeds: ", ""), " "))
					.map(Long::parseLong)
					.toList();
				continue;
			}

			String line = input.get(i);
			if (HEADER_NAMES.contains(line))
			{
				currentMap = line;
				continue;
			}

			if (StringUtils.isNotBlank(line))
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

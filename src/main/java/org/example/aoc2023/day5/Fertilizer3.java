package org.example.aoc2023.day5;

import static java.util.Arrays.stream;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.split;
import static org.example.aoc2023.day5.RangeType.FERTILIZER_TO_WATER;
import static org.example.aoc2023.day5.RangeType.HUMIDITY_TO_LOCATION;
import static org.example.aoc2023.day5.RangeType.LIGHT_TO_TEMPERATURE;
import static org.example.aoc2023.day5.RangeType.SEED_TO_SOIL;
import static org.example.aoc2023.day5.RangeType.SOIL_TO_FERTILIZER;
import static org.example.aoc2023.day5.RangeType.START;
import static org.example.aoc2023.day5.RangeType.TEMPERATURE_TO_HUMIDITY;
import static org.example.aoc2023.day5.RangeType.WATER_TO_LIGHT;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Fertilizer3
{
	private static final List<RangeType> HEADER_NAMES = List.of(
		START,
		SEED_TO_SOIL,
		SOIL_TO_FERTILIZER,
		FERTILIZER_TO_WATER,
		WATER_TO_LIGHT,
		LIGHT_TO_TEMPERATURE,
		TEMPERATURE_TO_HUMIDITY,
		HUMIDITY_TO_LOCATION);

	private final List<SeedRange2> seeds = new ArrayList<>();
	private final Map<String, List<SourceTargetMapping2>> mappingsMap = new HashMap<>();

	public Fertilizer3(List<String> input)
	{
		initMap(input);
	}

	public Long getLowestLocation()
	{
		List<SeedRange2> locationRanges = seeds.stream()
			.map(this::computeRangeForSeeds)
			.flatMap(Collection::stream)
			.toList();

		return locationRanges.stream()
			.mapToLong(SeedRange2::start)
			.min()
			.orElseThrow(IllegalStateException::new);
	}

	private List<SeedRange2> computeRangeForSeeds(SeedRange2 starter)
	{
		List<SeedRange2> seedRanges = new ArrayList<>();
		seedRanges.add(starter);

		int index = HEADER_NAMES.indexOf(starter.rangeType());
		while (index + 1 < HEADER_NAMES.size())
		{
			RangeType nextRangeType = HEADER_NAMES.get(index + 1);
			List<SourceTargetMapping2> nextMappings = mappingsMap.get(nextRangeType.heading);

			seedRanges = seedRanges.stream()
				.map(seedRange -> getRanges(seedRange, nextMappings, nextRangeType))
				.flatMap(Collection::stream)
				.toList();

			index++;
		}

		return seedRanges;
	}

	private List<SeedRange2> getRanges(SeedRange2 seedRange, List<SourceTargetMapping2> nextMappings, RangeType nextRangeType)
	{
		List<SeedRange2> newRanges = new ArrayList<>();
		long start = seedRange.start();

		while (start < seedRange.end())
		{
			long currStart = start;

			Optional<SourceTargetMapping2> mappingWithinRangeOptional = nextMappings.stream()
				.filter(m -> m.sourceStart() <= currStart && m.sourceEnd() >= currStart)
				.findFirst();
			if (mappingWithinRangeOptional.isPresent())
			{
				SourceTargetMapping2 mappingWithinRange = mappingWithinRangeOptional.get();
				long mappingOffset = mappingWithinRange.offset();
				long nStart = start + mappingOffset;
				long to = Math.min(mappingWithinRange.sourceEnd(), seedRange.end());
				long nEnd = to + mappingOffset;
				SeedRange2 nRange = new SeedRange2(nStart, nEnd, nextRangeType);
				newRanges.add(nRange);
				start = to + 1;
			}
			else
			{
				Optional<SourceTargetMapping2> mappingOutsideOfRangeOptional = nextMappings.stream()
					.filter(m -> m.sourceStart() > currStart && m.sourceStart() <= seedRange.end())
					.min(Comparator.comparingLong(i -> Math.abs(i.sourceStart() - currStart)));

				if (mappingOutsideOfRangeOptional.isPresent())
				{
					SourceTargetMapping2 mappingOutsideOfRange = mappingOutsideOfRangeOptional.get();
					long nStart = start;
					long to = mappingOutsideOfRange.sourceStart() - 1;
					SeedRange2 nRange = new SeedRange2(nStart, to, nextRangeType);
					newRanges.add(nRange);
					start = to + 1;
				}
				else
				{
					long nStart = start;
					long to = seedRange.end();
					SeedRange2 nRange = new SeedRange2(nStart, to, nextRangeType);
					newRanges.add(nRange);
					start = to + 1;
				}
			}
		}

		return newRanges;
	}

	private void initMap(List<String> input)
	{
		String currentMap = null;
		for (int i = 0; i < input.size(); i++)
		{
			if (i == 0)
			{
				List<Long> longs = stream(split(input.get(i).replace("seeds: ", ""), " "))
					.map(Long::parseLong)
					.toList();
				for (int j = 0; j < longs.size(); j += 2)
				{
					long start = longs.get(j);
					long range = longs.get(j + 1);
					seeds.add(new SeedRange2(start, start + range - 1, START));
				}
				continue;
			}

			String line = input.get(i);
			if (HEADER_NAMES.stream().anyMatch(h -> h.heading.equals(line)))
			{
				currentMap = line;
			}
			else if (isNotBlank(line))
			{
				List<Long> split = stream(split(line, ' '))
					.map(Long::parseLong)
					.toList();
				long targetStart = split.get(0);
				long sourceStart = split.get(1);
				long range = split.get(2);
				mappingsMap.computeIfAbsent(currentMap, k -> new ArrayList<>())
					.add(new SourceTargetMapping2(targetStart, targetStart + range - 1, sourceStart, sourceStart + range - 1, range, targetStart - sourceStart));
			}
		}
	}
}

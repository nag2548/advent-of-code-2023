package org.example.aoc2023.day5;

public enum RangeType
{
	START("start"), //
	SEED_TO_SOIL("seed-to-soil map:"), //
	SOIL_TO_FERTILIZER("soil-to-fertilizer map:"), //
	FERTILIZER_TO_WATER("fertilizer-to-water map:"), //
	WATER_TO_LIGHT("water-to-light map:"), //
	LIGHT_TO_TEMPERATURE("light-to-temperature map:"), //
	TEMPERATURE_TO_HUMIDITY("temperature-to-humidity map:"), //
	HUMIDITY_TO_LOCATION("humidity-to-location map:");

	public final String heading;

	RangeType(String heading)
	{
		this.heading = heading;
	}
}

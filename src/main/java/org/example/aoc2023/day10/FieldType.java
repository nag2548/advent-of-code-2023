package org.example.aoc2023.day10;

import java.util.Arrays;
import java.util.Objects;

public enum FieldType
{
	VERTICAL_PIPE('|'), //
	HORIZONTAL_PIPE('-'), //
	L('L'), //
	J('J'), //
	SEVEN('7'), //
	F('F'), //
	START('S'), //
	GROUND('.');

	public final Character value;

	FieldType(Character value)
	{
		this.value = value;
	}

	public static FieldType fromValue(Character val)
	{
		return Arrays.stream(FieldType.values())
			.filter(v -> Objects.equals(v.value, val))
			.findFirst()
			.orElseThrow(IllegalArgumentException::new);
	}
}

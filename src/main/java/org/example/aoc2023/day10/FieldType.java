package org.example.aoc2023.day10;

import java.util.Arrays;
import java.util.Objects;

public enum FieldType
{
	VERTICAL_PIPE('|', true, true, false, false), //
	HORIZONTAL_PIPE('-', false, false, true, true), //
	L('L', true, false, false, true), //
	J('J', true, false, true, false), //
	SEVEN('7', false, true, true, false), //
	F('F', false, true, false, true), //
	START('S', true, true, true, true), //
	GROUND('.', false, false, false, false);

	public final Character value;
	public final boolean up;
	public final boolean down;
	public final boolean left;
	public final boolean right;

	FieldType(Character value, boolean up, boolean down, boolean left, boolean right)
	{
		this.value = value;
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
	}

	public static FieldType fromValue(Character val)
	{
		return Arrays.stream(FieldType.values())
			.filter(v -> Objects.equals(v.value, val))
			.findFirst()
			.orElseThrow(IllegalArgumentException::new);
	}
}

package org.example.aoc2023.day10;

public class Path
{
	private final Field field;
	private final Path parent;

	public Path(Field field, Path parent)
	{
		this.field = field;
		this.parent = parent;
	}

	public Field getField()
	{
		return field;
	}

	public Path getParent()
	{
		return parent;
	}

	public int getSteps()
	{
		if (parent == null)
		{
			return 0;
		}

		return 1 + parent.getSteps();
	}
}

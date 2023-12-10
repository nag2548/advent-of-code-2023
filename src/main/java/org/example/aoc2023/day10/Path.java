package org.example.aoc2023.day10;

import java.util.ArrayList;
import java.util.List;

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

	public List<Field> getFields()
	{
		List<Field> fields = new ArrayList<>();
		Path start = this;
		while (start.getParent() != null)
		{
			fields.add(start.getField());
			start = start.getParent();
		}
		return fields;
	}
}

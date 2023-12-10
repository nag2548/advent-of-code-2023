package org.example.aoc2023.day10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PipeMaze
{
	private final List<List<Field>> maze;

	private final List<Path> completedPaths = new ArrayList<>();

	public PipeMaze(List<String> input)
	{
		this.maze = initMaze(input);
	}

	public int getHighestNumberOfSteps()
	{
		Field start = findStart();
		Path startPath = new Path(start, null);

		List<Path> paths = List.of(startPath);
		do
		{
			paths = paths.stream()
				.map(this::addChildren)
				.flatMap(Collection::stream)
				.toList();
		} while (paths.stream().noneMatch(c -> c.getField().type() == FieldType.START));

		return completedPaths.stream()
			.mapToInt(p -> (int)Math.ceil(p.getSteps() / 2.0))
			.min()
			.orElseThrow();
	}

	private List<Path> addChildren(Path parent)
	{
		List<Field> adjacentFields = findAdjacentFields(parent.getField(), parent.getParent() != null ? parent.getParent().getField() : null);
		List<Path> childPaths = adjacentFields.stream()
			.map(f -> new Path(f, parent))
			.toList();

		if (parent.getParent() == null)
		{
			childPaths = List.of(childPaths.get(0));
		}

		if (childPaths.stream().anyMatch(c -> c.getField().type() == FieldType.START))
		{
			completedPaths.addAll(childPaths.stream().filter(c -> c.getField().type() == FieldType.START).toList());
		}

		return childPaths;
	}

	private List<Field> findAdjacentFields(Field start, Field previousField)
	{
		Field top = maze.get(Math.max(start.coordinates().y() - 1, 0)).get(start.coordinates().x());
		Field left = maze.get(start.coordinates().y()).get(Math.max(start.coordinates().x() - 1, 0));
		Field right = maze.get(start.coordinates().y()).get(Math.min(start.coordinates().x() + 1, maze.get(start.coordinates().y()).size() - 1));
		Field bottom = maze.get(Math.min(start.coordinates().y() + 1, maze.size() - 1)).get(start.coordinates().x());
		List<Field> fields = List.of(top, left, right, bottom);

		return fields.stream()
			.filter(o -> previousField == null || o.coordinates().x() != previousField.coordinates().x() || o.coordinates().y() != previousField.coordinates().y())
			.filter(o -> o.coordinates().x() != start.coordinates().x() || o.coordinates().y() != start.coordinates().y())
			.filter(o -> connects(start, o))
			.toList();
	}

	private boolean connects(Field a, Field b)
	{
		boolean isStart = a.type() == FieldType.START;
		boolean fromAbove = b.coordinates().y() > a.coordinates().y();
		boolean fromBelow = a.coordinates().y() > b.coordinates().y();
		boolean fromLeft = b.coordinates().x() > a.coordinates().x();
		boolean fromRight = a.coordinates().x() > b.coordinates().x();

		return switch (b.type())
		{
			case VERTICAL_PIPE ->
			{
				yield isStart && a.coordinates().x() == b.coordinates().x()
					|| a.type() == FieldType.VERTICAL_PIPE && a.coordinates().x() == b.coordinates().x()
					|| fromBelow && a.type() == FieldType.L
					|| fromBelow && a.type() == FieldType.J
					|| fromAbove && a.type() == FieldType.SEVEN
					|| fromAbove && a.type() == FieldType.F;
			}
			case HORIZONTAL_PIPE ->
			{
				yield isStart && a.coordinates().y() == b.coordinates().y()
					|| a.type() == FieldType.HORIZONTAL_PIPE && a.coordinates().y() == b.coordinates().y()
					|| fromRight && a.type() == FieldType.SEVEN
					|| fromRight && a.type() == FieldType.J
					|| fromLeft && a.type() == FieldType.F
					|| fromLeft && a.type() == FieldType.L;
			}
			case L ->
			{
				yield isStart && (fromAbove || fromRight)
					|| fromAbove && a.type() == FieldType.VERTICAL_PIPE
					|| fromRight && a.type() == FieldType.HORIZONTAL_PIPE
					|| fromRight && a.type() == FieldType.J
					|| (fromAbove || fromRight) && a.type() == FieldType.SEVEN
					|| fromAbove && a.type() == FieldType.F;
			}
			case J ->
			{
				yield isStart && (fromAbove || fromLeft)
					|| fromAbove && a.type() == FieldType.VERTICAL_PIPE
					|| fromLeft && a.type() == FieldType.HORIZONTAL_PIPE
					|| fromLeft && a.type() == FieldType.L
					|| fromAbove && a.type() == FieldType.SEVEN
					|| (fromAbove || fromLeft) && a.type() == FieldType.F;
			}
			case SEVEN ->
			{
				yield isStart && (fromLeft || fromBelow)
					|| fromBelow && a.type() == FieldType.VERTICAL_PIPE
					|| fromLeft && a.type() == FieldType.HORIZONTAL_PIPE
					|| (fromBelow || fromLeft) && a.type() == FieldType.L
					|| fromBelow && a.type() == FieldType.J
					|| fromLeft && a.type() == FieldType.F;
			}
			case F ->
			{
				yield isStart && (fromBelow || fromRight)
					|| fromBelow && a.type() == FieldType.VERTICAL_PIPE
					|| fromRight && a.type() == FieldType.HORIZONTAL_PIPE
					|| fromBelow && a.type() == FieldType.L
					|| (fromBelow || fromRight) && a.type() == FieldType.J
					|| fromRight && a.type() == FieldType.SEVEN;
			}
			case GROUND -> false;
			case START ->
			{
				yield (fromAbove || fromBelow) && a.type() == FieldType.VERTICAL_PIPE
					|| (fromLeft || fromRight) && a.type() == FieldType.HORIZONTAL_PIPE
					|| (fromBelow || fromLeft) && a.type() == FieldType.L
					|| (fromBelow && fromRight) && a.type() == FieldType.J
					|| (fromRight || fromAbove) && a.type() == FieldType.SEVEN
					|| (fromAbove || fromLeft) && a.type() == FieldType.F;
			}
		};
	}

	private Field findStart()
	{
		for (List<Field> rows : maze)
		{
			for (Field field : rows)
			{
				if (field.type() == FieldType.START)
				{
					return field;
				}
			}
		}
		throw new IllegalStateException("no start field");
	}

	private List<List<Field>> initMaze(List<String> input)
	{
		AtomicInteger i = new AtomicInteger(0);
		return input.stream()
			.map(l -> {
				AtomicInteger j = new AtomicInteger(0);
				int row = i.getAndIncrement();
				return l.chars()
					.mapToObj(c -> new Field(new Coordinates(j.getAndIncrement(), row), FieldType.fromValue((char)c)))
					.toList();
			})
			.toList();
	}
}

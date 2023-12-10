package org.example.aoc2023.day10;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PipeMaze2
{
	private final List<List<Field>> maze;

	public PipeMaze2(List<String> input)
	{
		this.maze = initMaze(input);
	}

	public int getNumberOfEnclosedFields()
	{
		Path path = getPath();
		List<Field> fields = path.getFields();

		try (PrintWriter printWriter = new PrintWriter(new FileWriter(new File("/Users/ngr/pipes.txt"))))
		{
			for (List<Field> row : maze)
			{
				for (Field field : row)
				{
					if (fields.contains(field))
					{
						printWriter.printf("%c", getPrintSymbol(field));
					}
					else
					{
						printWriter.print("O");
					}
				}
				printWriter.print("\n");
			}
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}

		return getNumber(fields);
	}

	private int getNumber(List<Field> fields)
	{
		int count = 0;

		for (List<Field> rows : maze)
		{
			for (Field field : rows)
			{
				if (!fields.contains(field) && isPointInPolygon(field, fields))
				{
					count++;
				}
			}
		}

		return count;
	}

	private Path getPath()
	{
		Field start = findStart();
		Path startPath = new Path(start, null);

		List<Path> paths = List.of(startPath);
		do
		{
			paths = paths.stream()
				.map(this::getNextPaths)
				.flatMap(Collection::stream)
				.toList();
		} while (paths.stream().noneMatch(c -> c.getField().type() == FieldType.START));

		return paths.stream()
			.min(Comparator.comparingInt(p -> (int)Math.ceil(p.getSteps() / 2.0)))
			.orElseThrow();
	}

	private List<Path> getNextPaths(Path parent)
	{
		List<Field> adjacentFields = findAdjacentFields(parent.getField(), parent.getParent() != null ? parent.getParent().getField() : null);
		return adjacentFields.stream()
			.map(f -> new Path(f, parent))
			.toList();
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
				yield fromAbove && a.type().down || fromBelow && a.type().up;
			}
			case HORIZONTAL_PIPE ->
			{
				yield fromLeft && a.type().right || fromRight && a.type().left;
			}
			case L ->
			{
				yield fromAbove && a.type().down || fromRight && a.type().left;
			}
			case J ->
			{
				yield fromAbove && a.type().down || fromLeft && a.type().right;
			}
			case SEVEN ->
			{
				yield fromLeft && a.type().right || fromBelow && a.type().up;
			}
			case F ->
			{
				yield fromBelow && a.type().up || fromRight && a.type().left;
			}
			case GROUND -> false;
			case START ->
			{
				yield fromAbove && a.type().down || fromBelow && a.type().up || fromLeft && a.type().right || fromRight && a.type().left;
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

	private char getPrintSymbol(Field field)
	{
		return switch (field.type())
		{
			case VERTICAL_PIPE -> 0x2502;
			case START -> 0x253C;
			case HORIZONTAL_PIPE -> 0x2500;
			case L -> 0x2514;
			case J -> 0x2518;
			case SEVEN -> 0x2510;
			case F -> 0x250C;
			default -> throw new IllegalStateException("Unexpected value: " + field.type());
		};
	}

	public boolean isPointInPolygon(Field p, List<Field> polygon)
	{
		double minX = polygon.get(0).coordinates().x();
		double maxX = polygon.get(0).coordinates().x();
		double minY = polygon.get(0).coordinates().y();
		double maxY = polygon.get(0).coordinates().y();

		for (int i = 1; i < polygon.size(); i++)
		{
			Field q = polygon.get(i);
			minX = Math.min(q.coordinates().x(), minX);
			maxX = Math.max(q.coordinates().x(), maxX);
			minY = Math.min(q.coordinates().y(), minY);
			maxY = Math.max(q.coordinates().y(), maxY);
		}

		if (p.coordinates().x() < minX || p.coordinates().x() > maxX || p.coordinates().y() < minY || p.coordinates().y() > maxY)
		{
			return false;
		}

		boolean inside = false;
		for (int i = 0, j = polygon.size() - 1; i < polygon.size(); j = i++)
		{
			if ((polygon.get(i).coordinates().y() > p.coordinates().y()) != (polygon.get(j).coordinates().y() > p.coordinates().y()) &&
				p.coordinates().x() < (polygon.get(j).coordinates().x() - polygon.get(i).coordinates().x())
					* (p.coordinates().y() - polygon.get(i).coordinates().y()) / (polygon.get(j).coordinates().y() - polygon.get(i).coordinates().y()) + polygon.get(i).coordinates().x())
			{
				inside = !inside;
			}
		}

		return inside;
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

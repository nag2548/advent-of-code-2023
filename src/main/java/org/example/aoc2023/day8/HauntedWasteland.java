package org.example.aoc2023.day8;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HauntedWasteland
{
	private static final Pattern LINE_PATTERN = Pattern.compile("^(\\w+) = \\((\\w+), (\\w+)\\)$");

	private static final String AAA = "AAA";
	private static final String ZZZ = "ZZZ";
	private static final char LEFT = 'L';

	private String instructions;
	private Map<String, NavigationStep> steps = new HashMap<>();

	public HauntedWasteland(List<String> lines)
	{
		initNavigationInstructions(lines);
	}

	public int getCount()
	{
		int count = 0;
		NavigationStep curr = findStep(AAA);
		while (!ZZZ.equals(curr.key()))
		{
			char c = instructions.charAt(count % instructions.length());
			if (LEFT == c)
			{
				curr = findStep(curr.left());
			}
			else
			{
				curr = findStep(curr.right());
			}
			count++;
		}
		return count;
	}

	private NavigationStep findStep(String key)
	{
		return steps.get(key);
	}

	private void initNavigationInstructions(List<String> lines)
	{
		for (int i = 0; i < lines.size(); i++)
		{
			if (i == 0)
			{
				instructions = lines.get(i);
				i++;
			}

			Matcher matcher = LINE_PATTERN.matcher(lines.get(i));
			if (matcher.matches())
			{
				NavigationStep step = new NavigationStep(matcher.group(1), matcher.group(2), matcher.group(3));
				steps.put(step.key(), step);
			}
		}
	}
}

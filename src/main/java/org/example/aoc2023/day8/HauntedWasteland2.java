package org.example.aoc2023.day8;

import static java.util.stream.Collectors.toSet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HauntedWasteland2
{
	private static final Pattern LINE_PATTERN = Pattern.compile("^(\\w+) = \\((\\w+), (\\w+)\\)$");

	private static final String AAA = "AAA";
	private static final String ZZZ = "ZZZ";
	private static final char LEFT = 'L';

	private final ExecutorService executorService = Executors.newFixedThreadPool(10);

	private String instructions;
	private Map<String, NavigationStep> steps = new HashMap<>();
	private Set<NavigationStep> goals = new HashSet<>();

	private Map<String, Integer> startersMap = new HashMap<>();

	public HauntedWasteland2(List<String> lines)
	{
		initNavigationInstructions(lines);
	}

	public long getCount() throws InterruptedException
	{
		Set<NavigationStep> starters = steps.entrySet().stream()
			.filter(s -> s.getKey().endsWith("A"))
			.map(Map.Entry::getValue)
			.collect(toSet());

		for (NavigationStep starter : starters)
		{
			executorService.execute(() -> calculate(starter));
		}

		executorService.shutdown();
		boolean terminated = executorService.awaitTermination(10, TimeUnit.HOURS);
		if (!terminated)
		{
			executorService.shutdownNow();
		}

		return lcm();
	}

	private void calculate(NavigationStep starter)
	{
		int count = 0;
		NavigationStep curr = starter;
		while (!goals.contains(curr))
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

		startersMap.put(starter.key(), count);
	}

	private NavigationStep findStep(String key)
	{
		return steps.get(key);
	}

	private long gcd(long a, long b)
	{
		while (b > 0)
		{
			long temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}

	private long lcm(long a, long b)
	{
		return a * (b / gcd(a, b));
	}

	private long lcm()
	{
		List<Integer> inputs = startersMap.values().stream().toList();
		long result = inputs.get(0);
		for (int i = 1; i < inputs.size(); i++)
		{
			result = lcm(result, inputs.get(i));
		}
		return result;
	}

	private void initNavigationInstructions(List<String> lines)
	{
		for (int i = 0; i < lines.size(); i++)
		{
			if (i == 0)
			{
				instructions = lines.get(i);
			}

			Matcher matcher = LINE_PATTERN.matcher(lines.get(i));
			if (matcher.matches())
			{
				NavigationStep step = new NavigationStep(matcher.group(1), matcher.group(2), matcher.group(3));
				steps.put(step.key(), step);
			}
		}

		goals = steps.entrySet().stream()
			.filter(s -> s.getKey().endsWith("Z"))
			.map(Map.Entry::getValue)
			.collect(toSet());
	}
}

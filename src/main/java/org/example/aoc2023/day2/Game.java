package org.example.aoc2023.day2;

import java.util.List;
import java.util.Map;

public record Game(int id, List<Map<Color, Integer>> cubeMap) {
}

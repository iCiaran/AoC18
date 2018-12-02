package uk.co.ciaranmoran.aoc.challenges;

import java.util.HashSet;

import java.util.Set;

/*
 * https://adventofcode.com/2018/day/1
 * Part A - https://www.youtube.com/watch?v=lNkiQ9CJb7Y
 * Part B - https://www.youtube.com/watch?v=10ryQjLAqhM
 */
public class Day01 extends Challenge{
	
	public Day01() {
		super("01");
	}
	
	@Override
	String partA() {
		int result = 0;
		
		for(String s : input) {
			result += Integer.parseInt(s);
		}
		
		return "" + result;
	}
	
	@Override
	String partB() {
		int frequency = 0;
		Set<Integer> visited = new HashSet<Integer>();
		while(true) {
			for(String s : input) {
				frequency += Integer.parseInt(s); // bad!
				if (visited.contains(frequency)) {
					return "" + frequency;
				}
				visited.add(frequency); //:eyes:
			}

		}
	}
}

package uk.co.ciaranmoran.aoc.challenges;

import java.util.HashSet;
import java.util.Set;

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

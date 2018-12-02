package uk.co.ciaranmoran.aoc.challenges;

import java.util.HashMap;

/*
 * https://adventofcode.com/2018/day/2
 * Part A - https://youtu.be/WFdOJbCt37E
 * Part B - https://youtu.be/EIxzFvnUIo4
 */
public class Day02 extends Challenge {

	public Day02() {
		super("02");
	}

	@Override
	String partA() {
		int two = 0;
		int three = 0;

		for (String s : input) {
			HashMap<Character, Integer> letters = new HashMap<Character, Integer>();

			for (int i = 0; i < s.length(); i++) {
				if (letters.containsKey(s.charAt(i))) {
					letters.put(s.charAt(i), letters.get(s.charAt(i)) + 1);
				} else {
					letters.put(s.charAt(i), 1);
				}
			}

			boolean hasTwo = false;
			boolean hasThree = false;

			for (char key : letters.keySet()) {
				if (letters.get(key) == 2)
					hasTwo = true;
				if (letters.get(key) == 3)
					hasThree = true;
				if (hasTwo && hasThree)
					break;
			}

			if (hasTwo)
				two++;
			if (hasThree)
				three++;
		}

		return "" + two * three;
	}

	@Override
	String partB() {
		for (String a : input) {
			for (String b : input) {
				int index = compareStrings(a, b);
				if (index != -1)
					return getStringWithoutDiff(a, b, index);
			}
		}
		return super.partB();
	}

	int compareStrings(String a, String b) {
		int diff = 0;
		int index = -1;
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) != b.charAt(i)) {
				index = i;
				diff++;
			}
			if (diff > 1)
				return -1;
		}
		return index;
	}

	String getStringWithoutDiff(String a, String b, int index) {
		return a.substring(0, index) + b.substring(index + 1);
	}
}

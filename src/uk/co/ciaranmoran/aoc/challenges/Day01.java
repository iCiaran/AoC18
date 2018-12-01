package uk.co.ciaranmoran.aoc.challenges;

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
}

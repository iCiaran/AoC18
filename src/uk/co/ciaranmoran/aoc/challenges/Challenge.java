package uk.co.ciaranmoran.aoc.challenges;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Challenge {
	protected ArrayList<String> input;
	private String day;
	
	public Challenge(String day) {
		this.day = day;
		input = new ArrayList<String>();
		readInput();
	}
	
	String partA() {
		return this + "A";
	}

	String partB() {
		return this + "B";
	}
	
	String getInputFileName() {
		return String.format("inputs/%s.txt", day);
	}

	public String toString() {
		return day;
	}
	
	public void readInput() {
		try (BufferedReader br = new BufferedReader(new FileReader(getInputFileName()))) {
		   String line = null;
		   while ((line = br.readLine()) != null) {
		       this.input.add(line);
		   }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

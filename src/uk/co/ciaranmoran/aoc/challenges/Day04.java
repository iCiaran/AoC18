package uk.co.ciaranmoran.aoc.challenges;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Day04 extends Challenge{

	Map<Integer, Map<Integer, Integer>> minutes = new HashMap<Integer, Map<Integer, Integer>>();
	Map<Integer, Map<Integer, Integer>> times   = new HashMap<Integer, Map<Integer, Integer>>();
	Map<Integer, Integer>               total   = new HashMap<Integer, Integer>();
	
	public Day04() {
		super("04");
		parseInput();
	}
	
	void parseInput() {
		Collections.sort(input, new Comparator<String>() {
			public int compare(String a, String b) {
				return a.substring(1, 17).compareTo(b.substring(1, 17));
			}
		});
		
		int currentGuard = 0;
		int startSleep = 0;
		int endSleep = 0;
		
		for(String s : input) {
			switch(s.charAt(19)) {
				case 'G':
					currentGuard = Integer.parseInt(s.split(" ")[3].substring(1));
					break;
				case 'f':
					startSleep = Integer.parseInt(s.substring(15, 17));
					break;
				case 'w':
					endSleep = Integer.parseInt(s.substring(15, 17));
					addToTotal(currentGuard, startSleep, endSleep, total);
					addToTime(currentGuard, startSleep, endSleep, times);
					break;
			}
		}
	}
	
	@Override
	String partA() {
		int id = getLongest(total);
		int minute = getmostFrequent(id, times);
		
		return "" + id * minute;
	}
	
	int getLongest(Map<Integer, Integer> total) {
		int max = 0;
		int id = 0;
		for(int k : total.keySet()) {
			if(total.get(k) > max) {
				max = total.get(k);
				id = k;
			}
		}
		return id;
	}
	
	int getmostFrequent(int id, Map<Integer, Map<Integer, Integer>> times) {
		return getLongest(times.get(id));
	}
	
	void addToTotal(int currentGuard, int startSleep, int endSleep, Map<Integer, Integer> total) {
		if(total.containsKey(currentGuard)) {
			total.put(currentGuard, total.get(currentGuard) + endSleep - startSleep);
		}else {
			total.put(currentGuard, endSleep - startSleep);
		}
	}
	
	void addToTime(int currentGuard, int startSleep, int endSleep, Map<Integer, Map<Integer, Integer>> times) {
		if (!times.containsKey(currentGuard)) {
			times.put(currentGuard, new HashMap<Integer, Integer>());
		}
		for(int i = startSleep; i < endSleep; i++) {
			if(times.get(currentGuard).containsKey(i)) {
				times.get(currentGuard).put(i, times.get(currentGuard).get(i) + 1);
			} else {
				times.get(currentGuard).put(i,1);
			}
			
			if(!minutes.containsKey(i)) {
				minutes.put(i, new HashMap<Integer, Integer>());
			}
			if(minutes.get(i).containsKey(currentGuard)) {
				minutes.get(i).put(currentGuard, minutes.get(i).get(currentGuard) + 1);
			} else {
				minutes.get(i).put(currentGuard, 1);
			}
		}
	}
	
	@Override
	String partB() {
		int g = 0;
		int max = 0;
		int m = 0;
		for(int minute : minutes.keySet()) {
			for(int guard : minutes.get(minute).keySet()) {
				if(minutes.get(minute).get(guard) > max) {
					max = minutes.get(minute).get(guard);
					g = guard;
					m = minute;
				}
			}
		}
		return "" + g * m;
	}

}

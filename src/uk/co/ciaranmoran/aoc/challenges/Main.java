package uk.co.ciaranmoran.aoc.challenges;

import java.util.ArrayList;

public class Main {
	static final int currentDay = 1;

	public static void main(String[] args) {
//		runSingleDay(new Day01(), true, false);
//		runSingleDay(new Day01(), false, true);
//		runSingleDay(new Day01(), true, true );
		runAllDays(getAllDays());
	}

	public static void runSingleDay(Challenge day, boolean A, boolean B) {
		String resultA, resultB;
		if (A) {
			final long startTimeA = System.nanoTime();
			resultA = day.partA();
			final long timeA = System.nanoTime() - startTimeA;
			System.out.println(day + "A: " + resultA + " (" + timeA / 1000 + "ms)");
		}
		if (B) {
			final long startTimeB = System.nanoTime();
			resultB = day.partB();
			final long timeB = System.nanoTime() - startTimeB;
			System.out.println(day + "B: " + resultB + " (" + timeB / 1000 + "ms)\n");
		}
	}
	
	public static void runSingleDay(Challenge day) {
		runSingleDay(day, true, true);
	}

	public static void runAllDays(ArrayList<Challenge> days) {
		for (Challenge day : days) {
			runSingleDay(day, true, true);
		}
	}

	public static ArrayList<Challenge> getAllDays() {
		ArrayList<Challenge> days = new ArrayList<Challenge>(currentDay);
		for (int i = 1; i <= currentDay; ++i) {
			try {
				days.add((Challenge) Class.forName(String.format("uk.co.ciaranmoran.aoc.challenges.Day%02d", i)).newInstance());
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return days;
	}
}

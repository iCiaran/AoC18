package uk.co.ciaranmoran.aoc.challenges;

import java.util.ArrayList;

public class Main {
	static final int currentDay = 4;

	public static void main(String[] args) {
//		runSingleDay(new Day02(), true, false);
//		runSingleDay(new Day02(), false, true);
		runSingleDay(new Day07(), true, true);
//		runAllDays(getAllDays());
	}

	public static void runSingleDay(Challenge day, boolean A, boolean B) {
		String resultA, resultB;
		if (A) {
			final long startTimeA = System.nanoTime();
			resultA = day.partA();
			final double timeA = (System.nanoTime() - startTimeA) / 1000000.0;
			System.out.println(String.format("%sA: %s (%.2fms)", day, resultA, timeA));
		}
		if (B) {
			final long startTimeB = System.nanoTime();
			resultB = day.partB();
			final double timeB = (System.nanoTime() - startTimeB) / 1000000.0;
			System.out.println(String.format("%sB: %s (%.2fms)", day, resultB, timeB));
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
				days.add((Challenge) Class.forName(String.format("uk.co.ciaranmoran.aoc.challenges.Day%02d", i))
						.newInstance());
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return days;
	}
}

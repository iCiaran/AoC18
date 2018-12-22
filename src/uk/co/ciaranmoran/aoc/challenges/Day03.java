package uk.co.ciaranmoran.aoc.challenges;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Day03 extends Challenge {

	Map<Point, Integer> fabric = new HashMap<Point, Integer>();

	public Day03() {
		super("03");
	}

	@Override
	String partA() {
		int total = 0;
		for (String s : input) {
			String[] xywh = s.substring(s.indexOf('@') + 1).split(":");
			String[] xy = xywh[0].split(",");
			String[] wh = xywh[1].split("x");
			total += addClaim(Integer.parseInt(xy[0].trim()), Integer.parseInt(xy[1].trim()),
					Integer.parseInt(wh[0].trim()), Integer.parseInt(wh[1].trim()), fabric);
		}

		return "" + total;
	}

	@Override
	String partB() {
		for (String s : input) {
			String[] xywh = s.substring(s.indexOf('@') + 1).split(":");
			String[] xy = xywh[0].split(",");
			String[] wh = xywh[1].split("x");
			if (checkClaim(Integer.parseInt(xy[0].trim()), Integer.parseInt(xy[1].trim()),
					Integer.parseInt(wh[0].trim()), Integer.parseInt(wh[1].trim()), fabric)) {
				return s.substring(1, s.indexOf('@'));
			}
		}

		return super.partB();
	}

	int addClaim(int x, int y, int w, int h, Map<Point, Integer> m) {
		int extra = 0;
		int t;
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				Point p = new Point(x + i, y + j);
				if (m.containsKey(p)) {
					t = m.get(p);
					if (t == 1) {
						extra++;
					}
					m.put(p, t + 1);
				} else {
					m.put(p, 1);
				}
			}
		}
		return extra;
	}

	boolean checkClaim(int x, int y, int w, int h, Map<Point, Integer> m) {
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				Point p = new Point(x + i, y + j);
				if (m.get(p) != 1)
					return false;
			}
		}
		return true;
	}

}

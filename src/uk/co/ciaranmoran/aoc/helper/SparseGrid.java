package uk.co.ciaranmoran.aoc.helper;

import java.awt.Point;
import java.util.HashMap;

public class SparseGrid <T> extends Grid<T>{
	
	private HashMap<Point, T> grid;
	
	public SparseGrid(int maxX, int maxY, int minX, int minY, boolean bounded){
		super(maxX, maxY, minX, minY,bounded);
		grid = new HashMap<Point, T>();
	}
	
	@Override
	public T get(Point p) {
		return grid.get(p);
	}

	@Override
	public void set(Point coord, T value) {
		grid.put(coord, value);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Point key : grid.keySet()) {
		    builder.append(key + ": " + grid.get(key) + "\n");
		}
		return builder.toString();
	}
}

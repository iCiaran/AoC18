package uk.co.ciaranmoran.aoc.helper;

import java.awt.Point;
import java.util.ArrayList;

public class DenseGrid<T> extends Grid<T> {

	private ArrayList<ArrayList<T>> grid;

	public DenseGrid(int maxX, int maxY, int minX, int minY, boolean bounded) {
		super(maxX, maxY, minX, minY, bounded);
		if (!bounded)
			throw new IllegalArgumentException("Dense grid must be bounded (for now)");
		if (minX > maxX)
			throw new IllegalArgumentException("minX must be lower than maxX");
		if (minY > maxY)
			throw new IllegalArgumentException("minY must be lower than maxY");

		grid = new ArrayList<ArrayList<T>>(maxY - minY);
		for (int i = minY; i < maxY; i++) {
			ArrayList<T>temp = new ArrayList<T>(maxX - minX);
			for (int j = minX; j < maxX; j++) {
				temp.add(null);
			}
			grid.add(temp);
		}
	}

	@Override
	public T get(Point coord) {
		int y = coord.y - minY;
		int x = coord.x - minX;
		return grid.get(y).get(x);
	}

	@Override
	public void set(Point coord, T value) {
		int y = coord.y - minY;
		int x = coord.x - minX;
		grid.get(y).set(x, value);
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (ArrayList<T> y : grid) {
			for ( T x : y) {
				builder.append(x != null? x + " " : "_ ");
			}
			builder.append('\n');
		}
		return builder.toString();
	}

}

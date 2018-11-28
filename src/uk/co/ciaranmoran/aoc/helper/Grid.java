package uk.co.ciaranmoran.aoc.helper;

import java.awt.Point;

public abstract class Grid <T>{
	public int maxX, minX, maxY, minY;
	public Grid(int maxX, int maxY, int minX, int minY, boolean bounded) {
		this.maxX = maxX;
		this.minX = minX;
		this.maxY = maxY;
		this.minY = minY;
	}
	
	public abstract T get(Point coord);
	public abstract void set(Point coord, T value);
	public abstract String toString();
	
}

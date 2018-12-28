package uk.co.ciaranmoran.aoc.challenges;
import java.awt.Point;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class Day06 extends Challenge {

    private Point[] points = new Point[input.size()];
    private Plot maxX,maxY,minX,minY;
    private int width;
    private int height;

    public Day06() {
        super("06");
        for(int i = 0; i < input.size(); i++) {
            int x = Integer.parseInt(input.get(i).split(", ")[0]);
            int y = Integer.parseInt(input.get(i).split(", ")[1]);
            points[i] = new Point(x,y);
        }
        maxX = new Plot(0,0,Integer.MIN_VALUE);
        maxY = new Plot(0,0,Integer.MIN_VALUE);
        minX = new Plot(0,0,Integer.MAX_VALUE);
        minY = new Plot(0,0,Integer.MAX_VALUE);

        for(int i = 0; i < points.length; i++) {
            if(points[i].x > maxX.value){
                maxX.value = points[i].x;
                maxX.owner = i;
                maxX.p = points[i];
            }
            if(points[i].x < minX.value){
                minX.value = points[i].x;
                minX.owner = i;
                minX.p =points[i];
            }
            if(points[i].y > maxY.value){
                maxY.value = points[i].y;
                maxY.owner = i;
                maxY.p = points[i];
            }
            if(points[i].y < minY.value){
                minY.value = points[i].y;
                minY.owner = i;
                minY.p = points[i];
            }
        }

        width = maxX.value - minX.value;
        height = maxY.value - minY.value;
    }

    class Plot {
        int owner,value;
        Point p;
        public String toString(){
            return String.format("(%d,%d)", owner,value);
        }
        Plot(int x, int y, int value){
            this.owner = -1;
            this.value = value;
            this.p = new Point(x,y);
        }
    }

    @Override
    String partA() {
        Plot[][] map = new Plot[width][height];
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++) {
                map[i][j] = new Plot(minX.value + i,minY.value + j, Integer.MAX_VALUE);
            }
        }

        for (Plot[] plots : map){
            for (Plot plot : plots){
                for (int i = 0; i < points.length; i++) {
                    int m = manhattan(plot.p, points[i]);
                    if (m < plot.value) {
                        plot.owner = i;
                        plot.value = m;
                    } else if (m == plot.value) {
                        plot.owner = -1;
                    }
                }
            }
        }

        Set<Integer> inf = new HashSet<>();
        for(int i = 0; i < width; i++){
            inf.add(map[i][0].owner);
            inf.add(map[i][height-1].owner);
        }
        for(int i = 0; i < height; i++){
            inf.add(map[0][i].owner);
            inf.add(map[width-1][i].owner);
        }

        Map<Integer,Integer> count = new HashMap<>();


        for (Plot[] plots : map){
            for (Plot plot : plots){
                if(!inf.contains(plot.owner)){
                    if(count.containsKey(plot.owner)){
                        count.put(plot.owner, count.get(plot.owner) + 1);
                    } else {
                        count.put(plot.owner, 1);
                    }
                }
            }
        }

        int max = 0;
        for (int k : count.keySet()) {
            if(count.get(k) > max) {
                max = count.get(k);
            }
        }

        return "" + max;
    }

    @Override
    String partB() {
        final int threshold = 10000;

        Plot[][] map = new Plot[width][height];
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++) {
                map[i][j] = new Plot(minX.value + i,minY.value + j,0);
                for(Point p : points){
                    map[i][j].value += manhattan(p, map[i][j].p);
                }
            }
        }
        int total = 0;
        for (Plot[] plots : map) {
            for (Plot plot : plots) {
                if (plot.value < threshold) {
                    total++;
                }
            }
        }
        return "" + total;
    }

    @SuppressWarnings("unused")
    void printMap(Plot[][] map, boolean owner_value){
        for (Plot[] plots : map){
            for (Plot plot : plots){
                if(owner_value){
                    System.out.print(String.format("%03d ",plot.owner));
                } else {
                    System.out.print(String.format("%03d ",plot.value));
                }
            }
            System.out.println();
        }
    }

    private int manhattan(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
}

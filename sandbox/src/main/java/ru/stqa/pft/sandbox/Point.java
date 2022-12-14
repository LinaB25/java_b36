package ru.stqa.pft.sandbox;

public class Point {

    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point p) {
        double a = Math.pow((p.x - this.x), 2);
        double b = Math.pow((p.y - this.y), 2);
        return Math.sqrt((a + b));
    }
}
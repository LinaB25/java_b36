package ru.stqa.pft.sandbox;

import static ru.stqa.pft.sandbox.Point.distance;

public class Prog {

    public static void main(String[] args) {
        Point p1 = new Point(3, 4);
        Point p2 = new Point(3, 4);
        Point p3 = new Point(4, 6);
        System.out.println("Расстояние между точками 1 и 2 = " + distance(p1, p2));
        System.out.println("Расстояние между точками 2 и 3 = " + distance(p2, p3));
    }
}
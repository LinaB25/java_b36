package ru.stqa.pft.sandbox;

public class Prog {

    public static void main(String[] args) {
        Point p1 = new Point(3, 1);
        Point p2 = new Point(1, 1);
        System.out.println("Расстояние между точками p1 и p2 = " + p1.distance(p2));
    }

}
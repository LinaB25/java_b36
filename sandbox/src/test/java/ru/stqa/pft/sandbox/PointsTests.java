package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import static ru.stqa.pft.sandbox.Point.distance;

public class PointsTests {

    @Test
    public void testDistance() {
        Point p1 = new Point(3,1);
        Point p2 = new Point(1,1);
        Assert.assertEquals(distance(p1, p2),2, "miscalculation");
    }

    @Test
    public void testDistanceTwo() {
        Point p1 = new Point(3,3);
        Point p2 = new Point(3,1);
        Assert.assertEquals(distance(p1, p2),2, "miscalculation");
    }

    @Test
    public void testDistanceThree() {
        Point p1 = new Point(3,1);
        Assert.assertEquals(distance(p1, p1),0, "miscalculation");
    }
}

public class Prog {

    public static void main(String[] args) {
        Point p1 = new Point(3, 10);
        Point p2 = new Point(30, 31);
        System.out.println("Расстояние между точками = " + distance(p1, p2));
    }

    public static double distance(Point p1, Point p2) {
        double a = Math.pow((p1.x - p2.x), 2);
        double b = Math.pow((p1.y - p2.y), 2);
        return Math.sqrt((a + b));
    }
}
//NAME: Justin Ward
//ALGORITHM PAPER: Rotating Calipers
//COURSE TITLE: Data Structures and Algorithms
//COURSE NUMBER:CPSC 4355
//TERM: Summer 2019

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    static UI ui;
    static Screen screen;

    public static void main(String[] args)
    {
        screen = new Screen(1280, 720);
        ui = new UI(screen);
        createOMBB(randomPoints(25));
    }

    public static void createOMBB(List<Point> points)
    {
        Polygon hull = new Polygon(ConvexHull.makeHull(points));
        Rectangle rect = Caliper.minimumBox(hull);
        screen.points = points;
        screen.hull = hull;
        screen.OMBB = rect;
        screen.AABB = Caliper.boundingBox(hull);
    }

    public static List<Point> randomPoints(int n)
    {
        List<Point> points = new ArrayList<Point>(n);
        Random ran = new Random();

        for(int i=0;i<n;i++)
        {
            points.add(new Point(300 + ran.nextInt(400), 200 + ran.nextInt(250)));
        }

        return points;
    }
}
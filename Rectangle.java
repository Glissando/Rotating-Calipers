//NAME: Justin Ward
//ALGORITHM PAPER: Rotating Calipers
//COURSE TITLE: Data Structures and Algorithms
//COURSE NUMBER:CPSC 4355
//TERM: Summer 2019

import java.util.ArrayList;

public class Rectangle extends Polygon
{
    public double width;
    public double height;

    public Rectangle(double x, double y, double width, double height)
    {
        this.width = width;
        this.height = height;
        this.points = new ArrayList<Point>(4);
        this.points.add(new Point(x, y));
        this.points.add(new Point(x + width, y));
        this.points.add(new Point(x + width, y + height));
        this.points.add(new Point(x, y + height));
        calcCenter();
    }

    public Rectangle(double width, double height)
    {
        this.width = width;
        this.height = height;
        this.points = new ArrayList<Point>(4);
        this.points.add(new Point(0, 0));
        this.points.add(new Point(width, 0));
        this.points.add(new Point(width, height));
        this.points.add(new Point(0, height));
        calcCenter();
    }

    public double area()
    {
        return width * height;
    }
}
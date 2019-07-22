//NAME: Justin Ward
//ALGORITHM PAPER: Rotating Calipers
//COURSE TITLE: Data Structures and Algorithms
//COURSE NUMBER:CPSC 4355
//TERM: Summer 2019

import java.awt.*;
import java.util.List;

interface Renderable
{
    public void draw(Graphics page);
    public void draw(Graphics page, Color color);
}

public class Polygon implements Renderable
{
    protected List<Point> points;
    protected Point center;

    public Polygon()
    {
        center = new Point();
    }

    public Polygon(List<Point> points)
    {
        this.points = points;
        center = new Point();
        calcCenter();
    }

    public void addPoint(Point p)
    {
        this.points.add(p);
    }

    public void removePoint(Point p)
    {
        this.points.remove(p);
    }

    public void rotate(double theta)
    {
        rotate(theta, center);
    }

    public void rotate(double theta, Point pivot)
    {
        for(int i=0;i<points.size();i++)
        {
            Point p = points.get(i);
            double x = p.getX();
            double y = p.getY();
            x -= pivot.getX();
            y -= pivot.getY();
            double tx = x;
            double sinTheta = Math.sin(theta);
            double cosTheta = Math.cos(theta);
            x = x * cosTheta - y * sinTheta;
            y = tx * sinTheta + y * cosTheta;
            x += pivot.getX();
            y += pivot.getY();
            p.setLocation(x, y);
        }
    }
    
    public Point getPoint(int index)
    {
        return points.get(index);
    }

    public Point getCenter()
    {
        return center;
    }

    public Point getEdge(int index)
    {
        int next = (index+1) % pointCount();
        Point p1 = points.get(index);
        Point p2 = points.get(next);

        return p2.subtract(p1);
    }

    protected void calcCenter()
    {
        double x = 0;
        double y = 0;
        int n = points.size();

        for(int i=0;i<n;i++)
        {
            Point p = points.get(i);
            x += p.getX();
            y += p.getY();
        }

        center.setLocation(x / n, y / n);
    }
    
    public int pointCount()
    {
        return points.size();
    }

    public int edgeCount()
    {
        if(points.size() == 1)
        {
            return 0;
        }
        else if(points.size() == 2)
        {
            return 1;
        }
        else
        {
            return points.size();
        }
    }

    public boolean contains(Point p)
    {
        for(int i=0;i<pointCount();i++)
        {
            Point normal = getEdge(i).normal();
            p = p.subtract(center);
            if(normal.dot(p) > 0)
            {
                return false;
            }
        }
        return true;
    }

    public void draw(Graphics page)
    {
        draw(page, Color.GREEN);
    }

    public void draw(Graphics page, Color color)
    {
        page.setColor(color);
        
        for(int i=0;i<edgeCount();i++)
        {
            int next = (i+1) % pointCount();
            int x1 = (int)Math.round(points.get(i).getX());
            int y1 = (int)Math.round(points.get(i).getY());
            int x2 = (int)Math.round(points.get(next).getX());
            int y2 = (int)Math.round(points.get(next).getY());

            page.drawLine(x1, y1, x2, y2);
        }

        for(int i=0;i<pointCount();i++)
        {
            points.get(i).draw(page, color);
        }

        center.draw(page, Color.RED);
    }
}
//NAME: Justin Ward
//ALGORITHM PAPER: Rotating Calipers
//COURSE TITLE: Data Structures and Algorithms
//COURSE NUMBER:CPSC 4355
//TERM: Summer 2019

import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

final class Point implements Comparable<Point>, Renderable 
{	
	public double x;
	public double y;
	
	public Point()
	{
		x = 0;
		y = 0;
	}

	public Point(double x, double y) 
	{
		this.x = x;
		this.y = y;
	}
	
	public String toString() 
	{
		return String.format("Point(%g, %g)", x, y);
	}
	
	public boolean equals(Object obj) 
	{
		if (!(obj instanceof Point))
			return false;
		else 
		{
			Point other = (Point)obj;
			return x == other.x && y == other.y;
		}
	}
    
    public void setLocation(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
	
	public double getX()
	{
		return this.x;
	}

	public double getY()
	{
		return this.y;
	}

	public double magnitude()
	{
		return Math.sqrt(x*x + y*y);
	}

	public double dot(Point p)
	{
		return this.x * p.getX() + this.y * p.getY();
	}

	public double distance(Point p)
	{
		return Math.sqrt(Math.pow(x-p.x,2) + Math.pow(y-p.y,2));
	}

	public double angle(Point p)
	{
		System.out.println(this.normalize().dot(p.normalize()));
		return Math.acos(this.normalize().dot(p.normalize()));
	}

	public Point subtract(Point p)
	{
		return new Point(this.x - p.x, this.y - p.y);
	}

	public Point add(Point p)
	{
		return new Point(this.x + p.x, this.y + p.y);
	}

	public Point normalize()
	{
		double d = magnitude();
		return new Point(x / d, y / d);
	}

	public Point normal()
	{
		return new Point(-y, x);
	}

	public Point negate()
	{
		return new Point(-y, -x);
	}

	public int hashCode() 
	{
		return Objects.hash(x, y);
	}

	public Point clone()
	{
		return new Point(x, y);
	}
	
	public int compareTo(Point other) 
	{
		if (x != other.x)
			return Double.compare(x, other.x);
		else
			return Double.compare(y, other.y);
	}

	public void draw(Graphics page)
	{
		int px = (int)Math.round(getX());
		int py = (int)Math.round(getY());
		page.setColor(Color.GREEN);
		page.fillOval(px-6, py-6, 12, 12);
	}

	public void draw(Graphics page, Color color)
	{
		page.setColor(color);
		int px = (int)Math.round(getX());
		int py = (int)Math.round(getY());
		page.fillOval(px-6, py-6, 12, 12);
	}
}
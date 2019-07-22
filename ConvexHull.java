//NAME: Justin Ward
//ALGORITHM PAPER: Rotating Calipers
//COURSE TITLE: Data Structures and Algorithms
//COURSE NUMBER:CPSC 4355
//TERM: Summer 2019

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ConvexHull 
{
	
	// Returns a new list of points representing the convex hull of
	// the given set of points. The convex hull excludes collinear points.
	// This algorithm runs in O(n log n) time.
	public static List<Point> makeHull(List<Point> points) 
	{
		List<Point> newPoints = new ArrayList<>(points);
		Collections.sort(newPoints);
		return makeHullPresorted(newPoints);
	}
	
	
	// Returns the convex hull, assuming that each points[i] <= points[i + 1]. Runs in O(n) time.
	public static List<Point> makeHullPresorted(List<Point> points) 
	{
		if (points.size() <= 1)
			return new ArrayList<>(points);
		
		// Andrew's monotone chain algorithm. Positive y coordinates correspond to "up"
		// as per the mathematical convention, instead of "down" as per the computer
		// graphics convention. This doesn't affect the correctness of the result.
		
		List<Point> upperHull = new ArrayList<>();
		for (Point p : points) 
		{
			while (upperHull.size() >= 2) 
			{
				Point q = upperHull.get(upperHull.size() - 1);
				Point r = upperHull.get(upperHull.size() - 2);
				if ((q.x - r.x) * (p.y - r.y) >= (q.y - r.y) * (p.x - r.x))
					upperHull.remove(upperHull.size() - 1);
				else
					break;
			}
			upperHull.add(p);
		}
		upperHull.remove(upperHull.size() - 1);
		
		List<Point> lowerHull = new ArrayList<>();
		for (int i = points.size() - 1; i >= 0; i--) 
		{
			Point p = points.get(i);
			while (lowerHull.size() >= 2) 
			{
				Point q = lowerHull.get(lowerHull.size() - 1);
				Point r = lowerHull.get(lowerHull.size() - 2);
				if ((q.x - r.x) * (p.y - r.y) >= (q.y - r.y) * (p.x - r.x))
					lowerHull.remove(lowerHull.size() - 1);
				else
					break;
			}
			lowerHull.add(p);
		}
		lowerHull.remove(lowerHull.size() - 1);
		
		if (!(upperHull.size() == 1 && upperHull.equals(lowerHull)))
			upperHull.addAll(lowerHull);
		return upperHull;
	}
	
}
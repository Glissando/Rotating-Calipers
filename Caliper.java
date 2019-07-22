//NAME: Justin Ward
//ALGORITHM PAPER: Rotating Calipers
//COURSE TITLE: Data Structures and Algorithms
//COURSE NUMBER:CPSC 4355
//TERM: Summer 2019

public final class Caliper
{
    //Generates the minimum bounding box of the given polygon
    //Operates in O(n log n + m^2) time
    //where n is the number of random points
    //and m is the number of edges in the convex hull
    public static Rectangle minimumBox(Polygon polygon)
    {
        Rectangle[] rects = new Rectangle[polygon.edgeCount()];

        //Iterate over edge of the convex hull
        for(int i=0;i<polygon.edgeCount();i++)
        {
            Point edge = polygon.getEdge(i);
            //Rotate the polygon so that the selected edge is parallel to a major axis
            //The y-Axis in this use case
            double theta = Math.acos(edge.normalize().y);
            //System.out.println(theta);
            polygon.rotate(theta);
            //Calculate a bounding box
            rects[i] = boundingBox(polygon);
            polygon.rotate(-theta);
            rects[i].rotate(-theta, polygon.getCenter());
        }

        double minArea = Double.MAX_VALUE;
        Rectangle box = rects[0];

        //Find the bounding box with the smallest area, this is the minimum bounding box
        for(int i=0;i<rects.length;i++)
        {
            double area = rects[i].area();
            if(area < minArea)
            {
                minArea = area;
                box = rects[i];
            }
        }

        //We're done! :D
        return box;
    }

    public static Rectangle boundingBox(Polygon polygon)
    {
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;

        for(int i=0;i<polygon.pointCount();i++)
        {
            Point p = polygon.getPoint(i);
            if(minX > p.x)
                minX = p.x;
            if(maxX < p.x)
                maxX = p.x;
            if(minY > p.y)
                minY = p.y;
            if(maxY < p.y)
                maxY = p.y;
        }

        //System.out.println(minX + " " + minY);
        return new Rectangle(minX, minY, maxX - minX, maxY - minY);
    }
}
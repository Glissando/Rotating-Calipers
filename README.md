# Rotating Calipers

Final Project for Data Structure & Algorithms Summer 2019.

![Example GIF](images/example.gif)

## Table of Contents

> References to explanations of the sourcecode

- [Rotating Calipers](#rotating-calipers)
  - [Table of Contents](#table-of-contents)
  - [Introduction](#introduction)
  - [Definitions](#definitions)
  - [Description](#description)
  - [Performance](#performance)
  - [Code snippet](#code-snippet)
  - [Proof](#proof)
  - [References](#references)


## Introduction

The rotating Calipers algorithm is an algorithm developed by Freeman and Shapira and later generalized by George Toussaint. Godfriend Toussaint gave this algorithm it's name, Rotating Calipers referencing a pair of calipers going across the polygon. My motivations for choosing this algorithm was so I could learn more about Computational Geometry.
- The theorem by Shapira states 
```
"The smallest-area enclosing rectangle of a polygon has a side collinear with one of the edges of its convex hull"
```
- This theorem reduces the number of candidate rectangles down to ones which share an edge with the convex hull.

<a href=""><img src="images/exampe.gif" title="Example" alt="Example"></a>


For my usage I used the algorithm to generate a minimum bounding box, the easiest way to find a minimum bounding box is to find the mininimum and maximum (x,y) coordinates for that set of points and to construct a rectangle from it. This is the only method for solving problems such as the minimum bounding box in 2D and has been generalized to 3D by O'Rourke.

## Definitions

**Convex Hull** - the smallest convex set that contains a set of points S.

**Supporting Lines** - A line that goes through the polygon such that the whole polygon lies on one side of the line is called a supporting line.

**Antipodal Points** - a pair of vertices such that one can draw two parallel supporting lines through them.

**Diameter of a polygon** - the largest distance between a pair of vertices in the set of points S.

**AABB(Axis Aligned Bounding Box)** - The smallest rectangle containing the point such at each side is parallel to the x or y axis/

**OMBB(Oriented Minimum Bounding Box)** - the smallest rectangle where the orientation does not matter.

## Description

Rotating Calipers is a general use case algorithm in computational geometry that can be used to solve a wide variety
of problems ranging from finding the diameter of a convex polygon, finding the distance between two convex polygons, etc. The term Calipers is used as a visual metaphor for the "parallel supporting lines" that sweet across the polygon.
The Rotating Calipers algorithm generates The OMBB or the Optimal minimum bounding box of a set of points.
This algorithm is possible due to a theorem by Shapira which that the mininmum bounding box of a convex polygon lies on one of
that polygon's edges. Making it possible to iterate over the polygon to calculate this. Godfried described this algorithm in his paper as a general method for solving a variety of problems in computational similar to convex hull generation or delaunay triangulation. 

The algorithm operates in seven steps
1. Generate a convex hull from a set of points
2. Find the minimum or extreme points of the convex hull
3. Construct two vertical supporting lines at the minimum and maximum (x, y) coordinates
4. 

## Performance

The Rotating Calipers algorithm operates in Linear Time but requires a convex hull, so it is typically bounded by the convex hull generation which in my case is n log n. The Rotating Caliper algorithm itself is only O(m) or linear time. Where n refers to the number of points used in the calculation and m is referring to the number of verticies in the convex hull. So the Rotating Calipers algorithm will perform worse in situations where all or most points in the set are located on the convex hull, such as in the situation where the points lie on a circle.

## Code snippet

```java
//Iterate over the edges of the convex hull
for(int i=0;i<polygon.edgeCount();i++)
{
    Point edge = polygon.getEdge(i);
    //Rotate the polygon so that the current edge is parallel to a major axis
    //The y-Axis in this use case
    double theta = Math.acos(edge.normalize().y);
    polygon.rotate(theta);
    //Calculate a bounding box
    rects[i] = boundingBox(polygon);
    polygon.rotate(-theta);
    rects[i].rotate(-theta, polygon.getCenter());
}
```

## Proof

The theorem that this algorithm was built upon was proved by first breaking it apart into two theorems whose results would lead to the final theorem.

## References

https://www.iis.sinica.edu.tw/papers/liu/21474-F.pdf
http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.155.5671&rep=rep1&type=pdf
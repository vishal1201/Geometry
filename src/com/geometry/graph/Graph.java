package com.geometry.graph;

import com.geometry.utils.Range;

import java.util.LinkedList;
import java.util.List;

public class Graph {
    private static final Quadrant FIRST = new Quadrant("first", new Range(0, Integer.MAX_VALUE), new Range(0, Integer.MAX_VALUE));
    private static final Quadrant SECOND = new Quadrant("second", new Range(Integer.MIN_VALUE, 0), new Range(Integer.MAX_VALUE, 0));
    private static final Quadrant THIRD = new Quadrant("third", new Range(Integer.MIN_VALUE, 0), new Range(Integer.MIN_VALUE, 0));
    private static final Quadrant FOURTH = new Quadrant("fourth", new Range(0, Integer.MAX_VALUE), new Range(Integer.MIN_VALUE, 0));
    private String label;
    private List<Point> points;

    public Graph(String label) {
        this.label = label;
        points = new LinkedList<>();
    }

    public Point addPoint(double x, double y, String label) {
        Point newPoint = new Point(x, y, label);
        Quadrant container = findQuadrant(newPoint);
        if (container.checkIfUnique(newPoint)) {
            container.points.add(newPoint);
            System.out.printf("Successfully created %s in %s-Quadrant\n", newPoint, container);
            return newPoint;
        }

        System.out.printf("Adding %s failed\n", newPoint);
        return null;
    }

    private Quadrant findQuadrant(Point newPoint) {
        Quadrant found;
        if (FIRST.rangeX.inRange(newPoint.getX()) && FIRST.rangeY.inRange(newPoint.getY())) found = FIRST;
        else if (SECOND.rangeX.inRange(newPoint.getX()) && SECOND.rangeY.inRange(newPoint.getY())) found = SECOND;
        else if (THIRD.rangeX.inRange(newPoint.getX()) && THIRD.rangeY.inRange(newPoint.getY())) found = THIRD;
        else found = FOURTH;
        return found;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Point> points() {
        List<Point> points = new LinkedList<>();
        points.addAll(FIRST.points);
        points.addAll(SECOND.points);
        points.addAll(THIRD.points);
        points.addAll(FOURTH.points);
        return points;
    }

    public Point addPoint(double x, double y) {
        return addPoint(x, y, String.format("unnamed_%d", points().size()));
    }
}

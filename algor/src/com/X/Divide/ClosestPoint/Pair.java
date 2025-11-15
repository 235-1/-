package com.X.Divide.ClosestPoint;

/**
 * @author X
 * @date 2025/10/14 15:34
 */
public class Pair {
    Point1 a;   //平面中的点a
    Point1 b;   //点b
    double dist;    //a与b的距离
    public Pair(Point1 aa, Point1 bb, double dd)
    {
        a = aa;
        b = bb;
        dist = dd;
    }

    public static double dist( Point u, Point v)
    {
        double dx = u.x-v.x;
        double dy = u.y - v.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}

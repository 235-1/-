package com.X.Divide.ClosestPoint;

/**
 * @author X
 * @date 2025/10/14 15:30
 */
public class Point2 extends Point implements Comparable{
    int p; //同一点在数组x中的坐标
    public Point2(double xx, double yy, int pp)
    {
        super(xx,yy);
        p = pp;
    }

    @Override
    public int compareTo(Object x){
        double xy = ((Point2) x).y;
        if(this.y < xy) return -1;
        if(this.y == xy) return 0;
        return 1;
    }

    public boolean equals(Object x)
    {
        return this.y == ((Point2) x).y;
    }
}

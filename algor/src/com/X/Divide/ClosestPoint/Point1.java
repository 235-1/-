package com.X.Divide.ClosestPoint;

/**
 * @author X
 * @date 2025/10/14 14:39
 */
/*
* Point1 表示x上的点
* 并对点进行编号按x的大小
* */
public class Point1 extends Point implements Comparable{
    int id;     //点的编号
    public Point1(double xx, double yy, int Id) {
        super(xx, yy);
        id = Id;
    }//构造了一个点并给其编号
    //实现了compareTo方法，使得Point1对象可以根据x坐标进行排序。
    /*
    * 如果返回负数 第一个元素应该排在第二个元素之前；
    * 如果返回正数，则第一个元素应该排在第二个元素之后；
    * 如果返回0，则它们相等
    * */
    @Override
    public int compareTo(Object x) {        //这里确保了我们调用sort函数时不是比较所有的数而是只对x进行排序
        double xx = ((Point1) x).x;   //((Point1)x).x 将x强制转换为Point1类型并访问他的x属性
        if(this.x < xx) return -1;
        if(this.x == xx) return 0;
        return 1;
    }

    public boolean equals(Object x)
    {
        return this.x == ((Point1)x).x;
    }
}

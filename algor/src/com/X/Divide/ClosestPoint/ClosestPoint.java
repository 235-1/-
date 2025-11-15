package com.X.Divide.ClosestPoint;


import java.util.Arrays;

/**
 * @author X
 * @date 2025/10/14 14:11
 */
public class ClosestPoint {
    public static void testBasicCases() {
        System.out.println("=== 基础测试 ===");

        // 测试1: 简单的两个点
        Point1[] points1 = {
                new Point1(0, 0, 0),
                new Point1(1, 1, 1)
        };
        Pair result1 = cpair2(points1);
        System.out.println("测试1 - 两个点: " + result1.dist + " (期望: " + Math.sqrt(2) + ")");

        // 测试2: 三个点
        Point1[] points2 = {
                new Point1(0, 0, 0),
                new Point1(3, 0, 1),
                new Point1(1, 1, 2)
        };
        Pair result2 = cpair2(points2);
        System.out.println("测试2 - 三个点: " + result2.dist + " (期望: " + Math.sqrt(2) + ")");

        // 测试3: 四个点构成正方形
        Point1[] points3 = {
                new Point1(0.1, 0, 0),
                new Point1(1.6, 0, 1),
                new Point1(10, 1, 2),
                new Point1(1.1, 1, 3)
        };
        Pair result3 = cpair2(points3);
        System.out.println("测试3 - 正方形: " + result3.dist + " (期望: 1.0)");

        //测试多个点
        Point1[] points4 = {
                new Point1(12, 10, 0),
                new Point1(102.6, 80, 1),
                new Point1(102, 1, 2),
                new Point1(128, 3, 3),
                new Point1(92.1, 129, 4),
                new Point1(41, 22, 5),
                new Point1(120, 1, 6),
                new Point1(42.1, 1, 7),
                new Point1(32.14, 30, 8),
                new Point1(23.6, 0, 9),
                new Point1(40, 1, 10),
                new Point1(10.1, 1, 11),
                new Point1(91.1, 222, 12),
                new Point1(12.6, 98, 13),
                new Point1(10, 12, 14),
                new Point1(123.1, 11, 15),
                new Point1(0.11, 98, 16),
                new Point1(13.6, 7, 17),
                new Point1(10, 9, 18),
                new Point1(1.1, 81, 19),
                new Point1(3.1, 20, 20),
                new Point1(13.61, 320, 21),
                new Point1(1210, 12, 22),
                new Point1(12.1, 12, 23),
                new Point1(4.11, 30, 24),
                new Point1(13.6, 30, 25),
                new Point1(120, 12, 26),
                new Point1(16.1, 15, 27)

        };
        Pair result4 = cpair2(points4);
        Pair res = bruteForce(points4);
        System.out.println("暴力的结果：" + res.dist);
        System.out.println("测试4 - 多个点: " + result4.dist );
    }
    public static void main(String[] args) {
        testBasicCases();
    }

    public static Pair cpair2(Point1[]x)
    {
        if(x.length < 2) return null;
        //按照x的做坐标排序
        Arrays.sort(x);
        Point2 [] y = new Point2[x.length];
        for(int i = 0; i<x.length;i++)
        {
            //将x的数据复制给y然后在按y排序
            y[i] = new Point2(x[i].x, x[i].y, i);
        }
        Arrays.sort(y);
        Point2 [] z = new Point2[x.length]; //存储中间数据 也就是 我们 strip 内的点

        //计算最近点对
        return closestPair(x,y,z,0,x.length-1);
    }

    /*
    * 最接近点对问题的核心代码
    * 用于在点集x中寻找最近点对的分治算法。范围为从 l 到 r
    * x 为按x排序的点集
    * y 为按y排序的点集
    * z 辅助空间
    * int l 数组的左端
    * int r 数组的右端
    * 在r - l <= 3 时直接暴力求解
    * */
    public static Pair closestPair(Point1 [] x, Point2 [] y, Point2 [] z, int l, int r)
    {
        if(r - l == 1)  //两个
        {
            return new Pair(x[l],x[r],Pair.dist(x[l],x[r]));
        }
        //如果有三个枚举出来暴力求解
        if(r - l == 2)  //三个
        {
            double d1 = Pair.dist(x[l], x[l + 1]);
            double d2 = Pair.dist(x[l], x[l + 2]);
            double d3 = Pair.dist(x[l + 1], x[l + 2]);
            if (d1 <= d2 && d1 <= d3) {
                return new Pair(x[l], x[l + 1], d1);
            }
            if (d2 <= d3)    //此时d1 已经不是最小了 只需要比较d2 和 d3  谁小
            {
                return new Pair(x[l], x[l + 2], d2);

            } else    //d1 d2 都是不是最小只能是d3
            {
                return new Pair(x[l + 1], x[l + 2], d3);
            }
        }
            //大于三时分治解决
            /*
            * 在前面我们经过排序cpair2中我们经过排序消耗O(nlogn)的时间 （只排这一次，后续不再排）
            * 我们在分割时只需要将找到中点位置也就是（r - l）/ 2
            *
            * 在整个递归过程中 y 和 z 交替的变成辅助数组
            * */
            int m = (r + l) / 2;
            int f = l, g = m+1;
            //这个循环的意义在于
            //我们对y已经按y排序了，那么在遍历时我们也是按y值的大小排序
            //结果就是 点集被大致均匀的分为两部分（这也是时间上的保证） 这两部分y是按y的大小排序的
            for(int i = l; i <= r; i++)
            {
                //整数代替浮点数比较
                if(y[i].p>m)    //p值记录了按x排序后的大小关系，我们用比较p来代替直接比较x的值
                {
                    z[g++] = y[i];  // 比中间值大的放在 mid+1 到  r 之间
                }
                else
                {
                    z[f++] = y[i];  //比中间值小的放在 l 到 mid 之间
                }
            }
            //然后我们对这两部分分别求最小距离
            //
            Pair best = closestPair(x,z,y,l,m);
            Pair right = closestPair(x,z,y,m+1,r);
            if(right.dist < best.dist) best = right;
            merge(z,y,l,m,r);   //目的是将z 按 l 到 m  m+1 到  r 合并到 y 上
            //strip  划定x上 大小为 2*best.dist的范围 我们这里加了绝对值 也就是两者的距离在 best.dist内
            int k = l;
            for(int i = l; i <= r;i++)
            {

                if(Math.abs(x[m].x - y[i].x) < best.dist)
                {
                    z[k++] = y[i];  //z里的点在y上是有序的
                }
            }
            //遍历z
            for(int i = l; i < k; i++)
            {
                //从第二个点开始限定搜索 x上2d  y上d范围内的点
                for(int j = i+1;j < k && z[j].y -z[i].y < best.dist;j++)//找到这个点后面的 d范围内的点
                {
                    double dp = Pair.dist(z[i], z[j]);
                    if(dp < best.dist)
                    {
                        //p值其实记录了x点集排序后的位置，找到z[i].p 就找到了其在x点集中的索引  p是从0开始的
                        best = new Pair(x[z[i].p],x[z[j].p],dp);
                    }
                }
            }
            return best;


    }

    /*
    * 合并函数主要是按照z上的两部分，从l 到 m  m+1  到 r 比较 y值的大小进行合并
    * 相当于归并排序了 之所以不需要Onlogn的时间是因为我们已经对它分好组了
    *
    * */
    public static void merge(Point2 [] z, Point2 [] y, int l, int m, int r) {
        if (r - l < 2)   //只有一个元素
        {
            return;
        }
        int i = l, j = m + 1, k = l; //分别代表 第一个第二个子数组的起点 y数组某一段的起点
        while (i <= m && j <= r) {
            if (z[i].y <= z[j].y) {
                y[k++] = z[i];
                i++;
            } else {
                y[k++] = z[j];
                j++;
            }
        }

        //对于剩余元素追加到数组后面
        while (i <= m) {
            y[k++] = z[i];
            i++;
        }
        while (j <= r)
        {y[k++] = z[j];
        j++;
        }

    }
    // 暴力求最近点对
    public static Pair bruteForce(Point1[] points) {
        if (points == null || points.length < 2) return null;

        double minDist = Double.MAX_VALUE;
        Pair bestPair = null;

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double d = Pair.dist(points[i], points[j]);
                if (d < minDist) {
                    minDist = d;
                    bestPair = new Pair(points[i], points[j], d);
                }
            }
        }
        return bestPair;
    }
}


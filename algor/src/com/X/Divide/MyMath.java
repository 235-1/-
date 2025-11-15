package com.X.Divide;

/**
 * @author X
 * @date 2025/10/9 21:00
 */
public class MyMath {
    /*
    * 求x, y, z三者中最大的
    * */
    public static int max(int x, int y, int z)
    {
        return Math.max(x,Math.max(y,z));
    }

    /*
    * 交换数组 索引 i 上和索引 j 上的2元素
    * */
    public static void swap(int [] a, int i, int j)
    {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /*
    * 冒泡排序 为了找到 p~r之间第k小的元素
    * 从 p 排序到 r
    * */
    public static int bubbleSort(int [] a, int p, int r,int k)
    {
        //两层循环，外层控制次数 内层控制元素 不断比较 j和j+1
        int l = r - p + 1;
        for(int i = 0; i < l; i++)
        {
            for(int j =p; j < l-i-1+p; j++) // 每次排好末尾的一个元素因此每次遍历小 i 次
            {
                if(a[j] > a[j+1])
                {
                    swap(a,j,j+1);
                }
            }
        }
        return a[p+k-1];
    }
}

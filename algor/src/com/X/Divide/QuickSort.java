package com.X.Divide;

public class QuickSort {
    /*
    * com.X.Divide.QuickSort
    * 对于一个数组a 准备两个指针
    * l:表示数组第一个元素的索引
    * h:表示数组最后一个元素的索引
    * 从数组第一个位置取出元素用临时变量temp存起来（也可以是最后一个位置)
    * 如果取第一个位置的元素就先比较h和temp反之先比较l和temp
    * 比temp大则元素不动 h-- 继续比较h
    * 比tempt小元素移动到l的位置 l++ 转而比较l
    * 直到 l == h 此时将 temp放到 l（h） 的位置 用 point记录这个位置
    * 在point 两边接着执行相同
    * */

    public static void main(String[] args)
    {
        int [] a = {1,122,1,10, 8, 1, 11, 22, 3, 12, 4, 5, 6, 1,101};
        quickSort(a, 0, a.length-1);
        for (int c : a)
        {
            System.out.println(c);
        }

    }

    /*
    * 快速排序：
    * int [] a 待排序数组
    * int l 数组的起始位置索引
    * int h 数组的终止位置索引
    * */
    public static void quickSort(int [] a, int l, int h)
    {
        if (l >= h) //无需做任何操作
        {
            return;
        }
        int point = point(a, l, h); //找到每次元素放置位置
        quickSort(a, l, point-1);   //对这个位置两边的元素接着进行快速排序
        quickSort(a, point+1, h);
    }
    /*
    * 找出每次元素应该放置的位置
    * */
    public static int point(int [] a, int l, int h)
    {
        if(l == h)
        {
            return l;
        }
        int temp = a[l];
        while (l < h)
        {
            while(h > l && a[h] >= temp)
            {
                h --;
            }
            a[l] = a[h];

            while (l < h && a[l] <= temp)
            {
                l ++;
            }
            a[h] = a[l];

        }
            a[l] = temp;
            return l;
    }

}

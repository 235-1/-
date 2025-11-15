package com.X.Divide.HeapSort;

import com.X.Divide.MyMath;

public class HeapSort {
    /*
    * heapSort:
    * 分为两部分：
    * 1、堆化
    * 2、从堆中依次将根节点位置的元素与最后一个元素交换位置并移除堆（）
    * */
    public static void main(String[] args) {
        int [] tree =  {1,122,1,10, 8, 1, 11, 22, 3, 12, 4, 5, 6, 1,101};
        heapSort(tree,tree.length);
        for(int c: tree)
        {
            System.out.println(c);
        }

    }
    /*
    * int [] tree 表示待排序的数组
    * int n 表示共n个节点
    * 我们每次交换根节点与最后一个元素的位置然后对根节点进行堆化
    * */
    public static int [] heapSort(int [] tree, int n)
    {
        if (n == 1) //如果只有一个节点直接退出
        {
            return tree;
        }
        //多个节点先进行堆化
        build_heap(tree, n);
        int last = n - 1; //最后一个叶子的索引 我们要与0交换的节点
        for(int i = last; i > 0; i--)
        {
            MyMath.swap(tree, 0, i);
            heapify(tree,i ,0); //对根节点进行heapify
        }
        return tree;
    }
    /*
    * heapify：
    * 对给定节点进行堆化，这里构造大根堆
    * int [] tree 要进行堆化的数组
    * int n 元素个数
    * int i
    * */
    public static void heapify(int [] tree, int n, int i)
    {

        int s1 = i * 2 + 1; //要进行堆化节点的左孩子
        int s2 = i * 2 + 2; //右孩子
        //进行堆化就是将其与左右孩子对比选出最大的
        int max = i; //先假设最大的是根节点
        if(s1 < n && tree[max] < tree[s1]) //有左孩子 且 如果比左孩子小
        {
            max = s1;
        }
        if(s2 < n && tree[max] < tree[s2]) // 有右孩子 且 再将左孩子和父节点中大的一个与右孩子比较选出三者中最大的那个
        {
            max = s2;
        }
        if(max != i) // 最大值变了
        {
            MyMath.swap(tree, max, i); // 交换最大节点与进行heapify的节点
            heapify(tree, n ,max);  //我们要继续对其下面进行heapify
        }

    }


    /*
    * 构造一个堆
    * 从下往上
    * int [] tree ：要构造堆的数组
    * int n：元素个数
    * */
    public static void build_heap(int [] tree, int n)
    {
        if(n == 1)
        {
            return;
        }
        int last = n-1; //最后一个节点；
        int parent = (last - 1) / 2; //最后一个节点父节点，我们从这里进行heapify
        for(int i = parent; i >= 0; i--)
        {
            heapify(tree,n,i);  //对这个节点进行heapify
        }
    }


}
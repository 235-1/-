package com.X.Divide;

public class BinarySearch {
    public static void main(String[] args) {
        int [] a = {2, 15, 16, 20, 21, 32, 45, 60 ,121, 123, 130};
        System.out.println(BinarySearchN(a, 451, 0, a.length - 1));
        System.out.println(BinarySearchR(a, 0, a.length - 1, 43));

    }
    /*
    * int [] a 一个数组 从小到大排序
    * int target 目标数
    * int l 数组的最低端
    * int h 数组的最后一个索引
    * */
    public static int BinarySearchN(int[] a, int target, int l, int h)
    {
        while(l <= h)
        {

            int mid = (h + l) / 2;
            if(a[mid] < target)
            {
                l = mid+1;

            }
            if(a[mid] > target)
            {
                h = mid - 1;
            }
            if (a[mid] == target)
            {
                return mid;
            }
        }
        return -1;
    }

    public static int BinarySearchR(int []a , int l, int h, int target)
    {
        int mid = (l + h) / 2;
        if(l >= h)
        {
            if(a[mid] == target)
            {
                return mid;
            }
                return -1;
        }
        if(a[mid] > target)
        {
            return BinarySearchR(a, l, mid-1, target);
        }
        if(a[mid] < target)
        {
            return BinarySearchR(a, mid+1, h, target);
        }
        if(a[mid] == target)
        {
            return mid;
        }

        return -1;
    }
}

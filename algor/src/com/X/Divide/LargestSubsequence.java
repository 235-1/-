package com.X.Divide;

public class LargestSubsequence {
    /*
    * 最大子序列合
    * 采用分治的思想（将数组分为两部分别求这两部分的最大）
    * 最大子序列有三种情况（不需要判断，只需要求出然后比较这三种情况的大小）
    * 1、最大子序列在左半部分
    * 递归求解左半数组的最大子序列和
    * 2、最大子序列在右半部
    * 递归求解右半数组的最大子序列和
    * 3、最大子序列跨越中点
    * 包含左半部分末尾连续一段 + 右半部分开头连续一段
    * 这部分用线性扫描求解（从中点向左、向右各扫一次）
    * 通过不断地分治
    * 最终都变为两种情况
    * 1、剩一个元素直接输出
    * 2、横跨两个子数组
    * */
    public static void main(String[] args) {
        int [] a = {-1, -2, 10, -11, 8,1, -2,12, 1, -2, 3};
        System.out.println(largestSubsequence(a, 0, a.length - 1));
        System.out.println(largestSubsequenceDp(a));

    }

    /*
    * int [] a : 待求解的数组
    * int size : 元素个数
    * */
    public static int largestSubsequence(int [] a, int l, int h)
    {
        if(l >= h)
        {
            return a[l];
        }
        int mid = (l + h) / 2 ;
        int left_max = largestSubsequence(a, l, mid);
        int right_max = largestSubsequence(a, mid+1, h);
        int crossMid_max = crossMid(a, l, mid, h);
        return MyMath.max(left_max, right_max, crossMid_max); //输出三者中最大的
    }

    /*
    * int [] a : 待求解数组
    * int mid : 中点位置
    * 我们从中点向左向右求最大，求出后求合
    * */
    public static int crossMid(int [] a, int l, int mid, int r)
    {
        if(l >= r)
        {
            return a[l];
        }
        int left_max = a[mid]; //先假设最大的子序列合就是mid 位置上的元素（避免其他都是0或者负数的情况）
        int right_max = a[mid +1];
        int count = 0;
        //遍历左边
        for(int i = mid; i>=l; i--)
        {
            count += a[i];
            if(count > left_max)
            {
                left_max = count;
            }
        }
        //遍历右边
        count = 0;
        for(int i = mid + 1; i <= r; i++)
        {
            count += a[i];
            if(count > right_max)
            {
                right_max = count;
            }
        }
        return right_max+left_max;
    }

    /*
    * DP
    * int []sum 表示 从0到i的连续子序列和的最大值
    * sum的大小为 sum[n+1]  n为a的长度
    * */
    public static int largestSubsequenceDp(int [] a)
    {
        int n = a.length;
        int current;
        if (n == 1)
        {
            current = a[0];
        }
        //多个元素
        int max = a[0];
        current = a[0];
        for(int i = 1; i <= n-1; i++)
        {
            current = Math.max(current+a[i], a[i]);    //要(加上a[i])或者不要(从a[i]重新开始计算)
           max = Math.max(current, max); //找两者的最大值
        }
        return max;

    }

}

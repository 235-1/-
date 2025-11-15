package com.X.Greed.Seletor;

import java.util.ArrayList;

/**
 * @author X
 * @date 2025/11/14 19:46
 */
/*
* 基于归并排序实现对于活动安排算法按索引排序
* 递归
* */
public class MySort {

    public static int[] mergerSortIndex(int[] f, int l, int r,int[] d)
    {

        if (l >= r)
        {
            return d;
        }
        int m = (l+r)/2;
        mergerSortIndex(f,l,m,d);
        mergerSortIndex(f,m+1,r,d);
        merger(f,l,m,r,d);
        return d;
    }

    /*
    * 核心 对d排序按照 f的大小
    *
    * */
    public static int[] merger(int[] f, int l, int m, int r, int[] d)
    {
        int i = l;  //这里存的是d的索引
        int j = m+1;
        ArrayList<Integer> temp = new ArrayList<>();
        while (i <= m && j <= r)
        {
            if (f[d[i]] < f[d[j]])
            {
                temp.add(d[i]);
                i++;
            }
            else
            {
                temp.add(d[j]);
                j++;
            }
        }

        while (i <= m)
        {
            temp.add(d[i]);
            i++;
        }
        while (j <= r)
        {
            temp.add(d[j]);
            j++;
        }
        //最后将temp复制到d上
        for(int k = 0; k < temp.size(); k++)
        {
            d[l+k] = temp.get(k);
        }
        return d;
    }

    public static void main(String[] args) {
        int[] f = {4, 2, 3, 1, 5, 6, 8};
        int []d = new int[f.length];
        for (int i =0; i < f.length; i++)
        {
            d[i] = i;
        }
        int[] c = mergerSortIndex(f,0,f.length-1,d);
        for(int l : d)
        {
            System.out.println(l+" ");
        }
    }
}

package com.X.Greed.Seletor;

/**
 * @author X
 * @date 2025/11/14 19:40
 */

/*
* 活动选择问题贪心算法
* 核心思想：
* 将活动按结束时间排序（对索引排序，不打乱原来的结束时间数组），结束时间早的排前面（给后续的活动留下更多的时间）
* 优先选择结束时间最早的活动
* 然后比较下一个活动的开始时间和上一个活动的结束时间来决定要不要选这个活动
* */
public class GreedSeletor {

    /*
    * 核心方法：
    * s: 开始时间数组
    * f: 结束时间数组
    * a: 选这个活动a[i] = true 否则为false
    * */
    public static void seletor(int[] s, int[] f, Boolean[] a)
    {
        //先对开始时间进行排序
        int [] d= new int[f.length];
        for(int i = 0; i < f.length; i++)
        {
            d[i] = i;
        }
        d = MySort.mergerSortIndex(f,0,f.length-1,d);

        //我们每次都选择结束时间最早的活动
        int j = 0; //第一个活动一定包含在某一个最优解中
        a[d[0]] = true;
        int count = 1;
        for (int i = 1; i < f.length; i++)
        {
            if(s[d[i]] >= f[d[j]])
            {
                a[d[i]] = true;
                j = i;
                count++;
            }
            else
            {
                a[d[i]] = false;
            }
        }

        System.out.println(count);
        for (int i = 0 ; i < a.length; i++)
        {
            if(a[i])
            {
                System.out.println(s[i]+":"+f[i]);
            }
        }
    }

    public static void main(String[] args) {
        // 原始索引0-11，对应12个活动的开始时间
        int[] s = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 14};
        // 对应12个活动的结束时间（与s一一对应）
        int[] f = {3, 5, 4, 6, 8, 7, 9, 11, 10, 13, 15, 16};
        Boolean[] a = new Boolean[f.length];
        seletor(s,f,a);

    }
}

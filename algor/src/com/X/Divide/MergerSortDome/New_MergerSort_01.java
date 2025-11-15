package com.X.Divide.MergerSortDome;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * @author X
 * @date 2025/11/15 8:08
 */
public class New_MergerSort_01 {

    public static int[] mergerSort(int [] a, int l, int r)
    {
        int m = (l+r)/2;//我们将数组分成了左右两个，再对左右两个分别mergerSort

        if (l >= r)
        {
            return a;   //只有一个直接返回
        }

        mergerSort(a,l,m);
        mergerSort(a,m+1,r);
        merger(a,l,m,r);//实现数组的按大小合并从  l~m 和 (m+1)~r
        return a;
    }

    public static int[] merger(int[] a, int l, int m, int r)
    {
        if(l >= r)
        {
            return a;
        }
        /*
        * 如果大于一个 创建一个临时数组存放两个子数组归并的中间结果 结束后再复制到a上
        *
        * */
        int i = l;
        int j = m+1;
        ArrayList<Integer> temp = new ArrayList<>();
        while(i <= m && j <= r)  //任何一个数组遍历完就结束，剩下内容直接追加到temp后面
        {
            if (a[i] <= a[j])
            {
                temp.add(a[i]);
                i++;
            }
            else
            {
                temp.add(a[j]);
                j++;
            }
        }

        //合并剩余
        while (i <= m)
        {
            temp.add(a[i]);
            i++;
        }
        while (j <= r)
        {
            temp.add(a[j]);
            j++;
        }

        //结果放到a中
        /*
        * 这里的k指的是temp的索引，实际a的索引是从 l 开始的因此要 l+k
        * */
        for (int k = 0; k < temp.size(); k++)
        {
            a[k+l] = temp.get(k);
        }
        return a;
    }


    /*
    * 另一种思路：
    * 既然我们最终都要分成一个一个的那我们不如最开始就从一个一个开始排
    * 然后从有序的两个两个  四个四个
    * 如果有剩余, 例如我们排相邻的四个一组的时候
    * 两个种情况：
    * 1、剩下的大于四个
    *   那就对前四个和剩下的进行简单的归并
    * 2、剩下的不足四个
    *   那这几个一定是有序的   因为前面的步骤已经把四个一组的子数组排好了
    * */

    static int[] temp;
    public static int[] mergerSortR(int[] a)
    {
        int s = 1;
        temp = new int[a.length];
        while (s < a.length)
        {
            /*
            * 本质上就是 a->temp  然后 temp->a 来回倒
            * 最后再 temp -> a 确保正确答案在a中
            * 即使最后一次 temp->a上的s大于了a.length也没关系 mergerPass中的条件会控制
            * */
            mergerPass(a,temp,s);   //将元素从 a 以 s 为一组 合并到 temp
            s += s;
            mergerPass(temp,a,s);
            s += s;
        }
        return a;
    }

    /*
    * 实现了将两个s段的子数组合并成一个
    *
    * */
    public static int[] mergerPass(int[] a, int[] temp, int s)
    {
        int i = 0; //第一个子数组的起始

        while (i < a.length-2*s)    //如果剩下不足2*s 另外判断
        {//在范围内对每段子数组归并
            mergerR(a,i,i+s-1,i+2*s-1,temp);
            i += 2*s;   //i每次跨越两个子数组
        }

        if (a.length - i <= s)  // 不足s个直接追加
        {
            while (i < a.length)
            {
                temp[i] = a[i];
                i+=1;
            }
        }
        else //大于s个 对 i ~ i+s-1  和 i+s ~ a.length-1 合并
        {
            mergerR(a,i,i+s-1,a.length-1,temp);
        }
        return temp;
    }


    public static void mergerR(int[] a, int l, int m, int r, int[] temp)
    {
        if(l >= r)
        {
            return ;
        }
        int i = l;
        int j = m+1;
        int k = l;
        while (i <= m && j <= r)
        {
            if (a[i] <= a[j])
            {
                temp[k++] = a[i];
                i++;
            }
            else
            {
                temp[k++] = a[j];
                j++;
            }
        }

        while (i <= m)
        {
            temp[k++] = a[i];
            i++;
        }
        while (j <= r)
        {
            temp[k++] = a[j];
            j++;
        }
    }

    public static void main(String[] args) {
        int [] arr = {111, 10, 2, 1, 14, 21, 3, 6, 1, 121, 10, 1098, 1231, 0 };
        int [] a = mergerSort(arr, 0, arr.length-1);
        for (int c: a)
        {
            System.out.print(c+" ");//0 1 1 2 3 6 10 10 14 21 111 121 1098 1231
        }

        int [] arr1 = {111, 10, 2, 1, 14, 21, 3, 6, 1, 121, 10, 1098, 1231, 0 };
        System.out.println();
        System.out.println("迭代");
        int[] b = mergerSortR(a);
        for(int k:b)
        {
            System.out.print(k+" ");
        }
    }
}

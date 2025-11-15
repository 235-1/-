package com.X.Divide.MergerSortDome;

import java.util.ArrayList;

public class MergerSort {

    public static void main(String[] args) {
        int [] arr = {111, 10, 2, 1, 14, 21, 3, 6, 1, 121, 10, 1098, 1231, 0 };
        int [] a = mergerSort(arr, 0, arr.length-1);
        for(int c:a)
        {
            System.out.print(c+" ");
        }
        System.out.println("迭代");
        int [] arr1 = {111, 10, 2, 1, 14, 21, 3, 6, 1, 121, 10 };
        int [] b = mergerSortR(arr1,arr1.length);
        System.out.println(b.toString());
        for(int k: b)
        {
            System.out.print(k+" ");
        }
    }

    /*
    * 归并 将需要合并的数组先经过比较赋值给tempt 然后在回到a的相应位置
    * int [] a 需要排序的数组
    * int l 需要排序子数组的最左边的坐标 可以认为是需要排序的子数组的开始位置
    * int m 需要排序的部分的中间位置 可以认为是第一个子数组的结尾  那么 m+1 就是第二个子数组的开始位置
    * int r 需要排序的最右边索引 可以认为是第二个子数组的结尾
    * */
    public static int [] merger(int [] a, int l, int m, int r)
    {
        int i = l;
        int j = m + 1;
        ArrayList<Integer> temp = new ArrayList<>();
        while(i <= m && j <= r)
        {
            if(a[i] < a[j])
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

        while(i <= m )
        {
            temp.add(a[i]);
            i++;
        }
        while(j <= r)
        {
            temp.add(a[j]);
            j++;
        }
        //将temp赋值到a上
        for(int le = 0; le < temp.size(); le++)
        {
            a[l+le] = temp.get(le);
        }
        return a;
    }
    /*
    * 归并排序的主函数
    * 如果l < r 说明元素个数大于1个
    * 此时将数组一分为二 两部分再分别进行归并排序
    * */
    public static int [] mergerSort(int [] a, int l, int r) {

        if (l < r) {

            int mid = (l + r) / 2;
            mergerSort(a, l, mid);
            mergerSort(a, mid + 1, r);
            merger(a, l, mid, r);
        }
        return a;
    }







//-------------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------------



    /*
    * 归并排序的优化，可以采用迭代代替递归
    * 核心思想：
    *   每次对相邻的s个元素数组进行排序变为有序数组，下次对相邻的2s的数组排序使其有序 知道全部有序
    *   消除了递归对于栈的调用
    * 主函数
    * int [] a 排序数组
    * int n 数组元素个数
    * */

    static int []b ;   //用于临时存放排序的数据
    static int k ;  //交换时用于记录需要放置的位置
    public static int [] mergerSortR(int []a, int n)
    {

        b = new int[n];
        int s = 1;  //第一次对相邻的1个数组进行合并
        if(n <= 1)
        {
            return a;
        }
        while(s < a.length)
        {
            mergerPass(a, b, s);
            s += s;     //第一次s+=s后 就不满足循环条件后续的步骤会排除
            mergerPass(b, a, s);    //不用但心当排序完之后 b有序了而a无序， 因为先 a->b之后又执行了一次b->a
            s += s;

        }
        return a;
    }
    /*
    * 迭代归并的核心
    * 将 x中相邻的s个元素排大小合并到一起放入y中
    * s为合并的子数组的大小
    * */
    public static void mergerPass(int [] x, int [] y, int s)
    {
        int i = 0; //第一个子数组的位置
        while(i < x.length - 2*s)   //剩下不足两个s的时候停下
        {
            mergerR(x, y, i, i+s-1, i+2*s-1);
            i += 2*s;
        }   //结束时不足2*s
        //两种情况，1、比s大那么此时还是无序的还是要merger 2、比s小此时有序直接赋值到y上
        if(i < x.length - s)
        {
            mergerR(x, y, i, i+s-1, x.length-1);
        }
        else
        {
            for(int j = i; j < x.length; j++)
            {
                y[j] = x[j];
            }
        }
    }
    /*
    * 归并函数
    * 将x数组的l到m m到r之间的元素对比后放入y 再复制到x上
    * k表示需要放数组的位置
    * */

    public static void mergerR(int [] x, int []y, int l, int m, int r)
    {
        if (l >= r) //不需要进行操作
        {
            return;
        }
        int i = l;  //第一个数组的元素开始
        int j = m + 1;  //第二个数组的开始位置
        k = l; //放元素的位置
        while (i < m+1 && j < r+1)
        {
            if (x[i] < x[j])
            {
                y[k] = x[i];
                i++;
                k++;
            }
            else
            {
                y[k] = x[j];
                j++;
                k++;
            }
        }

        while (i < m+1)
        {
            y[k] = x[i];
            i++;
            k++;
        }
        while (j < r+1)
        {
            y[k] = x[j];
            j++;
            k++;
        }
    }
}

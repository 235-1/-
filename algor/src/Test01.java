import java.util.ArrayList;

public class Test01 {
    public static void main(String[] args) {
        int [] a = {21112, 101, 985, 123, 2, 1, 1098, 2, 1, 6, 8, 10, 0, 100};
        int [] arr = mergerSort(a,0, a.length-1);
        for(int c : arr)
        {
            System.out.println(c+" ");
        }
    }
/*
* 归并排序
* int [] a 待排序数组
* int l 数组的左端 可以认为其实是第一段数组的开始
* int r 数组的最右端 第二段数组的结尾
* */
    public static int [] mergerSort(int [] a, int l, int r)
    {
        if(l < r)
        {
            int mid = (r + l) / 2;  //认为是第一段数组的结束位置   mid+1 认为是第二段数组的起始位置
            //从中点分开左右分别再进行归并排序
            mergerSort(a, l,mid);
            mergerSort(a, mid+1, r);
            //归并直到只剩一个元素时执行 归并对两段数组进行合并
            merger(a, l, mid, r);
        }
        return a;
    }
    /*
    * 归并：将一个数组的给定两部分按大小合并在一起
    * int [] a 等待归并的数组  不用两个数组表示 用索引分为两段
    * int l 数组的左端 可以认为其实是第一段数组的开始
    * int mid 第一段数组的结尾 mid+1 第二段数组的开始
    * int r 数组的最右端 第二段数组的结尾
    * */

    public static void merger(int [] a, int l, int mid, int r)
    {
        if(l >= r)
        {
            return;
        }
        ArrayList<Integer> temp = new ArrayList<>();
        int i = l;  //第一段的开始 不直接改 l
        int j = mid+1;  //  第二段的开始
        while(i <= mid && j <= r)
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

        //对两个数组剩余变量进行追加
        while (i <= mid)
        {
            temp.add(a[i]);
            i++;
        }
        while (j <= r)
        {
            temp.add(a[j]);
            j++;
        }
        //将临时数组里有序的元素复制到原数组中
        for(int k = 0; k < temp.size(); k++ )
        {
            //我们是从a的l开始的排序 自然也要从 l 开始复制进去
            a[l + k] = temp.get(k);
        }

    }


}

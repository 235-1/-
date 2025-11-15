package com.X.Divide;

import java.util.Random;

/**
 * @author X
 * @date 2025/10/9 20:59
 */
public class LinearTimeSelect {
    static int[] a = {24, 12, 2, 18, 8, 34, 22, 6, 40, 30, 32, 16, 36, 14, 8, 38, 10, 28, 26, 20};

    //1,2,3,10,11,12,12,13,21,22
    public static void main(String[] args) {
        System.out.println(randomizedSelect(0, a.length - 1, 14));
        System.out.println(select(a, 0, a.length - 1, 14));
    }


    public static void swap(int[] a, int x, int y) {
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }

    /*
     * 从 p  到  r 之间选出一个 作为基准
     * int p 表示某段数组的首索引
     * int r 表示尾索引
     * */
    public static int randomizedPartation(int p, int r) {
        Random random = new Random();
        int i = random.nextInt(r-p+1)+p;
//                (int) (p + Math.random() * (r - p + 1));    //随机出p-r之间的一个整数
        swap(a, i, p); //将其与p位置上的元素换位置作为基准元素
        return Partation(p, r); //找出这个元素正确的位置
    }

    /*
     * 找到某个数在数组中的正确位置
     *
     * int p 表示某段数组的首索引  也是基准元素
     * int r 表示尾索引
     *
     * 不断循环比较 i（p） 和 j（r） 位置与 p 位置元素（基准元素）
     * 外部循环 true
     * 内部先循环 i
     * 直到找到一个 a[i] 大于等于 a[p] 或者 i > j（也许基准元素是最大的防止越界）
     * 终止循环 i 的循环
     * 再内部循环 j
     * 直到找到一个 a[j] 小于等于 a[p] （这里不用再 j >= i ）因为先遍历的 i 那么一定会有一个元素小于a[p]
     * 终止 j 的循环
     * 此时判断
     * 如果 i >= j  表明已经遍历完一边数组的了 退出外部循环
     * 否则  交换 i 和 j 上的元素
     * 外部循环结束
     * 此时对基准元素进行归位
     *
     * */
    public static int Partation(int p, int r) {
        if (p >= r) {
            return p;
        }
        int i = p;  //
        int j = r;
        int temp = a[p];
        //分区 基于左右两个指针
        while (i < j) {
            //先判断高位  方便替换a[p]
            while (j > i && a[j] >= temp) {
                j--;
            }
            a[i] = a[j];
            //都用等号避免失衡
            while (i < j && a[i] <= temp) {
                i++;
            }
            a[j] = a[i];
        }
        a[i] = temp;
        return i;
    }

    /*
     * 找到随机数的这个位置后左边的都比这个数小右边都比这个数大
     * 那么 我们只需要看左边数的个数
     * 例如我们要找第3小的数
     * 左边（p, i）包括p和i刚好 3 个 那么第3小的数一定在里面 对它再次 randomizedSelect(p, i, k)
     * 反之一定在右边（i+1, r)
     * 但这时我们要找的不再是第三小了 因为此时在右边找
     * 例如左边只有两个数那么我们就要找右边的第一小  randomizedSelect(p, i, i-p+1)
     * int p 表示某段数组的首索引
     * int r 表示尾索引
     * int k 第k小的元素
     * */
    public static int randomizedSelect(int p, int r, int k) {
        if (p >= r) {
            return a[p];
        }
        int i = randomizedPartation(p, r); // 先分区
        //对于两个分区 我们不需要都递归 先判断左半区的元素个数
        int j = i - p + 1;  //左半区元素个数
        if (k <= j) {
            return randomizedSelect(p, i, k);
        }
        return randomizedSelect(i + 1, r, k - j);
    }

    /*
    * TODO: 线性时间选择问题优化 使其最坏情况下的事件复杂度依然为O(n)
    * 将数据分为每5个一组  分成了 n/5 组
    * 每一组取中位数，将中位数与第一组的元素交换位置（不必关心元素的顺序，它不会影响中位数的中位数）
    * 经此我们可以保证：
    * 至少有至少（2n/10）个元素小于/大于中位数
    * 那么每次都能丢弃 n/5
    * 再在每组的中位数中接着取出中位数（我们要找的就是中位数的中位数）
    *
    * int [] a : 待求解数组
    * int p : 数组的最左端索引
    * int r : 数组的最右端索引
    * int k : 求第k小的元素
    * */
    public static int select(int []a, int p, int r, int k)
    {
        //先判断，如果数组内都没有5个数组自然不需要分组 直接冒泡排序求出结果
        if(r-p+1 <5 )
        {
            int aws = MyMath.bubbleSort(a,p,r,k);
            return aws;
        }
        //大于5就分组，对每组进行冒泡排序找到中位数 与 前面的元素交换位置
        //第 i 组的起始位置应该是 p + 5*i   结束位置应该是 p + 5*i +4
        for(int i = 0; i < (r - p -4)/5; i++)
        {
            int m = MyMath.bubbleSort(a,p+5*i,p+5*i+4,3);   //5个一组中位数应该是第3小的
            //交换m与第一组相应位置的元素
            MyMath.swap(a,3,i);
        }//从这里元素前面的都是中位数，在这些中位数中在找出一个中位数的中位数(递归)
        int x = select(a,p,(r-p-4)/5,(r-p+6)/10);
        //分组寻找到中位数的正确位置，然后判断左右元素个数 并递归
        int m = partition(a, p, r, x);
        int j = m-p+1; // p 到 i 之间有多少元素
        if(j < k)
        {
            return select(a,m+1, r, k-j);
        }
        return select(a, p, m, k);
    }
    /*
    * 对数组进行分组
    * int [] a : 待分组数组
    * int p : 数组左端
    * int r : 数组右端
    * int x : 基准元素 （比其小的放在左边，比它大的放在右边）
    *
    * */
    public static int partition(int [] a, int p, int r, int x)
    {
        //先将基准元素放到p的位置上
        int ind = 0;
        for(int i = p ; i <= r; i ++)
        {
            if( a[i] == x)
            {
                ind = i;
            }
        }
        swap(a,ind,p);


        if(r - p <= 1)
        {
            return p;
        }
        int i = p, j = r;
        //基于两个指针的分组
        while(i < j)
        {
            while(j > i && a[j] >= x)
            {
                j--;
            }
            a[i] = a[j];
            while (i < j && a[i] <= x)
            {
                i++;
            }
        }
        a[j] = a[i];
        a[i] = x;
        return i;
    }
}

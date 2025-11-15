package com.X.Dp;

/**
 * @author X
 * @date 2025/11/1 17:24
 */

/*
* 图像压缩算法
* 1、需要记录下每个图像压缩之后需要多大的位数
* 用 b[i]来表示
* 2、需要表示最优解
* 用s[i]来表示 前i位的最优解
* l[i]表示 i个元素最优解的最后一段的长度    那么前一段的长度就位于l[n-l[i]]
* */
public class GraphCompress {
    static final int lmax = 256; //规定一段最大不超过256
    static final int header = 11; //头信息 11位
    static int m;


    /*
    * 求某一个数的二进制表示位数
    * */
    public static int length(int num)
    {
        int k = 1;
        num = num/2;
        while (num > 0)
        {
            num = num/2;
            k++;
        }
        return k;
    }


    /*
    * p 记录像素点 从 1 开始
    *
    * */
    public static void graphCompress(int[] p, int[] b, int[] s, int[] l)
    {
        int n = p.length-1;
        s[0] = 0; //默认 0没有像素
      for(int i = 1; i <= n; i++) //遍历像素点
      {
          //变量初始化  相等于处理 j = 1 的情况
          b[i] = length(p[i]);
          int bmax = b[i];
          s[i] = s[i-1] + bmax;
          l[i] = 1;

          for (int j = 2; j <= i && j <= lmax; j++)  //枚举每段的段长找到以 i 结尾的最优解
          {
              if(bmax < b[i-j+1])//因为每次加入一个元素我们只需要看这个元素与之前的最大值的关系并维持最大值
              {
                  bmax = b[i-j+1];
              }
              if(s[i] > s[i-j]+j*bmax)
              {
                  s[i] = s[i-j]+j*bmax; //维持最优解
                  l[i] = j;
              }
          }
          s[i] += header; //加上头信息
      }
        System.out.println("最优压缩空间是:"+s[n]);
      outPut(s,b,l);
    }


    //回溯最优解
    /*
     * l[n]中记录了 n 个元素中最优解的最后一段的 长度
     * 那么 n - l[n] 是倒数第二段结尾
     * 利用s[m++] 来记录下前一段的结尾
     * */
    public static void traceBack(int [] l, int n, int [] s)
    {
        if(n == 0)
        {
            return;
        }
        traceBack(l, n - l[n], s);
        s[m++] = n-l[n];//上一段结尾 没有记录第m段的结尾
    }

    /*
    * 输出每段的长度和每个像素所用位数
    * */
    public static void outPut(int [] s, int [] b, int [] l)
    {
        int n = s.length-1;
        System.out.println("最优压缩空间是："+s[n]);
        m = 0;
        traceBack(l,n,s);   //回溯每段的起始位置
        s[m] = n;   //m 最后又加了 一次 因此 可以 直接对m赋值 表示 最后一段的结尾 在哪里
        System.out.println("共分" + m + "段"); // 本来是从0开始 但 m最后又加1次所以等于分段
        for (int j = 1; j <= m; j++)
        {

            //分段内元素数量   s[0]记录了第一段的前一段的段尾部  s[1] 记录1段的段尾部
            //而l[i]恰好表示以i结尾 的 最优解的最后一段的长度
            l[j] = l[s[j]];
            int start = s[j-1]+1;
            int end = s[j];
            int max = 0;
            for(int i = start; i <= end; i++)
            {
                max = Math.max(max, b[i]); //占位数
            }
            b[j] = max;

        }
        for (int j = 1; j <= m; j++)
        {
            System.out.println(l[j]+","+b[j]);
        }
    }





    public static void main(String[] args) {
//        int[] p = {0, 21, 22, 67, 89, 252, 123, 122, 90, 12, 14, 1};
        int [] p = {0,16,16,255,12,14,255, 11, 14, 15, 13,1, 2, 3, 111, 12, 13};
        int[] b = new int[p.length];
        int[] s = new int[p.length];
        int[] l = new int[p.length];
        graphCompress(p, b, s, l);
    }
}

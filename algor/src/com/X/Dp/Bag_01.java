package com.X.Dp;

/**
 * @author X
 * @date 2025/10/28 21:10
 */

/*
* 01背包问题
* */
public class Bag_01 {

    /*
    * dp解决
    * dp数组：int[][] m  m[i][j] : 表示 0 ~ i-1 件物品 背包容量为 j 时最大的拿取方式
    * int c 背包总容量
    * 物品下标从1开始
    * */
    public static void knapsack(int [] v, int [] w, int c, int [][] m)
    {
        int n = w.length-1; //表示物品的下标 从 1 到 n
        //对数组进行初始化
        int Maxj = Math.min(c, w[n] - 1);   //将 j 分为小于物品重量的一部分 = 0  大于物品重量的一部分 = v[n]
        for (int j = 0; j <= Maxj; j++)
        {
            m[n][j] = 0;
        }
        for (int j = w[n]; j <= c; j++)
        {
            m[n][j] = v[n];
        }
        //----------初始化完成
        //从n-1 开始向上遍历 因为n 已经初始化过了
        for(int i = n-1; i > 0; i--)
        {
            //同样将 i 分为 小于当前物品重量 和 大于当前物品重量的两部分
            Maxj = Math.min(c, w[i]-1);
            for(int j = 0; j <= Maxj; j++)  //只有一种可能继承前一个
            {
                m[i][j] = m[i+1][j];
            }
            for (int j = w[i]; j <= c; j++) // 两种可能
            {
                m[i][j] = Math.max(m[i+1][j], m[i+1][j-w[i]] + v[i]);
            }
            } //为什么到1就停了呢 ？ 因为我们需要一个数来记录最大值那就是m[1][c]
//        m[1][c] = m[2][c]; //假设不选1的话是这样
//        if(c > w[1])
//        {
//            m[1][c] = Math.max(m[1][c],m[2][c-w[1]]+v[1]);
//        }
        System.out.println(m[1][c]);
        for (int[] l: m)
        {
            for (int k: l)
            {
                System.out.print(k+" ");
            }
            System.out.println(" ");

        }
        traceBack(m,w,c);
    }
    /*
    * 我们还需要还原选择的物品
    * 从 m 矩阵的 m[1][c] 开始回溯
    * m[i][j] == m[i+1][j] 等于就是没选 不等于就是选了
    * 选了 i-1 j-w[i]  没选 i = i-1 j = j
    *
    * */
    public static void traceBack(int [][] m, int [] w, int c)
    {
        int n = w.length - 1; // 物品从1开始
        int j = c;
        System.out.print("选中的物品编号: ");
        for (int i = 1; i < n; i++) {
            if (m[i][j] != m[i + 1][j]) {
                System.out.print(i + " ");
                j -= w[i];
            }
        }
        System.out.println();
    }

      //
    /*
    * 尝试对dp表的0行0列初始值全为0从1行1列开始计算
    *
    * dp[i][j] 表示物品为i 容量为j时的最大价值
    * dp[0][j]  和 dp[i][0] 全部赋值为0
    *
    * 对于dp[i][j] 有两种可能
    * dp[i][j] =
    *   1、没选第i件商品/选了但不如原来大  d[i-1][j]
    *   2、选第i件商品且比原来大 d[i-1][j-w[i-1]] + v[i-1]
    *  */
    public static void bag01_head(int[]v, int [] w, int c)
    {
        int [][] dp = new int[v.length+1][c+1];  //0行 0列没有使用
        //初始化数组
        for(int i = 0; i < dp.length ; i++)
        {
            dp[i][0] = 0;
        }
        for (int j = 0; j < dp[0].length; j++)
        {
            dp[0][j] = 0;
        }

        //对于每个物品尝试不同的背包大小
        for(int i = 1; i < dp.length; i++)
        {
            int jMax = Math.min(c,w[i-1]);
            //将j 分为大于w[i-1]的部分 和小于w[i-1]的部分
            for(int j = 1; j < jMax; j++)
            {
                dp[i][j] = dp[i-1][j];   //容量小于物品大小为0
            }
            for(int j = jMax; j < dp[0].length; j++)//对于大于的部分分情况讨论
            {
                dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w[i-1]]+v[i-1]);
            }
        }
        System.out.println(dp[dp.length-1][dp[0].length-1]);
        for(int[] i : dp)
        {
            for (int s : i)
            {
                System.out.print(s+" ");
            }
            System.out.println("");
        }
        traceBack_head(w,dp,c);
    }

    /*
    * 头部的回溯算法
    * 从dp[1][dp[0].length-1] 开始
    * 看d[i][j] 与 dp[i-1][j] 的大小关系
    * 1、dp[i-1][j] 大 那么就是选了 打印 并 i = i+1  j = j-w[i-1]
    * 2、否则 没有 i=i-1 j = j
    * */
    public static void traceBack_head(int [] w, int [][]dp, int c)
    {
        int j = c;
        System.out.println("选择物品的编号:");
        for(int i = 1; i <= w.length; i++)
        {
            if(dp[i][j] != dp[i-1][j])
            {
                System.out.print(i+" ");
                j = j-w[i-1];
            }
            else
            {
                j = j;
            }

        }

    }





    public static void main(String[] args) {
        int [] v = {0,3,5,6,1};
        int [] w = {0,1,2,4,3};
        int [][]m = new int[v.length][6+1];
        knapsack(v,w,6,m);
        bag01_head(v,w,6);
    }
}

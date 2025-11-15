package TouGe.Dp;

/**
 * @author X
 * @date 2025/11/4 17:36
 */

/*
     6
    -2 11 -4 13 -5 -2
输出示例：
    20

  给定由n个整数（可能为负数）组成的序列：, 求该序列的最大子段和。当所有整数均为负数，定义其最大子段和为0。

  思路:
  dp[i][j]表示 从i开始跨度为j的最大序列和
  dp[i][j] = max(dp[i][j-1] +num[i+j], dp[i][j-1])
  我们观察数组可以发现
  最优解其实只依赖于 前一个数
  只需要 pre  = dp[i][j-1]
  curr = max(pre, pre + num[i+j])
x */
public class Test3 {

    public static void SS(int [] num, int n)
    {
        int globalMax = num[0];
        int curr = num[0];
        for(int i = 0; i < n; i++)
        {
            curr = Math.max(num[i], curr+num[i]);
            globalMax = Math.max(globalMax,curr);
        }
        globalMax = Math.max(globalMax,0);
        System.out.println(globalMax);
    }

    public static void Ssd(int [] num , int n)
    {
        int [] dp = new int[n];
        dp[0] = num[0];
        int globalMax = 0;
        for(int i = 1; i < n; i++)
        {
            dp[i] = Math.max(dp[i-1]+num[i], num[i]);
            globalMax = Math.max(dp[i], globalMax);
        }
        for (int  k : dp)
        {
            System.out.print(k+" ");
        }

        System.out.println(globalMax);
    }

    public static void main(String[] args) {
        int [] a = {-2, 11, -4, 13, -5, -2};
        SS(a,6);
        Ssd(a, 6);
    }
}

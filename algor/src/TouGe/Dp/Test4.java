package TouGe.Dp;

/**
 * @author X
 * @date 2025/11/4 19:59
 */

/* 给定一个长度为7的数组A5，6，7，1，2，8,9，
* 则其最长的单调递增子序列为5，6，7，8，9，长度为5。
* 求318714101223411624的最长的单调递增子序列长度。
*
* 思路：线性动态规划
* dp[i] 表示以i结尾的最优情况  这里是最长子序列
* dp[0] = num[0]
*
* 比较 num[j] 和 num[i]的大小情况   取
* dp[i] = max(dp[j]+1, dp[i]) j 从 0 到 i-1
*
* */

public class Test4 {

    public static void Llc(int [] num, int n)
    {
        int [] dp = new int[n];
        dp[0] = 1;
        int globalMax = 0;
        for (int i = 1 ; i < n; i++)
        {
            for(int j = 0; j < i; j++)
            {
                if(num[j] < num[i])
                {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    globalMax = Math.max(dp[i], globalMax);
                }
            }
        }
        System.out.println(globalMax);

    }

    public static void main(String[] args) {
        int []a = {5,6, 7, 1, 2, 8, 9};
        Llc(a,7);
    }
}

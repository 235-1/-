package TouGe.Dp;

/**
 * @author X
 * @date 2025/11/4 21:14
 */
/*
* 矩阵连乘问题
* dp[i][j] 表示从 矩阵i 乘到 矩阵j 的最优解
* p数组存储 矩阵的维度
*
* 初始化：
* dp[i][i] = 0
*
* 思路：
* 先求 A1*A2 A2*A3 ....  然后 A1A2A3 A2A3A4... 直到 A1.....A5
* 也就是 间隔从 1 到 2 直到 n-1
* dp[i][j] = dp[i][k] + dp[k][j] +p[i-1]*p[k]*p[j]
* */
public class Test5 {


    public static void Mm(int [] p , int n)
    {
        int [][] dp = new int[n][n];
        for (int i = 0; i < n; i++)
        {
            dp[i][i] = 0;
        }





    }


    public static void main(String[] args) {

    }

}

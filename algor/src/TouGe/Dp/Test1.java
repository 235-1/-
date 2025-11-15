package TouGe.Dp;

/**
 * @author X
 * @date 2025/11/4 15:46
 */

/*
* 测试输入：
    5

    9
    12    15
    10    6     8
    2     18    9    5
    19    7     10   4   16
输出示例：
    max=59
    数值和最大的路径是：9->12->10->18->10
* */

    /*
    * 找最大路径和
    * 思路：
    * 从下向上遍历 利用 一个 dp[i][j] 记录 中间过程
    * dp[i][j] 表示  以i行j列为顶端的最优路径的大小
    *
    * 自底向上
    * dp大小： n*n
    * 初始化：
    * dp[n][j] = num[n][j]
    * 最优子结构：
    * 从 n-2开始遍历
    * dp[i][j] = max(dp[i-1][j]+num[i][j],dp[i-1][j+1]+num[i][j])
    *
    * */
public class Test1 {

    public static void MaxSum(int [][] num, int n)
    {
        int [][] dp = new int[n][n];


        //初始化
        for (int i = 0; i < n; i++)
        {
            dp[n-1][i] = num[n-1][i];
        }


        for (int i = n-2; i >= 0; i--)
        {
            for (int j = i; j >= 0; j--)
            {
                dp[i][j] = Math.max(dp[i+1][j]+num[i][j],dp[i+1][j+1]+num[i][j]);
            }
        }
        System.out.println("max="+dp[0][0]);
        traceBack(dp,n, num);
    }

    /*
    * 回溯出最优解
    * 从数组dp[0][0] 开始回溯
    * 找到每一行的最大值 直到 n-1 结束  n-1  = (n-2) - (n-3)
    *  max=59
    数值和最大的路径是：9->12->10->18->10
    * */
    public static void traceBack(int [] [] dp, int n, int [][]num)
    {
        int [] aws = new int[n];
        int max = -1;
        for (int i = 0; i < n-1; i++)
        {
            max = dp[i][0];

            for (int j = 0; j <= i; j++)
            {
                if (max <= dp[i][j])
                {
                    max = dp[i][j];
                    aws[i] = num[i][j];
                }
            }
        }
        aws[n-1] = max - aws[n-2] ;
        System.out.print("数值和最大路径是：");
        for (int i = 0; i < n; i++)
        {
            if(i!= n-1)
            {
                System.out.print(aws[i]+"->");
            }
            else
            {
                System.out.print(aws[i]);
            }

        }
    }

/*
*   9
    12    15
    10    6     8
    2     18    9    5
    19    7     10   4   16
* */
    public static void main(String[] args) {
        int [][] num = {
                {9},
                {12,15},
                {10,6,8},
                {2,18,9,5},
                {19,7,10,4,16}

        };
        MaxSum(num,5);
    }
}

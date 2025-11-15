package TouGe.Dp;

/**
 * @author X
 * @date 2025/11/4 16:38
 */
/*
* 最长公共子序列问题
    a=“ABCDBAB”
    b=“BDCABA”
    BCBA
    *
    * 思路：
    * dp[i][j] 表示 从0到ai 和 0到bj的最长公共字串
    * 初始化：
    * dp[0][j] 比较 a[0] 和 b[j] 是否相等 等于 dp[0][j] = 1
    * dp[i][0] 比较 b[0] 和 a[i] 是否相等
    *
* */
public class Test2 {

    public static void Lcs(String A, String B)
    {
        char[] a = A.toCharArray();
        char[] b = B.toCharArray();
        int [][] dp = new int[a.length][b.length];
        //初始化
        for (int i = 0; i < a.length; i++)
        {
            if (a[i] == b[0])
            {
                dp[i][0] = 1;
            }

        }
        for (int i = 0; i < b.length; i++)
        {
            if (b[i] == a[0])
            {
                dp[0][i] = 1;
            }
        }

        for (int i = 1; i < a.length; i++)
        {
            for (int j = 1; j < b.length; j++)
            {
                if (a[i] == b[j])
                {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else
                {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }




        for (int [] k : dp)
        {
            for (int c : k)
            {
                System.out.print(c+" ");
            }
            System.out.println(" ");
        }
        traceBack(dp,a,b,a.length-1,b.length-1);

    }


    /*
    * 回溯最优解
    *
    * 1、如果 a[i]==b[j] 且 dp[i][j] > dp[i-1][j-1] 那么进入递归
    * 2、a[i] != b[j] 则 向大的方向移动 比较 dp[i-1][j] 和 dp[i][j-1]的大小
    *
    *
    * */
    public static void traceBack(int [][] dp, char[] a, char[] b, int i, int j)
    {
        if (i == 0 || j == 0)
        {
            return;
        }
        if(a[i] == b[j] && dp[i][j] > dp[i-1][j-1])
        {
            traceBack(dp,a,b,i-1,j-1);
            System.out.print(a[i]+" ");
        }
        else
        {
            if (dp[i-1][j] > dp[i][j-1])
            {
                traceBack(dp,a,b,i-1,j);
            }
            else
            {
                traceBack(dp,a,b,i,j-1);
            }
        }

    }

    public static void main(String[] args) {
        String A = "longest";
        String B = "strong";
        Lcs(A,B);
    }
}

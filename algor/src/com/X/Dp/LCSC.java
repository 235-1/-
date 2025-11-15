package com.X.Dp;

/**
 * @author X
 * @date 2025/10/27 20:40
 */
/*
* 寻找最长连续公共子序列
*
* */
public class LCSC {

    /*
    * 采用dp
    * 一个dp数组  dp[i][j]  表示以A[i]和B[j]结尾的最长连续子序列的长度
    * endpost : 记录最后一个的位置
    * maxlen记录最长长度  与endpost配合方便我们还原
    * */
    public static void lcsc(String A, String B)
    {

        char[] a = A.toCharArray(), b = B.toCharArray();
        int n = a.length, m = b.length;
        int dp [][] = new int[n+1][m+1];
        int endpost = 0, maxlen = 0;
        for(int i = 1; i <= n; i++) //遍历A
        {
            for(int j = 1; j <= m; j++)
            {
                // i代表的是dp数组中的行   这也是为什么 要<= m 因为 我们dp从1开始（防止-1越界）而
                // 指针从0开始 因此 要遍历到最后一个就要<=m  在int[n+1][m+1]; 已经考虑到dp 越界的问题了
                if(a[i-1] == b[j-1]) //i-1  j-1  才代表A B  上的指针
                {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    if(dp[i][j] > maxlen)
                    {
                        endpost = i;
                        maxlen = dp[i][j];
                    }
                }
                else
                {
                    dp[i][j] = 0;
                }
            }
        }
        for(int [] C : dp)
        {
            for (int x : C)
            {
                System.out.print(x+" ");
            }
            System.out.println("");
        }
        System.out.println(maxlen);
        System.out.println(A.substring(endpost - maxlen, endpost));
    }

    /*
    * 我们注意到每次其实只用到了上一行
    * 只开辟两个n,m的数组来记录下上一行和当前行即可
    * */
    public static void lcscp(String A, String B)
    {
        /*
        为什么用B的长度不用A的？
        这与我们的遍历方式有关
        每次记录的都是 同一个i 不同 j 的位置 下次换一个 i 因此以B的长度为准
        */
        int [] prev = new int[B.length()+1], curr = new int  [B.length()+1];
        char [] a = A.toCharArray();
        char[] b = B.toCharArray();
        int maxlen = 0, endpost = 0;
        for (int i = 1; i <= a.length; i++)
        {
            for(int j = 1; j <= b.length; j++)
            {
                if(a[i-1] == b[j-1])
                {
                    curr[j] = prev[j-1]+1;
                    if(curr[j] > maxlen)
                    {
                        maxlen = curr[j];
                        endpost = j;
                    }
                }
                else
                {
                    curr[j] = 0;
                }
            }
            prev = curr.clone();    //记录上一行  可以使用 temp临时值交换替代
        }
        System.out.println(maxlen);
        System.out.println(B.substring(endpost-maxlen,endpost));

    }

    public static void main(String[] args) {
        String A = "longeest";
        String B = "strongeeses";
        lcsc(A, B);
        lcscp(A, B);
    }
}

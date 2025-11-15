package com.X.Divide;

/**
 * @author X
 * @date 2025/10/18 19:51
 */
public class MatrixM {
    /*
    * 多矩阵相乘问题 求最少相乘数
    * p 表示矩阵规模  3*4  4*5 5*2 2*3  可表示为  3，4，5，2，3  n+1 个空间可以表示 n个矩阵
    * m[i][j] 表示从矩阵i到j相乘最少数
    * k[i][j] = n   表示在 n的位置放右括号
    *   例如  m[2][5]  k[2][5] = 3表示(A1*A2*A3)*A4*A5 在这种情况下得到最少相乘数
    * ra、ca  rb、cb 分别表示a/b的行数和列数
    *
    * 采用DP解决
    * 自低向上
    * 从 A1*A2 A2*A3 ....  一直到  A1*A2*A3..... 记录中间值减少计算次数
    * */
    public static void matrixChain_Dp(int [] p, int [][]m, int [][]k)
    {
        int n = p.length - 1; //矩阵个数
        for(int d = 1; d < n; d++) //表示第一个矩阵和最后一个矩阵的间隔
        {
            //表示第i个矩阵的行数的坐标 i小于等于 n-d 例如 4 个矩阵 间隔为2时 只需要 找 m[1][3] m[2][4]
            for(int i = 1; i <= n-d; i++)
            {
                int j = i+d; //第二个矩阵的列数坐标
                m[i][j] = m[i+1][j]+p[i-1]*p[i]*p[j];
                k[i][j] = i;
                for(int s = i; s < j; s++)    //括号的位置 因为最小只能是第1的右边最大只能在最后一个的左边因此是 i<s<j-1
                {
                    int t = m[i][s]+m[s+1][j]+p[i-1]*p[s]*p[j];
                    if(t < m[i][j])
                    {
                        m[i][j] = t;
                        k[i][j] = s;
                    }
                }
            }
        }
    }

    /*
    * 递归 ＋ 记忆解决
    * 利用空间换时间 与 dp不同的是是从上向下解决问题
    * */
    public static int matrixChain(int [] p, int [][]m, int [][]k, int i, int j)
    {
        if(m[i][j] != -1) //不为0则我们已经计算过了
        {
            return m[i][j];
        }
        if(i == j)  //就一个m[i][i] = 0
        {
            return 0;
        }
        int t = Integer.MAX_VALUE;
        k[i][j] = i;
        for(int s = i; s < j; s++)
        {
            int u = matrixChain(p,m,k,i,s)+matrixChain(p,m,k,s+1,j)+ p[i-1]*p[s]*p[j];
            if( u < t)
            {
                t = u;
                m[i][j] = t;
                k[i][j] = s;
            }
        }
        return t;
    }

    /*
    * 复原括号
    * i , j 表示复原 m[i][j] 的括号
    *
    * */
    public static void c(int [][] s, int i, int j)
    {
        if(i == j) System.out.print("A" + i);
        else
        {
            System.out.print("(");
            c(s,i,s[i][j]);
            c(s,s[i][j]+1,j);
            System.out.print(")");
        }

    }

    public static void main(String[] args) {
        int [] p = {3,2,4,2,4,5};
        int [] [] m = new int[6][6];
        int [] [] k = new int[6][6];
        for(int x = 0; x < m.length; x++)
            for(int y = 0; y < m.length; y++)
                m[x][y] = -1;
//        matrixChain_Dp(p,m,k); //((A1(A2A3))(A4A5))最佳相乘方式98
        System.out.println(matrixChain(p, m, k, 1, 5));
        c(k,1,5);
        System.out.println("最佳相乘方式"+m[1][5]);

    }
}

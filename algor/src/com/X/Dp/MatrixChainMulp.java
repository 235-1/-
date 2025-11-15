package com.X.Dp;

/**
 * @author X
 * @date 2025/10/16 19:54
 */
public class MatrixChainMulp {
    /*
    * 对于两个矩阵相乘问题
    * a、b为两个相乘矩阵  c为两个的结果
    * ra、ca、rb、cb 分别为 a/b的行和列
    *
    * 两个矩阵相乘 就是a的行乘以b的列
    * 共乘了 ra*cb*cb 次  或者说是 ra*cb（结果数组的元素个数） *ca（每个要经过乘多少次）次
    * */
    public static void matrixMultiplyTwo(int [][] a, int [][] b, int[][] c, int ra, int ca, int rb, int cb )
    {
        for (int i = 0; i < ra; i++) //a的行
        {
            for (int j = 0; j < cb; j++ ) //b的列
            {
                for (int k = 0; k < cb; k++) //代表点积部分
                {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
    }

    /*
    * 对于多个矩阵相乘  寻找最少相乘次数
    *
    * */

    public static void main(String[] args) {
        int [][] a =
                {
                        {1,2,3},
                        {2,3,4}
                };
        int [][] b =
                {
                        {3,4,5},
                        {1,2,3},
                        {2,1,3}
                };
        int [][]c = new int[a.length][b[0].length];
        matrixMultiplyTwo(a, b, c, a.length, a[0].length, b.length, b[0].length);
        for(int [] l:c)
        {
            for(int m: l)
            {
                System.out.print(m+" ");
            }
            System.out.println("");
        }
    }


}

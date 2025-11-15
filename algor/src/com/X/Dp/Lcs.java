package com.X.Dp;

/**
 * @author X
 * @date 2025/10/19 13:57
 */
public class Lcs {

    /*
     * BFS 递归暴力解决
     * A、B表示两个字符串
     * i 代表A的索引 j代表B的索引
     * */
    static int max = 0;

    public static int lcsBfs(String A, String B, int i, int j, int[][] l) {
        char[] a = A.toCharArray();
        char[] b = B.toCharArray();
        if (i > a.length - 1 || j > b.length - 1)    //等于的时候任然要判断不可返回
        {
            return 0;
        }

        if (a[i] == b[j]) {
            l[i][j] = 1;    //不使用0行0列
            return 1 + lcsBfs(A, B, i + 1, j + 1, l);
        }
        int m = lcsBfs(A, B, i + 1, j, l);
        int n = lcsBfs(A, B, i, j + 1, l);
        if (m > n) {
            l[i][j] = 2;
            return m;
        } else {
            l[i][j] = 3;
            return n;
        }
    }
    /*
     * 记忆＋递归
     * */

    public static int lcsD(String A, String B, int[][] m, int[][] l, int i, int j) {
        char[] a = A.toCharArray();
        char[] b = B.toCharArray();
        if (i > a.length - 1 || j > b.length - 1) {
            return 0;
        }
        if (m[i][j] != -1) {
            return m[i][j];
        }
        if (a[i] == b[j]) {
            l[i][j] = 1;
            m[i][j] = 1 + lcsD(A, B, m, l, i + 1, j + 1);
        } else {
            int g = lcsD(A, B, m, l, i + 1, j);
            int f = lcsD(A, B, m, l, i, j + 1);
            if (g < f) {
                l[i][j] = 3;
                m[i][j] = f;
            } else {
                l[i][j] = 2;
                m[i][j] = g;
            }
        }
        return m[i][j];
    }


    //回溯最长字串
    public static String p(int[][] b, int i, int j, String A) {
        char[] a = A.toCharArray();
        if (i >= b.length || j >= b[0].length) {
            return "-1";
        }
        if (b[i][j] == 1) {

            System.out.print(a[i]);
            return p(b, i + 1, j + 1, A);
        } else if (b[i][j] == 2)   //是lcsBfs(A,B,i+1,j, l);
        {
            return p(b, i + 1, j, A);
        } else {
            return p(b, i, j + 1, A);
        }
    }

    /*
     * dp解决  与记忆化+递归不同的是他是从下向上的一种方式
     * */
    public static void lcsDp(String A, String B, int[][] m, int[][] s) {
        //从1开始
        A = "#" + A;
        B = "#" + B;
        char[] a = A.toCharArray();
        char[] b = B.toCharArray();
        for (int i = 1; i < a.length; i++)
            for (int j = 1; j < b.length; j++) {
                if (a[i] == b[j]) {
                    m[i][j] = 1 + m[i - 1][j - 1];
                    s[i][j] = 1;

                } else // 如果没匹配上那 在 A串前一个 或B串前一个里面选一个
                {
                    int t = m[i][j - 1];
                    int u = m[i - 1][j];
                    if (u > t) {
                        m[i][j] = u;
                        s[i][j] = 3;
                    } else {
                        m[i][j] = t;
                        s[i][j] = 2;
                    }
                }
            }
    }

    public static void pToDp(int[][] b, int i, int j, String A) {
        char[] a = A.toCharArray();
        if (i <= 0 || j <= 0) {
            return;
        }
        if (b[i][j] == 1) {
            pToDp(b, i - 1, j - 1, A);  //为了报持顺序先递归在输出  这里是从后向前的
            System.out.print(a[i]);

        } else if (b[i][j] == 2) {
            pToDp(b, i, j - 1, A);
        } else {
            pToDp(b, i - 1, j, A);
        }

    }


    /*
     * m [i][j] , 从i到j的最长公共子序列
     * 不采用额外数组存储括号位置
     * */
    public static void LcsDp(String A, String B, int[][] m) {
        A = "#" + A;
        B = "#" + B;
        char[] a = A.toCharArray();
        char[] b = B.toCharArray();
        int u = a.length - 1;
        int l = b.length-1;
        for(int i = 1; i <= u ; i++)
        {
            for(int j = 1 ; j <= l ; j++ )
            {
                if(a[i] == b[j])
                {
                    m[i][j] = m[i-1][j-1] + 1;
                }
                else
                {
                    m[i][j] = Math.max(m[i-1][j], m[i][j-1]);
                }
            }
        }
    }

    /*
    * 利用m数组得出最优解
    * 从 m[m.length][m[0].length] 开始
    * 我们结合 A B来一起判断 是否是最优解
    * 如果  a[i] == b[j] 并且 m[i][j] == m[i-1][j-1] + 1
    * 我们就可以认为 a[i] / b[i]被选中了 放入数组 i - -; j--;
    * 否则 判断 m[i-1][j] 和 m[i][j-1] 谁更大  选择是进行 i-1 还是 j-1
    * */
    public static void tarket(int [] [] m, char [] aws, String A, String B)
    {
        char [] a = A.toCharArray();
        char [] b = B.toCharArray();
        int nums = 0;
        int i = a.length;
        int j = b.length;
        


    }
    public static void main(String[] args) {

        String A = "longest";
        String B = "strong";
        int [] [] l=new int[A.length()][B.length()];
        int [][] m = new int[A.length()+1][B.length()+1];
//        for(int x = 0; x < m.length; x++)
//            for(int y = 0; y < m[0].length; y++)
//                m[x][y] = 0;
//        System.out.println(lcsBfs(A, B, 0, 0, l));
//        System.out.println(lcsD(A, B, m, l, 0, 0));
//        lcsDp(A,B,m,l);
//        for(int [] c: l)
//        {
//            for(int s : c)
//            {
//                System.out.print(s+" ");
//            }
//            System.out.println();
//        }
////        p(l,0,0,A);
//        pToDp(l,A.length(),B.length(),"#"+A);

        LcsDp(A,B,m);
        System.out.println(m[A.length()][B.length()]);
        for(int [] c: m)
        {
            for (int t : c)
            {
                System.out.print(t+" ");
            }
            System.out.println();
        }
        char [] a = new char[m[A.length()][B.length()]];
        tarket(m,a,A, B);
        for(int i = a.length-1 ; i >=0 ; i--)
        {
            System.out.println(a[i]);
        }
    }

}

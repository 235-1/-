package com.X.Divide;

public class Chess {



    public static void main(String[] args) {
        chessBoard(0, 0 , 1, 2, 8);

    }

    public static void printB(int[][]board, int size)
    {
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                System.out.printf("%3d",board[i][j]);
            }
            System.out.println();
        }
    }

    /*
    * tr 棋盘左上角行坐标
    * tc 棋盘左上角列标    左上角（tr, tc）
    * dr 特殊方块行坐标
    * dc 特殊方块列坐标    特殊方块（dr, dc）
    * size 棋盘边长
    * */
    static int [][] board = new int[1024][1024];
    static int tail = 1;   //骨牌编号初始化为1
    public static void chessBoard(int tr, int tc, int dr, int dc, int size)
    {

        if(size == 1) return;   //只有一个位置不用操作
        else
        {
            int s = size / 2;   // 棋盘大小分为四块
            int t = tail++;
            //先判断左上角
            if(dr < tr+s && dc < tc+s)   //特殊方块在该区域
            {
                chessBoard(tr, tc, dr, dc, s); // 继续分这个区域
            }
            else    //不在这个区域
            {
                board[tr+s-1][tc+s-1] = t;    //填充右下角
                chessBoard(tr, tc, tr+s-1, tc+s-1, s );
            }
            //左下角
            if(dr >= tr+s && dc < tc+s)
            {
                chessBoard(tr+s, tc, dr, dc, s);
            }
            else    //填充右上角
            {
                board[tr+s][tc+s-1] = t;
                chessBoard(tr+s, tc, tr+s, tc+s-1, s);
            }
            //右上角
            if(dr < tr+s && dc >= tc+s)
            {
                chessBoard(tr, tc+s, dr, dc, s);
            }
            else    //填充左下角
            {
                board[tr+s-1][tc+s] = t;
                chessBoard(tr, tc+s, tr+s-1, tc+s, s);
            }
            //右下角
            if(dr >= tr+s && dc >= tc+s )
            {
                chessBoard(tr+s, tc+s, dr, dc, s);
            }
            else //填充左上角
            {
                board[tr+s][tc+s] = t;
                chessBoard(tr+s, tc+s, tr+s, tc+s, s);
            }
        }
        printB(board, size);
    }
}

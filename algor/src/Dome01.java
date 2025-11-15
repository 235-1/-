import java.util.ArrayList;

public class Dome01 {

    public static int [] sweap(int[] a, int i, int j)
    {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        return a;
    }

    /*
    * 全排列：
    * int [] a 给定数组从 1 开始连续的数
    * k 从 k 开始排列
    * m 数组的结尾
    * O(n * n!)
    * */
    public static void perm(int [] a, int k, int m)
    {
        if (k == m)
        {
            for(int c: a)
            {
                System.out.print(c+" ");
            }
            System.out.println(" ");
            return;
        }
        for(int i = k; i <= m; i++)
        {
            sweap(a, k, i);
            perm(a, k + 1, m);
            sweap(a, k, i);
        }
    }
    /*
    * int [] a 是目标排序数组
    * int l 数组起始位置
    * int h 数组结束位置
    * int target 目标数
    * O(logn)
    * */
    public static int  BinarySearchN(int [] a, int l, int h, int target)
    {
        int mid ;

       while (l <= h)
       {
           mid = (l+h) / 2;
           if(a[mid] == target)
           {
               return mid ;
           }
           if(a[mid] < target)
           {
               l = mid + 1;
           }
           if(a[mid] > target)
           {
               h = mid - 1;
           }
       }
        return -1;
    }
    /*
    * 递归解决二分查找
    * */
    public static int  BinarySearchR(int [] a, int l, int h, int target)
    {
        int mid = (l + h) / 2;
        if(l >= h)
        {
            if(a[mid] == target)
            {
                return mid;
            }
            return -1;
        }
        if(a[mid] < target)
        {
            return BinarySearchR(a, mid + 1, h, target);
        }
        if (a[mid] > target)
        {
            return BinarySearchR(a, l, mid - 1, target);
        }
        if (a[mid] == target)
        {
            return mid;
        }
        return -1;

    }
    /*
    * int [] tree 要进行堆化的数组
    * int k 需要调整的元素索引
    * int num 根的元素个数
    * i 节点的 parent为  i-1 / 2
    * i 的 lson 为 2*i+1  rson 为 2*i+2
    * 堆化将 k 位置上的元素同其子节点对比
    * 构造大堆
    * */

    public static int [] heapify(int [] tree, int num,int k)
    {
        int lson = k*2 + 1;
        int rson = k*2 + 2;
        int max = k;  // 假设最大值的索引
        if(rson < num  && tree[rson] > tree[max])
      {
          max = rson;
      }
        if(lson < num && tree[lson] > tree[max])
        {
            max = lson;
        }// 找出了他们之间的最大值
        //交换位置
        if(max != k) // 如果max不是k了  如果还是k那就没必要换这已经是一个堆了直接遍历下一个
        {
            sweap(tree,k, max);
            heapify(tree, num, max);
        }
        return tree;
    }
    /*
    * 构造一个大堆
    * tree 需要构造的堆
    * n 长度
    * */
    public static int [] buildHeap(int []tree, int n)
    {
        int length = n - 1;
        int parent = length/2;
        for(int i = parent; i >= 0; i--)
        {
            tree = heapify(tree, n, i);
        }
        return tree;
    }

    /*
    * 堆排序
    * 先创建一个堆，再依次删除堆，将删除的数据放入最后一位(不断从后面交换数组的0号元素)
    *O(nlogn)
    * */
    public static int [] heapSort(int[] tree, int n)
    {
        tree = buildHeap(tree, n); // ----------n
        for(int i = n - 1; i > -1; i--)// -----------nlogn
        {
            sweap(tree, i, 0);// -----------1
            heapify(tree, i, 0);// logn
        }
        return tree;
    }

    /*
    * merger 归并将两个列表按大小并为一个
    * int [] a 待合并的数
    * ArrayList<Integer> temp 用于存放临时归并后的数据
    * int l , 左边下标
    * int mid 中间位置
    * int r , 右边下标
    *
    * */

    public static int [] merger(int [] a, int l, int mid, int r )
    {
        int i = l;
        int j = mid + 1;
        ArrayList<Integer> temp = new ArrayList<Integer>();
       // 存放临时数组
        while(i < mid + 1 && j <= r)
        {
            if(a[i] < a[j])
            {
                temp.add(a[i]);
                i++;
            }
            else
            {
                temp.add(a[j]);
                j++;
            }
        }

        while(i < mid + 1)
        {
            temp.add(a[i]);
            i++;
        }
        while (j <= r)
        {
            temp.add(a[j]);
            j++;
        }
        //将temp赋值给a
        for(int m = 0 ; m<temp.size(); m++)
        {
            a[l+m] = temp.get(m);
        }

        return a;

    }

    /*
    * mergerSort
    * int [] a 待排序数组
    * int l 数组的最左端索引  开始的下标
    *
    * int r 数组最右端索引  结束的下标
    *
    * */
    public static int [] mergerSort(int [] a, int l, int r)
    {
        if(l < r)
        {
            int mid = (r + l)/2;
            mergerSort(a, l, mid);
            mergerSort(a, mid + 1, r);
            merger(a, l , mid,r);
        }
        return a;
    }


    public static void main(String[] args) {
        int a[] = {1,2,3};
        int n[] = {2,3,6,12,15,18,20,19,22,24,25,26,33,36,38,39,42,44};
        int tree[] = {10, 9, 3, 2, 1, 5, 16, 11};
        int [] left = {2, 6, 7, 10, 11, 14};
        int [] right = {3, 4, 5, 11, 21};
        int [] a1 = {2111, 21, 20, 6, 5, 8, 19, 21, 211, 12, 1};
//        tree = heapSort(tree, tree.length);
//        for (int c : tree)
//        {
//            System.out.print(c+" ");
//        }
        a1 = mergerSort(a1, 0, a1.length-1);
        for(int c: a1)
        {
            System.out.println(c+" ");
        }
    }
}



package class05;

public class Code01_CountOfRangeSum {
    /*
    要求：求数组中有多少子数组的和在范围lower-upper中
    例如[1,2,3] 有2个子数组在[3,4]的范围中

    思路：
        1.将子数组的和转化为两个前缀和相减
        2.对于右组的某个数x，左组中有多少数在x-upper至x-lower之间
        3.因为x-upper与x-lower单调递增，在左组构建两个不回退指针winL,winR，分别批量统计符合要求的数据量
    */

    public static int countRangeSum(int[] arr, int lower, int upper) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        // 构建前缀和数组
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        return process(sum, 0, arr.length - 1, lower, upper);
    }

    public static int process(int[] arr, int L, int R, int lower, int upper) {
        if (L == R) {
            return 0;
        }
        int M = L + ((R - L) / 2);
        return process(arr, L, M, lower, upper) + process(arr, M + 1, R, lower, upper) + merge(arr, L, M, R, lower, upper);
    }

    public static int merge(int[] arr, int L, int M, int R, int lower, int upper) {
        int winL = L;
        int winR = L;
        int res = 0;
        int p1 = L;
        int p2 = M + 1;
        int[] help = new int[R - L + 1];
        int idx = 0;

        //统计
        for (int i = M + 1; i <= R; i++) {
            while(winL <= M && arr[winL] < arr[i] - upper) {
                winL++;
            }
            while(winR <= M && arr[winR] <= arr[i] - lower) {
                winR++;
            }
            res += (winR - 1) - (winL - 1); //winR - winL
        }
        //归并
        while (p1 <= M && p2 <= R) {
            help[idx++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[idx++] = arr[p1++];
        }
        while (p2 <= R) {
            help[idx++] = arr[p2++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return res;
    }

    /////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        int[] test = {1,2,3,4,5};
        int lower = 3;
        int upper = 5;
        int res = countRangeSum(test, lower, upper); //return 4
        if (res == 4) {
            System.out.println("测试通过！");
        }


    }












}

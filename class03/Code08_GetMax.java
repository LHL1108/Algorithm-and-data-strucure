package class03;

public class Code08_GetMax {
    // 递归方法求数组中的最大值

    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        } else {
            int mid = L + (R - L) / 2;
            int leftMax = process(arr, 0, mid);
            int rightMax = process(arr, mid+1, R);
            return Math.max(leftMax, rightMax);
        }
    }

    public static int findMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        int[] test1 = {1, 22, 13, 4, 5};
        int ans = findMax(test1);
        System.out.println(ans);
    }
}

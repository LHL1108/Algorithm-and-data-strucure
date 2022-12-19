package lai01;

public class Test01 {


    public static int[] kClosest(int[] arr, int target, int k){
        if (arr == null || arr.length < 1) {
            return new int[] {};
        }
        if (k < 1) {
            return new int[] {};
        }
        int[] res = new int[k];
        int L = findLargerstSmallerOrEqual(arr, target);
        int R = L;
        res[0] = arr[L];
        for (int i = 1; i <= k - 1; i++) {
            if (R >= arr.length - 1 || L > 0 && (target - arr[L - 1] <= arr[R + 1] - target)) {
                L--;
                res[i] = arr[L];
            } else {
                R++;
                res[i] = arr[R];
            }
        }
        return res;
    }

    public static int findLargerstSmallerOrEqual(int[] arr, int target) {
        int L = 0;
        int R = arr.length - 1;
        while (L < R - 1) {
            int mid = L + (R - L) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                L = mid;
            } else {
                R = mid - 1;
            }
        }
        if (Math.abs(arr[R] - target) <= Math.abs(arr[L] - target)) {
            return R;
        } else {
            return L;
        }
    }
















}

package lai01;
import java.util.Arrays;
/*
[QUESTION]
    find k elements whose value is closest to the target
[IDEA]
    find the largest smaller or equal element, then expend both sides
[CONSTRUCTION]
    cleaning check
        arr
            null
            len < 1
        k
            k <= 0
    find the largest smaller or equal number
        find mid
            equal
                R = mid
            smaller
                L = mid
            larger
                R = mid - 1
        compare last 2
    expand
        iteration k - 1 rounds
            right is out of boundary or (left is less than boundary, left difference is smaller
                move to left
            else
                move to right
[NOTICE]
    the key idea to find the largest smaller item
    how to move L and R
[COMPLEXITY]
    Time: O(logN + k)  we have 2 step, one is binary search by n, one is linear expansion by k
    Space: O(1) we only apply for several limited variables
*/


public class Code06_K_Closest_In_Sorted_Array {
    public static int[] kClosest(int[] arr, int target, int k) {
        if (arr == null || arr.length < 1) {
            return new int[] {};
        }
        if (k < 1) {
            return new int[] {};
        }
        int[] res = new int[k];
        int L = findLargestsmallerOrEqual(arr, target);
        int R = L;
        res[0] = arr[L];
        for (int i = 1; i < k; i++) {
            if (R >= arr.length - 1 || (L >= 1 && (target - arr[L - 1]) <= (arr[R + 1] - target))) {
                L--;
                res[i] = arr[L];
            } else {
                R++;
                res[i] = arr[R];
            }
        }
        return res;
    }

    public static int findLargestsmallerOrEqual(int[] arr, int target) {
        int L = 0;
        int R = arr.length - 1;
        while (L < R - 1) {
            int mid = L + (R - L) / 2;
            if (target == arr[mid]) {
                return mid;
            } else if (arr[mid] < target) {
                L = mid;
            } else {
                R = mid - 1;
            }
        }
        if (Math.abs(target - arr[R]) <= Math.abs(target - arr[L])) {
            return R;
        } else {
            return L;
        }
    }


    public static void main(String[] args) {

        int[] A = new int[] {1, 4, 5, 6, 7, 8, 10, 14};
        int T = 3;
        int K = 4;
        System.out.println(Arrays.toString(kClosest(A, T, K)));
    }
}

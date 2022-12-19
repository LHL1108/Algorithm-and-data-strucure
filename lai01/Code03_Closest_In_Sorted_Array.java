package lai01;

/*
[QUESTION]
    find the idx of value that is closest to target
[IDEA]
    use binary search to reduce the seach area, finally compare last two elements
[CONSTRUCTION]
    binary search
    compare last 2
[Notice]
    we need to left 2, so while (L < R - 1)
    even if mid not equal to target, it still could be the closest one, so L/R = mid when shrinking the search area
[COMPLEXITY]
    Time: O(logN)  we use binary search
    Space: O(1)    we only apply for several limited variables
*/

public class Code03_Closest_In_Sorted_Array {
    public static int closest(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        while (L < R - 1) {
            int mid = L + (R - L) / 2;
            if (target == arr[mid]) {
                return mid;
            } else if (target < arr[mid]) {
                R = mid;
            } else {
                L = mid;
            }
        }
        if(Math.abs(arr[L] - target) <= Math.abs(arr[R] - target)) {
            return L;
        } else {
            return R;
        }
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 3};
        int T = 2;
        System.out.println(closest(A, T)); //1
    }
}

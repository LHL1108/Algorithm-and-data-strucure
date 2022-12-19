package lai01;
/*
[QUESTION]
    find the index of the first occurrence position, if not exists, return -1
[IDEA]
    use binary search, as long as we need to find the leftmost idx, if arr[mid] = target, we shrink boundary rather than
    return idx directly, finally we left 1 element, just make sure if it is.
[CONSTRUCTION]
    binary search
        equal
            R = mid
        arr[mid] is larger
            R = mid - 1
        arr[mid] is smaller
            L = mid + 1
    compare last value
[COMPLEXITY]
    Space: O(logN)
    Time: O(1)
*/

public class Code04_First_Occurrence {
    public static int firstOccur(int[] arr, int target) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        while (L < R) {
            int mid = L + (R - L) / 2;
            if (arr[mid] == target) {
                R = mid;
            } else if (arr[mid] > target) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        if (arr[L] == target) {
            return L;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 2, 2, 3};
        int T = 2;
        System.out.println(firstOccur(A, T));  // 1
    }
}

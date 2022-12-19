package lai01;
/*
[QUESTION]
    find the index of last occurrence of target, if not exists, return -1
[IDEA]
    use binary search to shrink search area, compare last one
[CONSTRUCTION]
    shrink search area
        while there are 2 or more elements
            middle value equals to target
                L -> mid
            middle value smaller
                L -> mid + 1
            middle value larger
                R -> mid - 1
    compare
        equal
            return idx
        not equal
            return -1
[NOTICE]
    notice L = mid, it could fall in infinity loop
    use L < R - 1,when 2 elements, jump out of the loop, two avoid L = mid loop
    if else has its order, we need to compare right first
 */

public class Code05_Last_Occurrence {

    public static int lastOccur(int[] arr, int target) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        while (L < R - 1) {
            int mid = L + (R - L) / 2;
            if (arr[mid] == target) {
                L = mid;
            } else if (arr[mid] > target) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        if (arr[R] == target) {
            return R;
        } else if (arr[L] == target) {
            return L;
        } else {
            return -1;
        }
    }

    public static void main(String[] args){
        int[] A = {1, 2, 2, 2, 3};
        int T = 2;
        System.out.println(lastOccur(A, T)); // 3
    }

}

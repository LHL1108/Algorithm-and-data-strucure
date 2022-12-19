package lai01;
/*
[QUESTION]
    find the idx of the smallest number larger than target in an ascending array
[IDEA]
    use binary search, consider the boundary shrinking rule
[CONSTRUCTION]
    cleaning check
        null
        0len
    initialize
        L
        R
    while more than 1 element
        compute mid idx
        arr[mid] <= target
            L = mid + 1;
        arr[mid] > target
            R = mid;
    if it is larger
        return it
    else
        -1
[NOTICE]
    decide the mid moving condition by the question
[COMPLEXITY]
    Time: O(logN)  we use binary search
    Space: O(1)    we only apply for several variables
*/
public class Code07_Smallest_Element_Larger_than_Target {
    public int smallestElementLargerThanTarget(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        while (L < R) {
            int mid = L + (R - L) / 2;
            if (arr[mid] <= target) {
                L = mid + 1;
            } else {
                R = mid;
            }
        }
        if (arr[L] > target) {
            return L;
        } else {
            return -1;
        }
    }
}

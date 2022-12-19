package lai01;

/*
[QUESTION]
    find the index of target value in an ascending array, if not exists, return -1
[IDEA]
    binary search, reduce a half of search area depending on the comparison of arr[mid] and target
[CONSTRUCTION]
    compute mid index
    compare mid value and target
    reduce search area
[Notice]
    L <= R, it must be found in array if it exists
    3 situation, equal, smaller, bigger, if equal, return directly, if smaller or bigger, remember to -1 or +1
    if couldn't find in loop, remember to return -1;
[COMPLEXITY]
    Time: O(logN)  use binary search
    Space: O(1)    use only several variables
*/



public class Code01_Classical_Binary_Search {
    public static int binarySearch(int[] arr, int target) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        while (L <= R) {
            int mid = L + (R - L) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                R = mid - 1;
            } else {
                L = mid + 1; //should be mid+1
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] test = {1, 2, 3, 4, 5, 6, 7};
        int target = 3;
        System.out.println(binarySearch(test, target)); //2
    }
}

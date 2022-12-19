package lai12;
/*
[Question]
    find the longest ascending sub array in an unsorted array
[Idea]
    linear scan and look back, if the last number is smaller, cnt + 1, otherwise update cnt to 1 to calculate again
*/


public class Code02_LongestAscendingSubArray {

    public static int longest(int[] arr) {
        int max = 0;
        int cur = 0;
        int last = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i == 0 || arr[i - 1] >= arr[i]) {
                cur = 1;
            } else {
                cur = last + 1;
            }
            max = Math.max(cur, max);
            last = cur;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {7, 2, 3, 1, 5, 8, 9, 6};
        //4
        System.out.println(longest(arr));
    }
}

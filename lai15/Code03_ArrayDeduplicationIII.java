package lai15;
/*
[question]
    deduplicate the ascending array, remove all the duplicated chunck in arr
    {1, 2, 2, 3, 3, 3} -> {1}
[idea]
    two pointers, use a flag to determine if we meet duplication right now, if it is, replace this slow, other wise, jump to the next position
[complexity]
    time: n
    space: 1
[notice]
    when return, tail could be changed
    hard to understand, use a array to simulate
*/


import java.util.Arrays;

public class Code03_ArrayDeduplicationIII {

    public static int[] dedup(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return arr;
        }
        int slow = 0;
        boolean deleteCurr = false;
        for (int fast = 1; fast < arr.length; fast++) {
            if (arr[slow] == arr[fast]) {
                deleteCurr = true;
            } else if (deleteCurr == true) {
                arr[slow] = arr[fast];
                deleteCurr = false;
            } else {
                slow++;
                arr[slow] = arr[fast];
            }
        }
        return Arrays.copyOfRange(arr, 0, deleteCurr == true ? slow : slow + 1);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 3, 3};
        int[] res = dedup(arr);
        // {1}
        System.out.println(Arrays.toString(res));
    }
}

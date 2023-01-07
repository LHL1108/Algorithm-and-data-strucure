package lai15;

/*
[question]
    deduplicate the array like this {1, 2, 3, 3, 3, 2, 2} → {1, 2, 2, 2} → {1},
[idea]
    two pointers
    if initial situation or not equal, copy value to the next position
    otherwise, move j to the next different element and delete i
    return arr[0, i]
[complexity]
    time: O(n)
    space: O(1)
*/

import java.util.Arrays;

public class Code07_ArrayDeduplicationIV {

    public static int[] dedup(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return arr;
        }
        int i = -1;
        for (int j = 0; j < arr.length; j++) {
            if (i == -1 || arr[j] != arr[i]) {
                arr[++i] = arr[j];
            } else {
                while (j + 1 < arr.length && arr[j + 1] == arr[i]) {
                    j++;
                }
                i--;
            }
        }
        return Arrays.copyOfRange(arr, 0, i + 1);
    }




    public static void main(String[] args) {
        int[] arr = {1,1,2,2,2,3};
        int[] res = dedup(arr);
        // [1, 5]
        System.out.println(Arrays.toString(res));
    }
}

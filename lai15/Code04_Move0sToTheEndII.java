package lai15;
/*
[question]
        move all the 0 elements in array to the end, maintain the relative order
        exg： [1, 0, 2, 0, 3] -> [1, 2, 3, 0, 0]
[idea]
    two pointer, slow: curr idx to place non-zero element; fast: watch each element
    first, get all the non-zero element to the left
    second, we set all the element after slow to 0
[complexity]
    time: n
    space: 1
[notice]
    don't forget to add i after copy
    no need to write i in second for loop
*/

import java.util.Arrays;

public class Code04_Move0sToTheEndII {

    public static int[] moveZero(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return arr;
        }
        int i = 0;
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] != 0) {
                arr[i++] = arr[j];
            }
        }

        for (; i < arr.length; i++) {
            arr[i] = 0;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {1, 0, 2, 0, 3};
        int[] res = moveZero(arr);
        System.out.println(Arrays.toString(res));
    }
}

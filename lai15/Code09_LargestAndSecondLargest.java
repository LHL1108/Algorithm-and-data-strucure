package lai15;
import java.util.Arrays;
/*
[question]
	{1, 3, 2, 4}, {4, 3}
[idea]
	traverse the array, if the new element is larger than max1, update the max1 and max2,
	if the new element is larger than max2 but smaller than max1, only update max2, otherwise, do nothing. go to next element.
[complexity]
	time: O(n)
	space: O(1)

*/

public class Code09_LargestAndSecondLargest {

    public static int[] largestAndSecond(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return arr;
        }

        int max1 = arr[0];
        int max2 = Integer.MIN_VALUE;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max1) {
                max2 = max1;
                max1 = arr[i];
            } else if (arr[i] > max2) {
                max2 = arr[i];
            }
        }
        return new int[] {max1, max2};
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 4};
        int[] res = largestAndSecond(arr);
        // [4, 3]
        System.out.println(Arrays.toString(res));
    }
}

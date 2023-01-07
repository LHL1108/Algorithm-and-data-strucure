package lai11;
import java.util.Arrays;

/*
[question]
    reorder the array in place like this: 0123456789 -> 0516273849
[my idea]
    first, get all elements to the right side like this
        0123456789 -> 0156 234789
    second, recurse the left and right part

    point is, how to move elements?
        first, reverse lm to m-1: 01(432)56789
        second, reverse m to rm-1:01432(65)789
        third, reverse lm to rm:  01(56234)789
[complexity]
    time: O(n)
    space: O(n) ?
[notice]
    two situation, length is odd and even
    use left + (n / ?) to make sure all the idx is right (lmid, mid, rmid)
    arr.length doesn't change, use n or right - left to judge the length of subarray
    don't forget to give arr[r] = tmp when swap
*/


public class Code05_ReOrderArray {

    public static int[] reorder(int[] arr) {
        if (arr == null) {
            return arr;
        }
        if (arr.length % 2 == 0) {
            helper(arr, 0, arr.length - 1);
        } else {
            helper(arr, 0, arr.length - 2);
        }
        return arr;
    }


    public static void helper(int[] arr, int left, int right) {
        int n = right - left + 1;  //10
        if (n <= 2) {
            return;
        }
        int mid = left + n / 2;        //5
        int lmid = left + n / 4; //2
        int rmid = left + n * 3 / 4; // 7
        reverse(arr, lmid, mid - 1);    //2, 4
        reverse(arr, mid, rmid - 1);    //5, 6
        reverse(arr, lmid, rmid - 1);   //2, 6
        helper(arr, left, left + (lmid - left) * 2 - 1);
        helper(arr, left + (lmid - left) * 2, right);
    }

    public static void reverse(int[] arr, int l, int r) {
        while (l < r) {
            int tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
            l++;
            r--;
        }
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] res = reorder(arr);
        // [1, 3, 2, 4, 5]
        System.out.println(Arrays.toString(res));
    }
}

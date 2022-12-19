package lai08;
/*
[Question]
    Given an integer array of size N - 1, containing all the numbers from 1 to N except one, find the missing number,
    The given array is not null, and N >= 1
    arr = {2, 1, 4}, return 3
[Idea]
    create a boolean arr, put all element in array into boolean arr, then check which idx was not changed, return it
[Notice]
    boolean size is arr size + 2, because we need to keep 0, and missing position
[Complexity]
    Time: O(N), we traverse arr two times
    Space: O(N), we create a boolean arr, it could be save by calculation method
*/

public class Code02_MissingNumberI {
    public static int missing(int[] arr) {
        boolean[] flag = new boolean[arr.length + 2];
        flag[0] = true;
        for (int item : arr) {
            flag[item] = true;
        }

        for (int i = 1; i < flag.length; i++) {
            if (flag[i] == false) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 4};
        int res = missing(arr);
        //3
        System.out.println(res);
    }
}

package lai08;
/*
[Question]
    Find all numbers that appear in both of two sorted arrays (the two arrays are all sorted in ascending order).
    A = {1, 1, 2, 2, 3}, B = {1, 1, 2, 5, 6}, common numbers are [1, 1, 2]
    In each of the two sorted arrays, there could be duplicate numbers.
    Both two arrays are not null.
[Idea]
    two pointers traverse the arrays, move idx depends on the situation
[Notice]
    two pointers method
    ends loop when one reach to the end
[Complexity]
    Time: O(N), we couldn't avoid to traverse in this question
    Space: O(1), we only several variables
*/

import java.util.ArrayList;
import java.util.List;

public class Code03_CommonNumbersOfTwoSortedArraysI {

    public static List<Integer> common(int[] arr1, int[] arr2) {
        List<Integer> res = new ArrayList<>();
        if (arr1.length == 0 || arr2.length == 0) {
            return res;
        }
        int i = 0;
        int j = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] == arr2[j]) {
                res.add(arr1[i]);
                i++;
                j++;
            } else if (arr1[i] < arr2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 1, 2, 2, 3};
        int[] arr2 = {1, 1, 2, 4};
        List<Integer> res = common(arr1, arr2);
        System.out.println(res);
    }

}

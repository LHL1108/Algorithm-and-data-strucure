package lai16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
[question]
	give array and target, find all 3 sum pairs, no duplicated triples should be added into result, and the order of triples doesn't matter, return as a list in list
[idea]
	sort array first
	try each item as the first, try to find the second and third
	second begin with first + 1 position, third begin with len - 1, they both move to middle.
	move one of second and third depends the sum
[complexity]
	time: O(n^2), for each i, traver to find j and k
	space: O(1)
[notice]
	the idea of move one of two pointer depends on the sum
	we only skip j after we have found and added, otherwise we will skip some answer consist of some duplicate value eg. 004 in {-1, 0, 0, 2, 3, 4, 5};
*/

public class Code08_3Sum {

    public static List<List<Integer>> allTriples(int[] arr, int target) {
        if (arr == null || arr.length < 3) {
            return null;
        }
        Arrays.sort(arr);

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < arr.length - 2; i++) {
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = arr.length - 1;
            while (j < k) {
                if (arr[i] + arr[j] + arr[k] == target) {
                    res.add(Arrays.asList(arr[i], arr[j], arr[k]));
                    j++;
                    while (j < k && arr[j] == arr[j - 1]) {
                        j++;
                    }
                } else if (arr[i] + arr[j] + arr[k] < target) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {

        int[] arr = {-1, 0, 0, 2, 3, 4, 5};
        List<List<Integer>> res = allTriples(arr, 4);
        //[[-1, 0, 5], [-1, 2, 3], [0, 0, 4]]
        for (List<Integer> item : res) {
            for (Integer idx: item) {
                System.out.print(idx);
            }
            System.out.println();
        }
    }
}

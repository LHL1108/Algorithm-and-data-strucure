package lai16;
import java.util.HashSet;
import java.util.Set;

/*
[question]
	given an array and a target, return if there are two numbers that can add to target
[idea]
	use a hashset, to store each number's required number, and traverse the array, find the element in set. if we could find, return true;
[complexity]
	time: O(n)
	space: O(n)
*/


public class Code05_2Sum {

    public static boolean existSum(int[] arr, int target) {
        if (arr == null || arr.length < 2) {
            return false;
        }

        Set<Integer> requiredSet = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            if (requiredSet.contains(arr[i])) {
                return true;
            } else {
                requiredSet.add(target - arr[i]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int target = 5;
        boolean res = existSum(arr, target);
        // true
        System.out.println(res);
    }
}

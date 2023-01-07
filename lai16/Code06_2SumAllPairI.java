package lai16;
import java.util.*;

/*
[question]
	give a array and target, return all the pairs in array that can add up to target
	eg. {1, 3, 2, 4}, target = 5, return [[0, 3][1, 2]]
[idea]
	because there could be the same value in different idx, so we need to use a map to
	store each number's compliment position, and add them to result
[complexity]
	time: O(n)
	space: O(n)
[notice]
    the meaning of map, key is value in arr, value is the same value index
    API: Arrays.asList(1, 2) create a list as [1, 2]
    don't merge these two: map.put(arr[i], new ArrayList<>()); map.get(arr[i]).add(i);

*/

public class Code06_2SumAllPairI {

    public static List<List<Integer>> allPairs(int[] arr, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (arr == null || arr.length < 2) {
            return res;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(target - arr[i])) {
                for (int item : map.get(target - arr[i])) {
                    res.add(Arrays.asList(item, i));
                }
            }
            if (!map.containsKey(arr[i])){
                map.put(arr[i], new ArrayList<>());
                map.get(arr[i]).add(i);
            } else {
                map.get(arr[i]).add(i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        List<List<Integer>> res = allPairs(arr, 5);
        for (List<Integer> item : res) {
            for (Integer idx: item) {
                System.out.print(idx);
            }
            System.out.println();
        }
    }
}

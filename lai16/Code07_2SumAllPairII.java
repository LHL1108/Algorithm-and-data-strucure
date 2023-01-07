package lai16;
import java.util.*;





public class Code07_2SumAllPairII {

    public static List<List<Integer>> allPairs(int[] arr, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (arr == null || arr.length < 2) {
            return res;
        }
        Set<List<Integer>> set = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsValue(arr[i])) {
                List<Integer> cur = new ArrayList<>();
                cur.add(arr[i] < target - arr[i] ? arr[i] : target - arr[i]);
                cur.add(arr[i] < target - arr[i] ? target - arr[i] : arr[i]);
                set.add(cur);
            }
            map.put(arr[i], target - arr[i]);
        }

        for (List<Integer> item : set) {
            res.add(item);
        }
        return res;
    }

    public static void main(String[] args) {
        //eg. [2, 1, 3, 2, 4, 3, 4, 2] target=6, return [[2, 4], [3, 3]]
        int[] arr = {2, 1, 3, 2, 4, 3, 4, 2};
        int target = 6;
        List<List<Integer>> res = allPairs(arr, 6);
        for (List<Integer> item : res) {
            for (Integer idx: item) {
                System.out.print(idx);
            }
            System.out.println();
        }
    }
}

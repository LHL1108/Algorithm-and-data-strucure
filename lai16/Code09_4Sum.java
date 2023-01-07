package lai16;
import java.util.HashMap;
import java.util.Map;







public class Code09_4Sum {

    public static boolean exist(int[] arr, int target) {
        if (arr == null || arr.length < 4) {
            return false;
        }
        Map<Integer, Pair> map = new HashMap<>();
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int partSum = arr[i] + arr[j];
                if (map.get(target - partSum) != null && map.get(target - partSum).right < i) {
                    return true;
                } else if (!map.containsKey(partSum) || map.get(partSum).right > j){
                    map.put(partSum, new Pair(i, j));
                }
            }
        }
        return false;
    }

    static class Pair {
        int left;
        int right;
        Pair(int l, int r) {
            left = l;
            right = r;
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 1, 1, 0};
        int target = 3;
        //true
        System.out.println(exist(arr, target));
    }


}

package lai07;
import java.util.ArrayList;
import java.util.List;

/*
[Question]
    we need to get all permutations of a String, eg. "ab" -> ["ab", "ba"]
[Idea]
    we can try DFS because it is a permutation question, draw the recursion tree, for each level, it means which place are we settling,
    for each brand, it means which place do we use to swap with settling place
[Notice]
    we stop add index when it is N, not N+1
    remember to swap back
[Complexity]
    Time:  O(n!)
    Space: O(n)
*/

public class Code04_AllPermutationsI {

    public static List<String> permutations(String input) {
        List<String> res = new ArrayList<>();
        if (input == null) {
            return res;
        }
        char[] arr = input.toCharArray();
        helper(arr, 0, res);
        return res;
    }

    public static void helper(char[] arr, int index, List<String> res) {
        if (index == arr.length) {
            res.add(new String(arr));
            return;
        }

        for (int i = index; i < arr.length; i++) {
            swap(arr, i, index);
            helper(arr, index + 1, res);
            swap(arr, i, index);
        }
    }

    public static void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static void main(String[] args) {
        String input = "abc";
        List<String> res = permutations(input);
        for (String item : res) {
            System.out.println(item);
        }
    }
}

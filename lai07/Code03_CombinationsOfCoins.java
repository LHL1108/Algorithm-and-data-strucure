package lai07;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/*
[Question]
    given a target and coins int array, return all the possible combination of coins to sum to target
[Idea]
    since it is a combination problem, we can try DFS to solve it, each layer is the different kind of coins we are trying
    to use, each branch is the number of this kind of coins we use, we stop when layer is n-1 and we could divided it totally
[Complexity]
    Time:  O(target^n), each layer there are target/n[idx] branches, since n[idx] is finite number, and target is a large number so we ignore n[idx],
            and there are n layers, so total is target^n
    Space: O(n), since it is a recursion problem, so the space is the depth of the recursion tree, which is n
[Notice]
    1.list use .size()
    2.stopping condition is idx == coins.length - 1, no matter we can fill the last kind of coins, we should stop and return,
      so don't mix two condition togetger
    3.we should create a new list rather than add choice directly to the res, otherwise our recover manipulation will have a affection
*/

public class Code03_CombinationsOfCoins {

    public static List<List<Integer>> combinations(int target, int[] coins) {
        List<List<Integer>> res = new LinkedList<>();
        if (coins == null) {
            return res;
        }

        List<Integer> choice = new LinkedList<>();
        comb(target, coins, 0, choice, res);
        return res;
    }

    public static void comb(int targetLeft, int[] coins, int idx, List<Integer> choice, List<List<Integer>> res) {
        if (idx == coins.length - 1) {
            if (targetLeft % coins[coins.length - 1] == 0) {
                choice.add(targetLeft / coins[coins.length - 1]);
                res.add(new ArrayList<Integer>(choice));
                choice.remove(choice.size() - 1);
            }
            return;
        }

        int maxNums = targetLeft / coins[idx];
        for (int i = 0; i <= maxNums; i++) {
            choice.add(i);
            comb(targetLeft - i * coins[idx], coins, idx + 1, choice, res);
            choice.remove(choice.size() - 1);
        }
    }



    public static void main(String[] args) {
        int target = 100;
        int[] coins = {99};
        List<List<Integer>> res = combinations(target, coins);

        for (List<Integer> item : res) {
            for(int num : item) {
                System.out.print(num);
            }
            System.out.println();
            System.out.println("-------------------------------");
        }
    }
}

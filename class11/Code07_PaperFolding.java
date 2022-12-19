package class11;

public class Code07_PaperFolding {
    /*
    要求： 将一张纸条对折两次，中间的折痕是凹痕，左边是凹痕，右边是凸痕，求对折N次的痕迹情况。
    思路：
        题目的本质是中序遍历一颗头节点是凹，左子节点
    */

    public static void printAllFolds(int n) {
        process(1, n, true);
        System.out.println();
    }

    public static void process(int i, int n, boolean down) {
        if (i > n) {
            return;
        }
        process(i + 1, n, true);
        System.out.print(down?"凹":"凸");
        process(i + 1, n, false);
    }



    public static void main(String[] args) {
        int N = 4;
        printAllFolds(N);
        // 凹 凹 凸 凹 凹 凸 凸 凹 凹 凹 凸 凸 凹 凸 凸
    }
}

package class25;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

/*
要求： 实现一个单调栈，对于给定一个数组，返回每个位置的左边和右边距离它最近的比它小的位置
思路：从左向右依次压入元素，如果栈中元素小，直接压，如果栈中元素大，弹出来，弹的过程中记录这个位置的最接近小值位置，分别是弹出元素下面压着的和即将压入的。
     所有值都压完以后，把栈中的剩余值依次弹出，右边界是-1，左边界是下面压着的。分为有重复值和无重复值两种，无重复值塞数，有重复值塞链表。
     链表压入的时候如果和栈顶相同，就连在栈为，弹出时统一处理，即链表中的每一个元素的左右最近都相同。
易错：
    1.注意栈为空和栈为null的区别
    2.在弹出并记录答案的时候，要考虑栈下面为空是，结果为-1的情况
代码：
    异常
        空
        零
    初始化
    处理
        遍历压入
            判断
                弹出
                统计
                    左下
                    右压
            压入
        弹出剩余
            弹出
            统计
                右无
    返回

思路2：当栈中有重复值的时候，栈中元素小直接压，大踢走，等于连到链表末尾，弹出时弹出一整个链表，统一离他们最近的小值分别为下面的和要压入的。
易错2：
    1.取数判断之前要先确定栈中有值
	2.注意赋值的时候，取得链表是下面的链表中的最后一个，不要弄混成当前列表
代码2：
    异常
        空
        零
    初始化
    处理
        遍历压入
            弹出所有不符合条件的
                栈小压大
                右边界为压入
                左边界为-1或下面
                每个结果都相同
            压入
                栈中小
                    包一个链表压
                栈中等
                    连接到链表尾部
        剩余弹出
            右边界为-1
            左边界判断
            每个结果都相同
    返回
*/


public class Code01_MonotonousStack {

    public static int[][] getNearLessNoRepeat(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int p = stack.pop();
                res[p][0] = stack.isEmpty() ? -1 : stack.peek();
                res[p][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int p = stack.pop();
            res[p][0] = stack.isEmpty() ? -1 : stack.peek();
            res[p][1] = -1;
        }
        return res;
    }


    public static int[][] getNearLess(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[][] res = new int[arr.length][2];
        Stack<ArrayList<Integer>> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                ArrayList<Integer> p = stack.pop();
                for (int item : p) {
                    res[item][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                    res[item][1] = i;
                }
            }

            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            } else {
                ArrayList<Integer> l = new ArrayList<>();
                l.add(i);
                stack.push(l);
            }
        }

        while (!stack.isEmpty()) {
            ArrayList<Integer> p = stack.pop();
            for (int item : p) {
                res[item][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                res[item][1] = -1;
            }
        }

        return res;
    }



    // for test
    public static int[] getRandomArrayNoRepeat(int size) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < arr.length; i++) {
            int swapIndex = (int) (Math.random() * arr.length);
            int tmp = arr[swapIndex];
            arr[swapIndex] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }

    // for test
    public static int[] getRandomArray(int size, int max) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    // for test
    public static int[][] rightWay(int[] arr) {
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            int leftLessIndex = -1;
            int rightLessIndex = -1;
            int cur = i - 1;
            while (cur >= 0) {
                if (arr[cur] < arr[i]) {
                    leftLessIndex = cur;
                    break;
                }
                cur--;
            }
            cur = i + 1;
            while (cur < arr.length) {
                if (arr[cur] < arr[i]) {
                    rightLessIndex = cur;
                    break;
                }
                cur++;
            }
            res[i][0] = leftLessIndex;
            res[i][1] = rightLessIndex;
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[][] res1, int[][] res2) {
        if (res1.length != res2.length) {
            return false;
        }
        for (int i = 0; i < res1.length; i++) {
            if (res1[i][0] != res2[i][0] || res1[i][1] != res2[i][1]) {
                return false;
            }
        }

        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int size = 10;
        int max = 20;
        int testTimes = 20000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = getRandomArrayNoRepeat(size);
            int[] arr2 = getRandomArray(size, max);
            if (!isEqual(getNearLessNoRepeat(arr1), rightWay(arr1))) {
                System.out.println("Oops!");
                printArray(arr1);
                break;
            }
            if (!isEqual(getNearLess(arr2), rightWay(arr2))) {
                System.out.println("Oops!");
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}

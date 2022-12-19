package class24;
import java.util.LinkedList;

/*
要求：在一个环形加油站中，gas数组记录了每一站的汽油数，cost数组记录了从上一站到这一站的耗费汽油数，
     返回任意一个从此站出发能跑完全程的站点
思路：gas - cost数组即是净消耗数组，跑的过程即是一个净值累加的过程，若跑完全程需要累加和数组中不出现小于0的值
     即找到从该站出发（即减去前面的累加值）的累加数组的最小值大于0的站点，动态更新最小值的过程可以用滑窗，因为可能从最后一站跑完一圈，所以这个数组需要两倍原数组长度
代码：
    主函数
        获得结果数组
        遍历尝试
            返回第一个可以跑完的数组

    获得函数
        构建累加和数组
            2倍长度
            赋2次原始值
            累加
        创建双端队列
            遍历一遍原数组的依次缩短最小值
        遍历剩余所有站点
            判断该站点可行性并返回至结果中
            更新双端队列
链接：https://leetcode.com/problems/gas-station
易错：
    1.多个初始条件的for循环，只写一个int
    2.踢完后面的和更新前面的中间需要塞值
    3.更新双端队列前面的值不需要考虑其是否为空
*/

public class Code03_GasStation {

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        boolean[]ans = getGoods(gas, cost);
        for (int i = 0; i < gas.length; i++) {
            if (ans[i] == true) {
                return i;
            }
        }
        return -1;
    }

    public static boolean[] getGoods(int[] gas, int[]cost) {
        boolean[] res = new boolean[gas.length];
        int N = gas.length;
        int M = 2 * N;
        int[] c = new int[M];
        for (int i = 0; i < N; i++) {
            c[i] = gas[i] - cost[i];
            c[i + N] = gas[i] - cost[i];
        }
        for (int i = 1; i < M; i++) {
            c[i] = c[i-1] + c[i];
        }

        LinkedList<Integer> qmin = new LinkedList<>();
        int L = 0;
        int R = 0;
        while (R < N) {
            while (!qmin.isEmpty() && c[qmin.peekLast()] >= c[R]) {
                qmin.pollLast();
            }
            qmin.addLast(R);
            R++;
        }
        for (int f = 0, i = 0, j = N; j < M; f = c[i++], j++) {
            if (c[qmin.peekFirst()] - f >= 0) {
                res[i] = true;
            }
            while (!qmin.isEmpty() && c[qmin.peekLast()] >= c[j]) {
                qmin.pollLast();
            }
            qmin.addLast(j);
            if (qmin.peekFirst() == i) {
                qmin.pollFirst();
            }
        }
        return res;
    }
}
package class14;
import java.util.HashSet;

/*
要求： 一串字符串，X代表墙，.代表需要被灯照亮的空位，如 "..X....X.X..",每盏灯的照亮范围为它自己及左右的三个位置，
求照亮全部空位所需的最少灯数。

思路
    每走到一步，就根据当前位置，判断不了就再看后两位，每一步都只看当前位置和后两个位置。
*/


public class Code01_Light {

    public static int minLight2(String road) {
        char[] str = road.toCharArray();
        int i = 0;
        int light = 0;
        while(i < str.length) {
            if (str[i] == 'X') {
                i = i + 1;
            } else {
                light++;
                if (str[i + 1] == 'X') {
                    i = i + 2;
                } else {
                    i = i + 3;
                }
            }
        }
        return light;

    }



    public static int minLight1(String road) {
        if (road == null || road.length() == 0) {
            return 0;
        }
        return process(road.toCharArray(), 0, new HashSet<>());
    }

    // str[index....]位置，自由选择放灯还是不放灯
    // str[0..index-1]位置呢？已经做完决定了，那些放了灯的位置，存在lights里
    // 要求选出能照亮所有.的方案，并且在这些有效的方案中，返回最少需要几个灯
    public static int process(char[] str, int index, HashSet<Integer> lights) {
        if (index == str.length) { // 结束的时候
            for (int i = 0; i < str.length; i++) {
                if (str[i] != 'X') { // 当前位置是点的话
                    if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        } else { // str还没结束
            // i X .
            int no = process(str, index + 1, lights);
            int yes = Integer.MAX_VALUE;
            if (str[index] == '.') {
                lights.add(index);
                yes = process(str, index + 1, lights);
                lights.remove(index);
            }
            return Math.min(no, yes);
        }
    }


    // for test
    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int len = 20;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            String test = randomString(len);
            int ans1 = minLight1(test);
            int ans2 = minLight2(test);
            if (ans1 != ans2) {
                System.out.println("oops!");
            }
        }
        System.out.println("finish!");
    }
}

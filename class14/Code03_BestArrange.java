package class14;
import java.util.Arrays;
import java.util.Comparator;
/*
要求： 每一个会议都有开始时间和结束时间，会议只能一个开完了再开下一个，求不冲突的前提下能安排的最多会议数
思路：将会以根据结束时间升序排序，先看早结束的能不能开，不能就看下一个，能就开，并且更新时间线，直至看完所有会议

代码思路
	定义排序规则
	初始化数据
		会议数
		时间线
	将所有会议根据结束时间排序
	遍历所有会议
		找到能开的
			会议数 + 1
			更新时间线
*/



public class Code03_BestArrange {


    public static class MyComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }

    }

    public static int bestArrange2(Program[] programs) {
        int meetings = 0;
        int timeline = 0;
        Arrays.sort(programs, new MyComparator());
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= timeline) {
                meetings++;
                timeline = programs[i].end;
            }
        }
        return meetings;
    }


    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // 暴力！所有情况都尝试！
    public static int bestArrange1(Program[] programs) {
        if (programs == null || programs.length == 0) {
            return 0;
        }
        return process(programs, 0, 0);
    }

    // 还剩下的会议都放在programs里
    // done之前已经安排了多少会议的数量
    // timeLine目前来到的时间点是什么

    // 目前来到timeLine的时间点，已经安排了done多的会议，剩下的会议programs可以自由安排
    // 返回能安排的最多会议数量
    public static int process(Program[] programs, int done, int timeLine) {
        if (programs.length == 0) {
            return done;
        }
        // 还剩下会议
        int max = done;
        // 当前安排的会议是什么会，每一个都枚举
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= timeLine) {
                Program[] next = copyButExcept(programs, i);
                max = Math.max(max, process(next, done + 1, programs[i].end));
            }
        }
        return max;
    }

    public static Program[] copyButExcept(Program[] programs, int i) {
        Program[] ans = new Program[programs.length - 1];
        int index = 0;
        for (int k = 0; k < programs.length; k++) {
            if (k != i) {
                ans[index++] = programs[k];
            }
        }
        return ans;
    }



    // for test
    public static Program[] generatePrograms(int programSize, int timeMax) {
        Program[] ans = new Program[(int) (Math.random() * (programSize + 1))];
        for (int i = 0; i < ans.length; i++) {
            int r1 = (int) (Math.random() * (timeMax + 1));
            int r2 = (int) (Math.random() * (timeMax + 1));
            if (r1 == r2) {
                ans[i] = new Program(r1, r1 + 1);
            } else {
                ans[i] = new Program(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int programSize = 12;
        int timeMax = 20;
        int timeTimes = 1000000;
        for (int i = 0; i < timeTimes; i++) {
            Program[] programs = generatePrograms(programSize, timeMax);
            if (bestArrange1(programs) != bestArrange2(programs)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}

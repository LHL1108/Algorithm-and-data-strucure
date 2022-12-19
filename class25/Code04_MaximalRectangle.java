package class25;
import java.util.Stack;
/*
链接：https://leetcode.com/problems/maximal-rectangle/
要求：在一个row * col的二维数组中，其中的值非0即1，求其中最大的全部为1的矩型面积
思路：不管这个矩形怎么画，它一定是以其中的某一行为底，往上画的，那么就将其转化成，在以该行为底的前提下的直方图中的最大矩形面积，只要遍历每一行，求出这一行下的最大直方图矩形面积，更新结果即可
代码：
    主函数
        异常
        初始化
        处理
            遍历每一行
                更新该行直方图数组
                求该直方图的最大矩形面积
                更新结果
        返回


    求该直方图下的最大矩形
        异常
        初始化
        处理
            遍历压入
                剔出大于等于的
                    弹出
                    计算左右边界
                    更新最大矩形
                压入
            弹出剩余
                弹出
                计算左右边界
                更新最大矩形
        返回
*/



public class Code04_MaximalRectangle {

    public static int maximalRectangle(char[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == '0') {
                    height[j] = 0;
                } else {
                    height[j] += 1;
                }
            }
            int curArea = maxRecFromBottom(height);
            maxArea = Math.max(maxArea, curArea);
        }

        return maxArea;
    }

    public static int maxRecFromBottom(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
                int p = stack.pop();
                int l = stack.isEmpty() ? -1 : stack.peek();
                int curArea = height[p] * (i - l - 1);
                maxArea = Math.max(maxArea, curArea);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int p = stack.pop();
            int l = stack.isEmpty() ? -1 : stack.peek();
            int curArea = height[p] * (height.length - l - 1);
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }


}

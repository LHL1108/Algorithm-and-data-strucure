package class25;
import java.util.Stack;

/*
链接：https://leetcode.com/problems/largest-rectangle-in-histogram
要求：求一个每一列都高度不一的直方图中，能勾勒出的最大矩形面积
思路：最大矩形不管怎么画，高度一定和某一列是一致的，所以就遍历每一列作为基础，求能向左右延伸出来的最大宽度，相乘即是该高度下的最大面积，遍历完后更新得到去哪聚最大值
代码：
    异常判断
    初始化
    处理
        用单调栈求出每一列的最大宽度
        乘以该高度得到最大面积
        更新面积最大值
    返回
思路2：为了对常数时间进行优化，采用数组代替栈结构，加快运行速度，idx只向当前最后一个元素位置，其实为-1, push -> s[++idx] = value, pop -> value = s[idx--], peek -> s[idx]
*/

// 测试
public class Code03_LargestRectangleInHistogram {

    public static int largestRectangleArea1(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                int p = stack.pop();
                int l = stack.isEmpty() ? -1 : stack.peek();
                int curArea = heights[p] * (i - l - 1);
                maxArea = Math.max(maxArea, curArea);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int p = stack.pop();
            int l = stack.isEmpty() ? -1 : stack.peek();
            int curArea = heights[p] * (heights.length - l - 1);
            maxArea = Math.max(maxArea, curArea);
        }

        return maxArea;
    }


    public static int largestRectangleArea2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int maxArea = 0;
        int N = height.length;
        int[] s = new int[N];
        int idx = -1;
        for (int i = 0; i < N; i++) {
            while (idx != -1 && height[s[idx]] >= height[i]) {
                int p = s[idx--];
                int l = idx == -1 ? -1 : s[idx];
                int curArea = height[p] * (i - l - 1);
                maxArea = Math.max(maxArea, curArea);
            }
            s[++idx] = i;
        }

        while (idx != -1) {
            int p = s[idx--];
            int l = idx == -1 ? -1 : s[idx];
            int curArea = height[p] * (N - l - 1);
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }

}

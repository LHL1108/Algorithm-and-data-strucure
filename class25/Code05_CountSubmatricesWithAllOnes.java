package class25;
/*
链接：https://leetcode.com/problems/count-submatrices-with-all-ones
要求：求一个二维二进制矩阵中有多少个只包含1的子矩阵
思路：依然是遍历每一行做底，累加此情况下有多少个子矩阵，将问题转换为求直方图中的子矩阵个数，依然是通过不同高度为基横向扩展出的最大矩形，借助公式一次性求该矩形下的子矩形个数，需要注意，矩形的高度必须大于该矩形两侧的相对
     较高的矮柱高度，因为哪些高度要以矮柱为基统一算。
易错：
    1.单调栈>=都弹，但可能只有在大于的情况下，统计答案，要多做一个逻辑过滤
    2.选择数组方式自行实现栈功能时，一定要注意idx变化带来的影响，即当弹出后，idx所代表的就不是弹出元素了，而是下面压着的元素
代码：
    主函数
        异常
            空
            零
        初始化
        处理
            分成不同行分别统计累加
                求直方图
                累加该直方图下的矩形个数
        返回


    求某直方图下的矩形数量
        异常
        初始化
        处理
            遍历压入
                弹出大于以及等于的
                在严格大于的情况下统计个数
                    宽度
                        左边界
                            -1
                            正常
                        右边界
                        右 - 左 - 1
                    高度
                        求出左右两边相对较高的那个
                        求出剩余高度
                    总数量
                        高的变化 *  N *（N + 1）/ 2
            剩余弹出
                宽度
                    左边界
                        -1
                        正常
                    右边界
                        height右
                    右 - 左 - 1
                高度
                    求出左边的高度
                    求出剩余高度
                总数量
                    高的变化 *  N *（N + 1）/ 2
        返回
*/


public class Code05_CountSubmatricesWithAllOnes {

    public static int numSubmat(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return 0;
        }
        int ans = 0;
        int[] height = new int[mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                height[j] = mat[i][j] == 0 ? 0 : height[j] + 1;
            }
            ans += numInRecFromBottom(height);
        }
        return ans;
    }

    public static int numInRecFromBottom(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int res = 0;
        int[] s = new int[height.length];
        int idx = -1;
        for (int i = 0; i < height.length; i++) {
            while (idx != -1 && height[s[idx]] >= height[i]) {
                int p = s[idx--];
                if (height[p] > height[i]) {
                    int l = idx == -1 ? -1 : s[idx];
                    int x = nums(i - l - 1);
                    int y = height[p] - Math.max(idx == -1 ? 0 : height[s[idx]], height[i]);
                    res += x * y;
                }
            }
            s[++idx] = i;
        }
        while (idx != -1) {
            int p = s[idx--];
            int l = idx == -1 ? -1 : s[idx];
            int x = nums(height.length - l - 1);
            int y = height[p] - (idx == -1 ? 0 : height[s[idx]]);
            res += x * y;
        }

        return res;
    }

    public static int nums(int n) {
        return n * (n + 1) >> 1;
    }

}

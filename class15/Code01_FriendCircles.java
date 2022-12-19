package class15;



/*
要求： 有一个N*N的矩阵M，代表了N个人之间的关系，M[i][j] = 0 / 1, 代表i和j互相不认识/认识，由于是互相的，所以
      该矩阵关于左上右下对角线对称，求这个N个人中有多少个彼此独立的朋友圈
      本题为leetcode原题, 可以直接通过
      https://leetcode.com/problems/friend-circles/

思路：并查集，初始化大小为N，遍历矩阵右上半部分，遍历i,j位置，如果是1，那么i,和j就合并，最后返回并查集数量
代码思路：

    主函数
        初始化
        遍历右上角
            为1合并

    并查集
        初始化
            祖先
            规模
            数量
            辅助
        构造函数
            初始化变量
            初始化值
        寻找代表函数
            找祖宗
                辅助函数记录值
                向上找
            路径压缩
                更新辅助函数中的爹
        合并函数
            找祖宗
            合并
                祖宗不等
                    f1大
                        2并1
                    f2大
                        1并2
                祖宗等
                    不操作
                sets减少
        集合数量函数
            返回集合数量

*/


public class Code01_FriendCircles {

    public static int findCircleNum(int[][] M) {
        int N = M.length;
        UnionFind uf = new UnionFind(N);
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if(M[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.sets();
    }

    public static class UnionFind{
        private int[] parents;
        private int[] size;
        private int[] help;
        private int sets;

        public UnionFind(int N) {
            parents = new int[N];
            size = new int[N];
            help = new int[N];
            sets = N;
            for (int i = 0; i < N; i++) {
                parents[i] = i;
                size[i] = 1;
            }
        }

        public int find(int i){
            int idx = 0;
            while(i != parents[i]){
                help[idx++] = i;
                i = parents[i];
            }
            for (idx--; idx >= 0; idx--) {
                parents[help[idx]] = i;
            }
            return i;
        }

        public void union(int i, int j){
            int f1 = find(i);
            int f2 = find(j);
            if(f1 != f2) {
                if(size[f1] >= size[f2]){
                    parents[f2] = f1;
                    size[f1] += size[f2];
                } else {
                    parents[f1] = f2;
                    size[f2] += size[f1];
                }
                sets--;
            }
        }

        public int sets(){
            return sets;
        }

    }

}

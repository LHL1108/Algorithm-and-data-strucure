package class16;
import java.util.*;
/*
OJ链接：https://www.lintcode.com/problem/topological-sorting
要求： 返回有向无环图的拓扑序
思路： 返回入度为0的节点，并且将该节点的后继节点的入度-1，再继续返回入度为0的节点
代码思路：
    初始化
        入度表
        零度队列
        结果数组
    明确整个图的入度情况
        创建每个节点即0入度
        处理每个节点的后继的入度
        将符合条件的节点放入零度队列中
    获取结果
        只要0度队列不为空
            弹出0度队列元素
            加入结果
            处理该元素的后继
                入度-1
                如入度变为0则塞入零度队列
     返回
*/

public class Code03_TopologicalOrderBFS {

    // 不要提交这个类
    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph){

        HashMap<DirectedGraphNode, Integer> indegree = new HashMap<>();
        Queue<DirectedGraphNode> zerodegree = new LinkedList<>();
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();

        for (DirectedGraphNode cur : graph) {
            indegree.put(cur, 0);
        }
        for (DirectedGraphNode cur : graph) {
            for (DirectedGraphNode next : cur.neighbors) {
                indegree.put(next, indegree.get(next) + 1);
            }
        }
        for (DirectedGraphNode cur : indegree.keySet()) {
            if (indegree.get(cur) == 0) {
                zerodegree.add(cur);
            }
        }
        while (!zerodegree.isEmpty()) {
            DirectedGraphNode x = zerodegree.poll();
            ans.add(x);
            for (DirectedGraphNode next : x.neighbors) {
                indegree.put(next, indegree.get(next) - 1);
                if (indegree.get(next) == 0) {
                    zerodegree.add(next);
                }
            }
        }
        return ans;
    }

}

package class16;
/*
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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Code03_TopolpgySort {

    public static List<Node> sortedTopology(Graph graph) {
        HashMap<Node, Integer> indegree = new HashMap<>();
        Queue<Node> zerodegree = new LinkedList<>();
        List<Node> ans = new LinkedList<>();

        for (Node cur : graph.nodes.values()) {
            indegree.put(cur, cur.in);
            if (cur.in == 0) {
                zerodegree.add(cur);
            }
        }

        while(!zerodegree.isEmpty()) {
            Node cur = zerodegree.poll();
            ans.add(cur);

            for (Node next : cur.nexts) {
                indegree.put(next, indegree.get(next) - 1);
                if(indegree.get(next) == 0) {
                    zerodegree.add(next);
                }
            }
        }
        return ans;


    }

}

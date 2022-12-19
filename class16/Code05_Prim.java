package class16;
/*
要求： 解释并实现P算法
思路： P算法是一种最小生成树算法，即对一个无向图结构，计算出在确保所有点连接的前提下使边的权重和最小的连接方式。
      P算法的具体做法是指，从无向图的任意一点开始，解锁该节点，再根据权重由小到大的顺序依次考察其连接边，选取第一个不成环的连接，
      进而解锁下一个节点，周而复始，直至所有节点都被解锁。
代码思路：
    创建三个结构
        元素为边的小根堆，根据权重升序排序，作为解锁边集
        节点HashSet，作为解锁点集
        边HashSet，作为结果边集
        遍历每个节点，为了防森林
            如果节点没被解锁
                解锁该点
                解锁该点的所有边
                依次弹出边小根堆中的所有边
                    只要边的后继节点没有被解锁过
                        向结果中加入该边
                        解锁该后继点
                        解锁该后继点的所有边
*/

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Code05_Prim {

    public static class EdgeComparator implements Comparator<Edge>{
        @Override
        public int compare(Edge e1, Edge e2){
            return e1.weight - e2.weight;
        }
    }

    public static Set<Edge> PrimMST(Graph graph) {
        PriorityQueue<Edge> edgeset = new PriorityQueue<>(new EdgeComparator());
        HashSet<Node> nodeset = new HashSet<>();
        Set<Edge> res = new HashSet<>();

        for (Node cur: graph.nodes.values()) {
            if (!nodeset.contains(cur)) {
                nodeset.add(cur);
                for(Edge e : cur.edges){
                    edgeset.add(e);
                }
                while(!edgeset.isEmpty()) {
                    Edge e = edgeset.poll();
                    Node next = e.to;
                    if(!nodeset.contains(next)){
                        res.add(e);
                        nodeset.add(next);
                        for(Edge f : next.edges){
                            edgeset.add(f);
                        }
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("finish");
    }


}

package class16;
/*
要求：简要解释K算法并用代码实现
思路：K算法是解决最小生成树问题的一种算法，即在一个图结构中，怎样以最小权重和的方式连接所有节点。
     K算法的具体思路是:按照权重从小到大依次考察所有边，不会形成环就保留，会形成环，就放弃，属于一种贪心算法
代码思路：
    并查集
        属性
        初始化方法
        创建并查集
        判断是否为同一集合
        寻找代表元素


    比较器
        对边进行比较
        权重小的在前

    主函数
        输入graphy 输出Set<edge>
        初始化
            并查集
            小根堆
            结果
        初始赋值
            并查集赋值
            小根堆加入
        只要小根堆不为空
            弹出堆顶
            如果边的两边元素不是同一集合
                结果加入该边
                并查集加入该边
*/


import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class Code04_Kruskal {

    public static class UnionFind{
        private HashMap<Node, Node> fatherMap;
        private HashMap<Node, Integer> sizeMap;

        public UnionFind(){
            fatherMap = new HashMap<Node, Node>();
            sizeMap = new HashMap<Node, Integer>();
        }

        public void makeSet(Collection<Node> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node findFather(Node cur){
            Stack<Node> stack = new Stack<>();
            while (cur != fatherMap.get(cur)) {
                cur = fatherMap.get(cur);
                stack.add(cur);
            }
            while (!stack.isEmpty()){
                Node n = stack.pop();
                fatherMap.put(n, cur);
            }
            return cur;
        }

        public boolean isSameSet(Node a, Node b) {
            Node f1 = findFather(a);
            Node f2 = findFather(b);
            return f1 == f2;
        }

        public void Union(Node a, Node b){
            if (a == null || b == null) {
                return;
            }
            Node f1 = findFather(a);
            Node f2 = findFather(b);
            if (f1 != f2) {
                int s1 = sizeMap.get(f1);
                int s2 = sizeMap.get(f1);
                if(s1 >= s2) {
                    fatherMap.put(f2, f1);
                    sizeMap.put(f1, s1 + s2);
                    sizeMap.remove(f2);
                } else {
                    fatherMap.put(f1, f2);
                    sizeMap.put(f2, s1 + s2);
                    sizeMap.remove(f1);
                }
            }
        }
    }

    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge e1, Edge e2){
            return e1.weight - e2.weight;
        }
    }

    public static Set<Edge> kruskalMST(Graph graph){
        UnionFind uf = new UnionFind();
        PriorityQueue<Edge> heap = new PriorityQueue<Edge>(new EdgeComparator());
        Set<Edge> res = new HashSet<Edge>();

        uf.makeSet(graph.nodes.values());
        for (Edge edge :graph.edges) {
            heap.add(edge);
        }

        while (!heap.isEmpty()) {
            Edge e = heap.poll();
            boolean sameset = uf.isSameSet(e.from, e.to);
            if (!sameset) {
                res.add(e);
                uf.Union(e.from, e.to);
            }
        }
        return res;
    }
}
package class16;
/*
要求：解释并实现Dijkstra算法
思路：Dijkstra算法是用来解决这样一种问题，给定一个有向图的起始点，求每一个能走到的点的最小权重和
     具体解决思路是，有一个记录每一个节点最小路径和的HashMap，和记录处理完成节点的Set，计算其所有后继点的最小权重和将其放入或更新至HashMap中，将原节点封存，继续看HashMap中有哪些解封点继续处理，循环往复。
代码：
    主方法
        初始化
            距离哈希表
            判断过的节点哈希set
        初始处理
            哈希表放入初始节点
            通过getmin方法从哈希表中获取可用最小节点
        只要可用最小节点不为空
            获取其距离
            遍历每条边
                获取其后继节点
                如果该后继节点在表里没有
                    加入其距离
                如果有了
                    对比后更新其距离
            处理过节点表加入该节点
            通过getmin获取下一个节点
        返回

    获取最小可用节点方法
        初始化
            最小节点为空
            距离为最大
        对于哈希表中的每一个对
            获取节点和距离
            如果节点没有被处理过且距离更小
                最小节点更新
                距离更新
        返回最小节点
*/



import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

public class Code06_Dijkstra {

    public static HashMap<Node, Integer> Dijkstra1(Node from) {
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        HashSet<Node> selectedNodes = new HashSet<>();

        distanceMap.put(from, 0);
        Node minNode = getMinNodeNotSelected(distanceMap, selectedNodes);

        while(minNode != null){
            int distance = distanceMap.get(minNode);
            for(Edge e : minNode.edges){
                Node n = e.to;
                if(!distanceMap.containsKey(n)){
                    distanceMap.put(n, distance + e.weight);
                } else {
                    distanceMap.put(n, Math.min(distanceMap.get(n), distance + e.weight));
                }
            }
            selectedNodes.add(minNode);
            minNode = getMinNodeNotSelected(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    public static Node getMinNodeNotSelected(HashMap<Node, Integer> distanceMap, HashSet<Node> selectedNodes){
        Node minNode = null;
        int mindistance = Integer.MAX_VALUE;
        for (Entry<Node, Integer> entry : distanceMap.entrySet()){
            Node node = entry.getKey();
            int distance = entry.getValue();
            if(!selectedNodes.contains(node) && distance < mindistance) {
                minNode = node;
                mindistance = distance;
            }
        }
        return minNode;
    }



/*
主函数
    初始化
        加强堆
        结果
    赋值
        加强堆加入首个节点
    只要加强堆不为空
        弹出堆顶
        获取节点和距离
        对于节点的每条边
            更新该边后继节点的路径值
        将更新后数据放入结果
    返回

NodeRecord
    节点
    距离

加强堆
    属性创建
        实际堆结构，节点集合
        辅助表
        距离表
        堆大小
    初始化
        堆结构
        辅助表
        距离表
        堆大小
    是否为空
        size是否为0
    是否加入过
        辅助表中有这个元素
    是否在堆内
        是否加入过且值非-1
    弹出操作
        创建堆顶的NodeRecord
        把剩下结构调对
            交换首末
            更新表格
                heapIndexMap
                    值赋-1
                distanceMap
                    删除记录
            size - 1
            heapify
    更新函数
        添加
            如果没加入过
                堆的size位置加入
                辅助表加入
                距离表加入
                InsertHeapify
        更新
            如果在堆中
                距离表更新
                当前位置InsertHeapify
        忽视
            没中上两种
                不需要任何操作
    InsertHeapify
        只要index位置比他爹的距离小
            交换两个节点
            更新index
    Heapify
        计算左孩子位置
        只要左孩子不越界
            初步确定左右子中小的坐标位置
            计算父左右中的最小
            如果父是最小跳出循环
            否则交换父和最小
            更新index
            重新计算left
    交换函数
        更新辅助表
        更新nodes

*/


    public static class NodeRecord{
        public Node node;
        public int distance;

        public NodeRecord(Node n, int d) {
            node = n;
            distance = d;
        }
    }

    public static class NodeHeap{
        private Node[] nodes;
        private HashMap<Node, Integer> indexMap;
        private HashMap<Node, Integer> distanceMap;
        private int size;

        public NodeHeap(int n){
            nodes = new Node[n];
            indexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            size = 0;
        }

        private void swap(int i, int j) {
            indexMap.put(nodes[i], j);
            indexMap.put(nodes[j], i);
            Node tmp = nodes[i];
            nodes[i] = nodes[j];
            nodes[j] = tmp;
        }

        public boolean isEmpty(){
            return size == 0;
        }

        private boolean isInsert(Node node) {
            return indexMap.containsKey(node);
        }

        public NodeRecord pop(){
            NodeRecord record = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));

            swap(0, size - 1);
            indexMap.put(nodes[size - 1], -1);
            distanceMap.remove(nodes[size - 1]);
            nodes[size - 1] = null;
            heapify(0, --size);
            return record;
        }

        private void heapify(int index, int size) {
            int left = 2 * index + 1;
            while (left < size) {
                int smallest = (left + 1 < size) && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left]) ? left + 1 : left;
                smallest = distanceMap.get(nodes[index]) < distanceMap.get(nodes[smallest]) ? index : smallest;
                if (smallest == index){
                    break;
                }
                swap(index, smallest);
                index = smallest;
                left = 2 * index + 1;
            }
        }

        private void insertHeapify(Node node, int idx) {
            while (distanceMap.get(nodes[idx]) < distanceMap.get(nodes[(idx-1)/2])) {
                swap(idx, (idx - 1) / 2);
                idx = (idx - 1 ) / 2;
            }
        }

        private void addOrUpdateOrIgnore(Node node, int distance) {
            if (!isInsert(node)) {
                distanceMap.put(node, distance);

                nodes[size] = node;
                indexMap.put(node, size);
                insertHeapify(node, size++);
            }
            if (isInsert(node) && distanceMap.get(node) != -1) {
                distanceMap.put(node, Math.min(distance, distanceMap.get(node)));
                insertHeapify(node, indexMap.get(node));
            }
        }
    }

    public static HashMap<Node, Integer> dijkstra2(Node head, int size){
        NodeHeap nodeHeap = new NodeHeap(size);
        HashMap<Node, Integer> res = new HashMap<>();
        nodeHeap.addOrUpdateOrIgnore(head, 0);
        while (!nodeHeap.isEmpty()) {
            NodeRecord record = nodeHeap.pop();
            Node cur = record.node;
            int distance = record.distance;
            for (Edge e : cur.edges) {
                Node n = e.to;
                int newDistance = distance + e.weight;
                nodeHeap.addOrUpdateOrIgnore(n, newDistance);
            }
            res.put(cur, distance);
        }
        return res;
    }
}

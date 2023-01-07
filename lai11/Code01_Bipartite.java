package lai11;
import java.util.*;
/*
[question]
    judge if a graph is a Bipartite, which means all nodes belong to two kind of nodes, each node points to another kind of node
[idea]
    for each node, runs a BST and color it (represent with give it type 0 or 1)
    there are three situation
        visited
            check the type
                right -> skip
                wrong -> return false
        not visited
            add to queue
            give the type
            put it into visited

        1 - 2
          /
        3 - 4
[complexity]
    time: O(V * (V + E)) O(V^2)
    space: O(V), in the stack
[notice]
    why try each node to run BST, because it might has several graph parts, they are independent
    Queue -> LinkedList
    Queue: offer, poll
    Map: put, get
    in BST, don't forget to add element to queue
    in graph, most of time, we should consider visited
    reset visited each round
*/

public class Code01_Bipartite {



    public static boolean isBipartite(List<GraphNode> graph) {
        if (graph == null || graph.size() == 0) {
            return false;
        }

        for (GraphNode node : graph) {
            Map<GraphNode, Integer> visited = new HashMap<>();
            if (checkBipartite(node, visited) == false) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkBipartite(GraphNode node, Map<GraphNode, Integer>visited) {
        Queue<GraphNode> queue = new LinkedList<>();
        queue.offer(node);
        visited.put(node, 0);
        while (!queue.isEmpty()){
            GraphNode item = queue.poll();
            int expectColor = visited.get(item) == 0 ? 1 : 0;
            for (GraphNode n : item.neighbors) {
                if (visited.get(n) == null) {
                    queue.offer(n);
                    visited.put(n, expectColor);
                } else {
                    if (visited.get(n) != expectColor) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    public static class GraphNode {
        public int key;
        public List<GraphNode> neighbors;
        public GraphNode(int key) {
            this.key = key;
            this.neighbors = new ArrayList<GraphNode>();
        }
    }

  /*
        1 - 2
        | /
        3 - 4
   */
    public static void main(String[] args) {
        GraphNode one = new GraphNode(1);
        GraphNode two = new GraphNode(2);
        GraphNode three = new GraphNode(3);
        GraphNode four = new GraphNode(4);
        one.neighbors.add(two);
        one.neighbors.add(three);
        two.neighbors.add(one);
        two.neighbors.add(three);
        three.neighbors.add(one);
        three.neighbors.add(two);
        three.neighbors.add(four);
        four.neighbors.add(three);
        List<GraphNode> graph = new ArrayList<>();
        graph.add(one);
        graph.add(two);
        graph.add(three);
        graph.add(four);
        // false;
        System.out.println(isBipartite(graph));

    }
}

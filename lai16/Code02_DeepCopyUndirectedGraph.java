package lai16;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
[question]
    deep copy a undirected graph
[idea]
    use DFS to put the relationship to map first
    than link
[complexity]
    time: O(E + V), graph normal complexity
    space: O(E + V), copy
[notice]
    map.containsKey and map.get() == null are different
    use map.get(seed) to get copy rather than create a new one
    in DSF, we only enter in when we just create
*/


public class Code02_DeepCopyUndirectedGraph {

    public static List<GraphNode> copy(List<GraphNode> graph) {
        if (graph == null) {
            return null;
        }
        Map<GraphNode, GraphNode> map = new HashMap<>();
        for (GraphNode node : graph) {
            if (!map.containsKey(node)) {
                map.put(node, new GraphNode(node.key));
                DFS(node, map);
            }
        }
        return new ArrayList<GraphNode>(map.values());
    }

    public static void DFS(GraphNode seed, Map<GraphNode, GraphNode> map){
        GraphNode copy = map.get(seed);
        for (GraphNode child : seed.neighbors) {
            if (!map.containsKey(child)) {
                map.put(child, new GraphNode(child.key));
                DFS(child, map);
            }
            copy.neighbors.add(map.get(child));
        }
    }

      static class GraphNode {
        public int key;
        public List<GraphNode> neighbors;
        public GraphNode(int key) {
          this.key = key;
          this.neighbors = new ArrayList<GraphNode>();
        }
      }

}

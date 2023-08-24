import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class MainDijkstra2 {
  public static class Node implements Comparable<Node> {
    double id;
    double x;
    double y;
    double type;
    double radius;
    double distance;

    public Node(double id, double x, double y, double type, double radius) {
      this.id = id;
      this.x = x;
      this.y = y;
      this.type = type;
      this.radius = radius;
      this.distance = Double.MAX_VALUE;
    }

    @Override
    public int compareTo(Node other) {
      return Double.compare(this.distance, other.distance);
    }
  }

  public static void main(String[] args) {
    ArrayList<Node> nodeList = new ArrayList<Node>();
    nodeList.add(new Node(-1.0, 0.0, 0.0, -1.0, 0.0));
    nodeList.add(new Node(27.0, 3520.194044, -3475.335853, 2.0, 18000.0));
    nodeList.add(new Node(20.0, 14632.062552, -22565.122004, 2.0, 18000.0));
    nodeList.add(new Node(29.0, 14779.024225, 14687.490119, 2.0, 18000.0));
    nodeList.add(new Node(12.0, 18110.242766, -2442.03326, 1.0, 9500.0));
    nodeList.add(new Node(11.0, 20033.540599, -10025.814796, 1.0, 9500.0));
    nodeList.add(new Node(13.0, 23387.727288, 4666.693098, 1.0, 9500.0));
    nodeList.add(new Node(1.0, 24780.788807, -3837.205573, 0.0, 6500.0));
    nodeList.add(new Node(0.0, 29261.266231, 371.80014, 0.0, 6500.0));
    nodeList.add(new Node(10.0, 29711.978351, -12473.639713, 1.0, 9500.0));
    nodeList.add(new Node(2.0, 31062.148778, -6090.876171, 0.0, 6500.0));
    nodeList.add(new Node(14.0, 31117.449532, 11665.350778, 1.0, 9500.0));
    nodeList.add(new Node(-2.0, 31500.759029, 0, -2.0, 0));
    List<Node> optimalPath = findOptimalPath(nodeList);
    System.out.println("最终结果:");
    for (Node node : optimalPath) {
      System.out.println(node.id);
    }
  }

  public static List<Node> findOptimalPath(ArrayList<Node> nodeList) {
    List<List<Node>> allPaths = new ArrayList<>(); // Store all possible paths
    List<Node> optimalPath = new ArrayList<>();
    Node startNode = nodeList.get(0);
    Node endNode = nodeList.get(nodeList.size() - 1);

    Map<Node, Double> distances = new HashMap<>();
    Map<Node, Node> parentNodes = new HashMap<>();
    PriorityQueue<Node> pq = new PriorityQueue<>();

    for (Node node : nodeList) {
      distances.put(node, Double.MAX_VALUE);
    }

    distances.put(startNode, 0.0);
    pq.add(startNode);

    while (!pq.isEmpty()) {
      Node currentNode = pq.poll();

      if (currentNode == endNode) {
        List<Node> currentPath = new ArrayList<>();
        Node node = currentNode;
        while (node != null) {
          currentPath.add(0, node);
          node = parentNodes.get(node);
        }
        allPaths.add(currentPath);
      }

      for (Node neighbor : nodeList) {
        if (isReachable(currentNode, neighbor)) {
          double distance = distances.get(currentNode)
              + Math.sqrt(Math.pow(neighbor.x - currentNode.x, 2) + Math.pow(neighbor.y - currentNode.y, 2));
          if (distance < distances.get(neighbor)) {
            distances.put(neighbor, distance);
            parentNodes.put(neighbor, currentNode);
            pq.add(neighbor);
          }
        }
      }
    }
    System.out.println("==========所有路径START==========");
    int index = 1;
    for (List<Node> path : allPaths) {
      System.out.println("第" + index + "条路径:");
      for (Node node : path) {
        System.out.println(node.id);
      }
      index++;
    }
    System.out.println("==========所有路径END==========");
    int minNodeCount = Integer.MAX_VALUE;
    for (List<Node> path : allPaths) {
      if (path.size() < minNodeCount) {
        minNodeCount = path.size();
        optimalPath = path;
      }
    }
    // 如果没有找到合适的路径，就返回全部节点
    if(optimalPath.size() == 0){
      optimalPath = nodeList;
    }
    return optimalPath;
  }

  public static boolean isReachable(Node node1, Node node2) {
    double distance = Math.sqrt(Math.pow(node2.x - node1.x, 2) + Math.pow(node2.y - node1.y, 2));
    double sumOfRadii = node1.radius + node2.radius;
    return distance <= sumOfRadii;
  }
}
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class MainDijkstra3 {
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
    nodeList.add(new Node(123.0, 2884.057987, -2594.196071, 2.0, 5000.0));
    nodeList.add(new Node(115.0, 6653.589907, -9673.443718, 2.0, 5000.0));
    nodeList.add(new Node(126.0, 6767.271977, 5798.213931, 2.0, 5000.0));
    nodeList.add(new Node(124.0, 12611.606636, -2249.069465, 2.0, 5000.0));
    nodeList.add(new Node(125.0, 15882.343481, 8042.086324, 2.0, 5000.0));
    nodeList.add(new Node(52.0, 17537.067073, -6485.505189, 1.0, 3000.0));
    nodeList.add(new Node(50.0, 17687.115143, -1076.001731, 1.0, 3000.0));
    nodeList.add(new Node(49.0, 18684.436822, 3241.219931, 1.0, 3000.0));
    nodeList.add(new Node(47.0, 19847.436037, 7269.419812, 1.0, 3000.0));
    nodeList.add(new Node(53.0, 20529.338837, -8277.741803, 1.0, 3000.0));
    nodeList.add(new Node(48.0, 22476.71552, 1515.051088, 1.0, 3000.0));
    nodeList.add(new Node(51.0, 22770.328722, -3817.829862, 1.0, 3000.0));
    nodeList.add(new Node(18.0, 24273.116599, 715.684308, 0.0, 2000.0));
    nodeList.add(new Node(46.0, 24301.646146, 5933.075597, 1.0, 3000.0));
    nodeList.add(new Node(19.0, 24728.570656, -2315.569032, 0.0, 2000.0));
    nodeList.add(new Node(55.0, 25202.926626, -9012.458293, 1.0, 3000.0));
    nodeList.add(new Node(54.0, 25729.262403, -3924.040692, 1.0, 3000.0));
    nodeList.add(new Node(17.0, 26182.429556, 3100.454822, 0.0, 2000.0));
    nodeList.add(new Node(16.0, 27513.785053, 559.345791, 0.0, 2000.0));
    nodeList.add(new Node(15.0, 28247.455258, -2354.415282, 0.0, 2000.0));
    nodeList.add(new Node(14.0, 28857.364239, 3317.822613, 0.0, 2000.0));
    nodeList.add(new Node(41.0, 29192.849674, 6481.171183, 1.0, 3000.0));
    nodeList.add(new Node(56.0, 30729.538198, -5628.351559, 1.0, 3000.0));
    nodeList.add(new Node(12.0, 30944.111254, 734.959559, 0.0, 2000.0));
    nodeList.add(new Node(13.0, 31517.1516, -2549.548095, 0.0, 2000.0));
    nodeList.add(new Node(5.0, 31755.616255, 3716.682171, 0.0, 2000.0));
    nodeList.add(new Node(10.0, 33654.065212, -4419.85115, 0.0, 2000.0));
    nodeList.add(new Node(11.0, 34090.659219, -943.879617, 0.0, 2000.0));
    nodeList.add(new Node(4.0, 34173.861603, 1979.352765, 0.0, 2000.0));
    nodeList.add(new Node(2.0, 34374.857352, 4945.88377, 0.0, 2000.0));
    nodeList.add(new Node(36.0, 34898.19215, 7052.446779, 1.0, 3000.0));
    nodeList.add(new Node(58.0, 35237.372244, -7409.468718, 1.0, 3000.0));
    nodeList.add(new Node(9.0, 35739.0965, -3008.60168, 0.0, 2000.0));
    nodeList.add(new Node(8.0, 36167.214122, -6233.600241, 0.0, 2000.0));
    nodeList.add(new Node(0.0, 37065.049941, 5973.47052, 0.0, 2000.0));
    nodeList.add(new Node(3.0, 37109.557741, -257.769597, 0.0, 2000.0));
    nodeList.add(new Node(1.0, 37230.012669, 2745.97306, 0.0, 2000.0));
    nodeList.add(new Node(7.0, 38941.271408, -4880.821129, 0.0, 2000.0));
    nodeList.add(new Node(6.0, 38968.899766, -1866.683896, 0.0, 2000.0));
    nodeList.add(new Node(35.0, 40355.165565, 5062.720293, 1.0, 3000.0));
    nodeList.add(new Node(32.0, 40797.960205, -7763.659599, 1.0, 3000.0));
    nodeList.add(new Node(34.0, 42242.210681, 755.417715, 1.0, 3000.0));
    nodeList.add(new Node(33.0, 43123.277429, -3220.11268, 1.0, 3000.0));
    nodeList.add(new Node(24.0, 43338.149005, 9129.573204, 1.0, 3000.0));
    nodeList.add(new Node(-2.0, 31300.839918, 0, -2.0, 0));
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
    // 如果没有找到合适的路径，按照需求重新寻找最远可达节点
    if (optimalPath.size() == 0) {
      List<Node> farthestReachableNodes = new ArrayList<>();
      Node currentNode = startNode;

      while (true) {
        Node farthestReachableNode = null;
        double maxDistance = 0.0;

        for (Node neighbor : nodeList) {
          if (isReachable(currentNode, neighbor)) {
            double distance = Math
                .sqrt(Math.pow(neighbor.x - currentNode.x, 2) + Math.pow(neighbor.y - currentNode.y, 2));
            if (distance > maxDistance) {
              maxDistance = distance;
              farthestReachableNode = neighbor;
            }
          }
        }

        if (farthestReachableNode != null) {
          farthestReachableNodes.add(farthestReachableNode);
          currentNode = farthestReachableNode;
        } else {
          break;
        }
      }
      farthestReachableNodes.add(0, startNode);
      farthestReachableNodes.add(endNode);
      if (farthestReachableNodes.size() == 2) {
        return nodeList;
      } else {
        return farthestReachableNodes;
      }
    }
    return optimalPath;
  }

  public static boolean isReachable(Node node1, Node node2) {
    double distance = Math.sqrt(Math.pow(node2.x - node1.x, 2) + Math.pow(node2.y - node1.y, 2));
    double sumOfRadii = node1.radius + node2.radius;
    return distance <= sumOfRadii;
  }
}
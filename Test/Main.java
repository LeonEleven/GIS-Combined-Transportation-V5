// 有如下代码，现在实现一个方法，使得nodeList的第一项为起点，最后一项为终点，经过中间其他点的最优路径，两个点之间是否可达需满足两点x,y坐标距离小于两点radius之和，如果下一个点是终点，可以不满足这个可达条件。用Dijkstra算法实现。
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// public class Main {
//     public static class Node {
//         double id;
//         double x;
//         double y;
//         double type;
//         double radius;

//         public Node(double id, double x, double y, double type, double radius) {
//             this.id = id;
//             this.x = x;
//             this.y = y;
//             this.type = type;
//             this.radius = radius;
//         }
//     }

//     public static void main(String[] args) {
//         ArrayList<Node> nodeList = new ArrayList<Node>();
//         nodeList.add(new Node(-1.0, 0.0, 0.0, -1.0, 0.0));
//         nodeList.add(new Node(27.0, 3338.124385, -3650.568584, 2.0, 10000.0));
//         nodeList.add(new Node(12.0, 17961.904235, -3363.690793, 1.0, 5000.0));
//         nodeList.add(new Node(13.0, 23595.527244, 3466.252479, 1.0, 5000.0));
//         nodeList.add(new Node(1.0, 24552.498702, -5097.690458, 0.0, 3000.0));
//         nodeList.add(new Node(0.0, 29242.073209, -1122.983175, 0.0, 3000.0));
//         nodeList.add(new Node(2.0, 30710.573589, -7669.193506, 0.0, 3000.0));
//         nodeList.add(new Node(5.0, 32210.191349, 1451.104589, 0.0, 3000.0));
//         nodeList.add(new Node(3.0, 35216.531331, -3476.630071, 0.0, 3000.0));
//         nodeList.add(new Node(6.0, 38730.315414, 4390.156436, 1.0, 5000.0));
//         nodeList.add(new Node(7.0, 43182.007297, -1182.929677, 1.0, 5000.0));
//         nodeList.add(new Node(8.0, 44524.764287, -9737.083218, 1.0, 5000.0));
//         nodeList.add(new Node(-2.0, 48583.385114, 0, -2.0, 0.0));
// }



import java.util.*;

public class Main {
    public static class Node {
        double id;
        double x;
        double y;
        double type;
        double radius;

        public Node(double id, double x, double y, double type, double radius) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.type = type;
            this.radius = radius;
        }
    }

    private static double calculateDistance(Node node1, Node node2) {
        double dx = node2.x - node1.x;
        double dy = node2.y - node1.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    private static boolean isReachable(Node node1, Node node2) {
        double distance = calculateDistance(node1, node2);
        if (node2.type != -2.0 && distance > node1.radius + node2.radius) {
            return false;
        }
        return true;
    }

    private static Node findMinDistanceNode(Map<Node, Double> distanceMap, Set<Node> unvisitedNodes) {
        Node minNode = null;
        double minDistance = Double.MAX_VALUE;
        for (Node node : unvisitedNodes) {
            double distance = distanceMap.getOrDefault(node, Double.MAX_VALUE);
            if (distance < minDistance) {
                minDistance = distance;
                minNode = node;
            }
        }
        return minNode;
    }

    public static List<Node> findOptimalPath(List<Node> nodeList) {
        Map<Node, List<Node>> adjacencyMap = new HashMap<>();
        for (Node node1 : nodeList) {
            List<Node> neighbors = new ArrayList<>();
            for (Node node2 : nodeList) {
                if (node1 != node2 && isReachable(node1, node2)) {
                    neighbors.add(node2);
                }
            }
            adjacencyMap.put(node1, neighbors);
        }

        Map<Node, Double> distanceMap = new HashMap<>();
        Map<Node, Node> previousNodeMap = new HashMap<>();
        Set<Node> unvisitedNodes = new HashSet<>();

        for (Node node : nodeList) {
            distanceMap.put(node, Double.MAX_VALUE);
            unvisitedNodes.add(node);
        }

        distanceMap.put(nodeList.get(0), 0.0);

        while (!unvisitedNodes.isEmpty()) {
            Node currentNode = findMinDistanceNode(distanceMap, unvisitedNodes);
            unvisitedNodes.remove(currentNode);
            List<Node> neighbors = adjacencyMap.get(currentNode);

            if (neighbors == null) {
                continue;
            }

            for (Node neighbor : neighbors) {
                double currentDistance = distanceMap.get(currentNode);
                double newDistance = currentDistance + calculateDistance(currentNode, neighbor);
                if (newDistance < distanceMap.get(neighbor)) {
                    distanceMap.put(neighbor, newDistance);
                    previousNodeMap.put(neighbor, currentNode);
                }
            }
        }

        List<Node> optimalPath = new ArrayList<>();
        Node currentNode = nodeList.get(nodeList.size() - 1);
        optimalPath.add(currentNode);

        while (!currentNode.equals(nodeList.get(0))) {
            Node previousNode = previousNodeMap.get(currentNode);
            if (previousNode == null || optimalPath.contains(previousNode)) {
                break;
            }
            optimalPath.add(previousNode);
            currentNode = previousNode;
        }

        Collections.reverse(optimalPath);

        return optimalPath;
    }

    public static void main(String[] args) {
        List<Node> nodeList = new ArrayList<>();
        nodeList.add(new Node(-1.0, 0.0, 0.0, -1.0, 0.0));
        nodeList.add(new Node(27.0, 3338.124385, -3650.568584, 2.0, 10000.0));
        nodeList.add(new Node(12.0, 17961.904235, -3363.690793, 1.0, 5000.0));
        nodeList.add(new Node(13.0, 23595.527244, 3466.252479, 1.0, 5000.0));
        nodeList.add(new Node(1.0, 24552.498702, -5097.690458, 0.0, 3000.0));
        nodeList.add(new Node(0.0, 29242.073209, -1122.983175, 0.0, 3000.0));
        nodeList.add(new Node(2.0, 30710.573589, -7669.193506, 0.0, 3000.0));
        nodeList.add(new Node(5.0, 32210.191349, 1451.104589, 0.0, 3000.0));
        nodeList.add(new Node(3.0, 35216.531331, -3476.630071, 0.0, 3000.0));
        nodeList.add(new Node(6.0, 38730.315414, 4390.156436, 1.0, 5000.0));
        nodeList.add(new Node(7.0, 43182.007297, -1182.929677, 1.0, 5000.0));
        nodeList.add(new Node(8.0, 44524.764287, -9737.083218, 1.0, 5000.0));
        nodeList.add(new Node(-2.0, 48583.385114, 0, -2.0, 0.0));

        List<Node> optimalPath = findOptimalPath(nodeList);

        for (Node node : optimalPath) {
            System.out.println("Node ID: " + node.id + ", X: " + node.x + ", Y: " + node.y);
        }
    }
}

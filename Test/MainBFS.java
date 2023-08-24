import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class MainBFS {
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

    public static void main(String[] args) {
        ArrayList<Node> nodeList = new ArrayList<Node>();
        nodeList.add(new Node(-1.0, 0.0, 0.0, -1.0, 0.0));
        nodeList.add(new Node(27.0, 3338.124385, -3650.568584, 2.0, 100000.0));
        nodeList.add(new Node(12.0, 17961.904235, -3363.690793, 1.0, 50000.0));
        nodeList.add(new Node(13.0, 23595.527244, 3466.252479, 1.0, 50000.0));
        nodeList.add(new Node(1.0, 24552.498702, -5097.690458, 0.0, 30000.0));
        nodeList.add(new Node(0.0, 29242.073209, -1122.983175, 0.0, 30000.0));
        nodeList.add(new Node(2.0, 30710.573589, -7669.193506, 0.0, 30000.0));
        nodeList.add(new Node(5.0, 32210.191349, 1451.104589, 0.0, 30000.0));
        nodeList.add(new Node(3.0, 35216.531331, -3476.630071, 0.0, 30000.0));
        nodeList.add(new Node(6.0, 38730.315414, 4390.156436, 1.0, 50000.0));
        nodeList.add(new Node(7.0, 43182.007297, -1182.929677, 1.0, 50000.0));
        nodeList.add(new Node(8.0, 44524.764287, -9737.083218, 1.0, 50000.0));
        nodeList.add(new Node(-2.0, 48583.385114, 0, -2.0, 0.0));
        List<Node> optimalPath = findOptimalPath(nodeList);
        System.out.println("Optimal Path:");
        for (Node node : optimalPath) {
            System.out.println(node.id);
        }
    }

    public static List<Node> findOptimalPath(ArrayList<Node> nodeList) {
        List<Node> optimalPath = new ArrayList<>();
        Node startNode = nodeList.get(0);
        Node endNode = nodeList.get(nodeList.size() - 1);

        Map<Node, Boolean> visited = new HashMap<>();
        Map<Node, Node> parentNodes = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        for (Node node : nodeList) {
            visited.put(node, false);
        }

        queue.add(startNode);
        visited.put(startNode, true);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if (currentNode == endNode) {
                break;
            }

            for (Node neighbor : nodeList) {
                if (!visited.get(neighbor) && (neighbor == endNode || isReachable(currentNode, neighbor))) {
                    queue.add(neighbor);
                    visited.put(neighbor, true);
                    parentNodes.put(neighbor, currentNode);
                }
            }
        }

        if (visited.get(endNode)) {
            Node node = endNode;
            while (node != null) {
                optimalPath.add(0, node);
                node = parentNodes.get(node);
            }
        }

        return optimalPath;
    }

    public static boolean isReachable(Node node1, Node node2) {
        double distance = Math.sqrt(Math.pow(node2.x - node1.x, 2) + Math.pow(node2.y - node1.y, 2));
        System.out.println("distance:"  + distance);
        double sumOfRadii = node1.radius + node2.radius;
        System.out.println("sumOfRadii:"  + sumOfRadii);

        return distance <= sumOfRadii;
    }
}

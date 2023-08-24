// 有如下代码，现在实现一个方法，使得nodeList的第一项为起点，最后一项为终点，经过中间其他点的最优路径，两个点之间是否可达需满足两点x,y坐标距离小于两点radius之和
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainDFS {
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
        for (Node node : nodeList) {
            visited.put(node, false);
        }

        optimalPath.add(startNode);
        visited.put(startNode, true);

        findOptimalPathDFS(startNode, endNode, nodeList, visited, optimalPath);

        return optimalPath;
    }

    public static boolean findOptimalPathDFS(Node currentNode, Node endNode, ArrayList<Node> nodeList,
                                             Map<Node, Boolean> visited, List<Node> optimalPath) {
        if (currentNode == endNode) {
            return true;
        }

        for (Node neighbor : nodeList) {
            if (!visited.get(neighbor)) {
                optimalPath.add(neighbor);
                visited.put(neighbor, true);

                if (neighbor == endNode || isReachable(currentNode, neighbor)) {
                    if (findOptimalPathDFS(neighbor, endNode, nodeList, visited, optimalPath)) {
                        return true;
                    }
                }

                optimalPath.remove(neighbor);
                visited.put(neighbor, false);
            }
        }

        return false;
    }

    public static boolean isReachable(Node node1, Node node2) {
        double distance = Math.sqrt(Math.pow(node2.x - node1.x, 2) + Math.pow(node2.y - node1.y, 2));
        double sumOfRadii = node1.radius + node2.radius;

        return distance <= sumOfRadii;
    }
}
import java.util.*;

public class Practice05_ShortestPathBranchBound {
    private static class Node implements Comparable<Node> {
        int vertex;
        int distance;

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public static int shortestPathLength(int[][] graph, int source, int target) {
        int n = graph.length;

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        int[] distances = new int[n];
        int[] pathCount = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(pathCount, 0);

        distances[source] = 0;
        pathCount[source] = 1;

        priorityQueue.offer(new Node(source, 0));

        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();
            int vertex = node.vertex;
            int distance = node.distance;

            if (vertex == target) {
                break;
            }

            if (distance > distances[vertex]) {
                continue;
            }

            for (int i = 0; i < n; i++) {
                if (graph[vertex][i] != 0) {
                    int newDistance = distance + graph[vertex][i];

                    if (newDistance < distances[i]) {
                        distances[i] = newDistance;
                        pathCount[i] = pathCount[vertex];
                        priorityQueue.offer(new Node(i, newDistance));
                    } else if (newDistance == distances[i]) {
                        pathCount[i] += pathCount[vertex];
                    }
                }
            }
        }

        System.out.println("经过路径的条数：" + pathCount[target]);
        return distances[target];
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 2, 0, 4, 0},
                {2, 0, 1, 0, 0},
                {0, 1, 0, 1, 3},
                {4, 0, 1, 0, 2},
                {0, 0, 3, 2, 0}
        };

        int source = 0;
        int target = 4;

        int shortestDistance = shortestPathLength(graph, source, target);
        System.out.println("最短路径长度：" + shortestDistance);
    }
}

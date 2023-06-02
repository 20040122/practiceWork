import java.util.*;

public class Practice05_BranchBoundKnapsack {
    private static class Node implements Comparable<Node> {
        int level;
        int weight;
        int boxes;

        public Node(int level, int weight, int boxes) {
            this.level = level;
            this.weight = weight;
            this.boxes = boxes;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.boxes, other.boxes);
        }
    }

    public static int findOptimalLoading(int[] weights, int capacity) {
        int n = weights.length;
        Arrays.sort(weights);

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        Node rootNode = new Node(-1, 0, 0);
        priorityQueue.offer(rootNode);

        int minBoxes = Integer.MAX_VALUE;

        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();
            int level = node.level;
            int weight = node.weight;
            int boxes = node.boxes;

            if (weight == capacity) {
                minBoxes = Math.min(minBoxes, boxes);
                continue;
            }

            if (level == n - 1 || weight > capacity) {
                continue;
            }

            Node leftChild = new Node(level + 1, weight + weights[level + 1], boxes + 1);
            Node rightChild = new Node(level + 1, weight, boxes);

            if (leftChild.weight <= capacity && leftChild.boxes < minBoxes) {
                priorityQueue.offer(leftChild);
            }

            if (rightChild.weight <= capacity && rightChild.boxes < minBoxes) {
                priorityQueue.offer(rightChild);
            }
        }

        return minBoxes;
    }

    public static void main(String[] args) {
        int[] weights = {5, 2, 6, 4, 3};
        int capacity = 10;

        int minBoxes = findOptimalLoading(weights, capacity);
        System.out.println("最优装载的集装箱个数：" + minBoxes);
    }
}

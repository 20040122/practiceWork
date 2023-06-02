import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Practice03_MeetingRooms {
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(intervals[0][1]);

        int minRooms = 1;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < minHeap.peek()) {
                minRooms++;
            } else {
                minHeap.poll();
            }
            minHeap.offer(intervals[i][1]);
        }

        return minRooms;
    }

    public static void main(String[] args) {
        int[][] intervals = {
                {1, 4},
                {2, 5},
                {6, 8},
                {7, 9},
                {10, 12}
        };

        int minRooms = minMeetingRooms(intervals);
        System.out.println("最少的会场个数：" + minRooms);
    }
}

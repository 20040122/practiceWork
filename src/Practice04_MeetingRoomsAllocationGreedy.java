import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Practice04_MeetingRoomsAllocationGreedy {

    public static int minMeetingRooms(int[][] activities) {
        // 对活动按照开始时间进行排序
        Arrays.sort(activities, (a, b) -> a[0] - b[0]);

        List<Integer> endTimes = new ArrayList<>();
        endTimes.add(activities[0][1]); // 将第一个活动的结束时间加入列表

        for (int i = 1; i < activities.length; i++) {
            int currentStartTime = activities[i][0];
            int currentEndTime = activities[i][1];

            boolean hasAllocated = false;
            for (int j = 0; j < endTimes.size(); j++) {
                if (currentStartTime >= endTimes.get(j)) {
                    // 找到一个会场，该活动可以与已有的会场共享
                    endTimes.set(j, currentEndTime);
                    hasAllocated = true;
                    break;
                }
            }

            if (!hasAllocated) {
                // 没有找到可以共享的会场，需要新增一个会场
                endTimes.add(currentEndTime);
            }
        }

        return endTimes.size();
    }

    public static void main(String[] args) {
        int[][] activities = {
                {1, 3},
                {2, 4},
                {3, 6},
                {5, 7},
                {8, 9}
        };
        int minRooms = minMeetingRooms(activities);
        System.out.println("Minimum number of meeting rooms required: " + minRooms);
    }
}

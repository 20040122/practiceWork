import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Practice02_MeetingScheduleDynamicProgramming {
    public static List<int[]> maxMeetingSchedule(int[][] meetings) {
        int n = meetings.length;
        int[] dp = new int[n]; // stores the maximum duration of meetings up to index i
        int[] prev = new int[n]; // stores the previous meeting index in the optimal solution

        Arrays.sort(meetings, (a, b) -> a[1] - b[1]); // sort meetings based on end times

        // Initialize the first meeting
        dp[0] = meetings[0][1] - meetings[0][0];
        prev[0] = -1;

        // Compute the maximum duration for each meeting
        for (int i = 1; i < n; i++) {
            // Find the previous meeting with the maximum duration
            int maxDuration = 0;
            int prevMeetingIndex = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (meetings[j][1] <= meetings[i][0] && dp[j] > maxDuration) {
                    maxDuration = dp[j];
                    prevMeetingIndex = j;
                }
            }

            // Compute the maximum duration
            if (prevMeetingIndex != -1) {
                dp[i] = maxDuration + meetings[i][1] - meetings[i][0];
                prev[i] = prevMeetingIndex;
            } else {
                dp[i] = meetings[i][1] - meetings[i][0];
                prev[i] = -1;
            }
        }

        // Find the index of the meeting with the maximum duration
        int maxDurationIndex = 0;
        for (int i = 1; i < n; i++) {
            if (dp[i] > dp[maxDurationIndex]) {
                maxDurationIndex = i;
            }
        }

        // Backtrack to find the meetings included in the optimal solution
        List<int[]> schedule = new ArrayList<>();
        int index = maxDurationIndex;
        while (index >= 0) {
            schedule.add(0, meetings[index]);
            index = prev[index];
        }

        return schedule;
    }

    public static void main(String[] args) {
        int[][] meetings = {
                {1, 4},
                {3, 5},
                {0, 6},
                {5, 7},
                {3, 8},
                {5, 9},
                {6, 10},
                {8, 11},
                {8, 12},
                {2, 13},
                {12, 15}
        };

        List<int[]> schedule = maxMeetingSchedule(meetings);
        System.out.println("会议安排方案：");
        for (int[] meeting : schedule) {
            System.out.println(Arrays.toString(meeting));
        }
    }
}

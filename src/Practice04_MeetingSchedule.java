import java.util.Scanner;

class Main {
    static final int MAX_ACTIVITIES = 100;

    // 表示一个活动
    static class Activity {
        int start_time;
        int end_time;

        Activity(int start_time, int end_time) {
            this.start_time = start_time;
            this.end_time = end_time;
        }
    }

    // 检查两个活动是否相容
    static boolean isCompatible(Activity a, Activity b) {
        return (a.end_time <= b.start_time || b.end_time <= a.start_time);
    }

    // 回溯算法分配活动到会场
    static void assignActivities(Activity[] activities, int n, int curr_activity, int[] colors, int num_colors,
                                 int[] min_colors) {
        if (curr_activity == n) {
            // 所有活动都已分配完毕，更新最少会场数量
            if (num_colors < min_colors[0]) {
                min_colors[0] = num_colors;
            }
            return;
        }

        // 尝试将当前活动分配到每个已有的会场或新建一个会场
        for (int i = 1; i <= num_colors; i++) {
            boolean isCompatibleWithExisting = true;
            for (int j = 0; j < curr_activity; j++) {
                if (colors[j] == i && !isCompatible(activities[j], activities[curr_activity])) {
                    isCompatibleWithExisting = false;
                    break;
                }
            }

            if (isCompatibleWithExisting) {
                colors[curr_activity] = i;

                // 递归分配下一个活动
                assignActivities(activities, n, curr_activity + 1, colors, num_colors, min_colors);

                colors[curr_activity] = 0; // 恢复为未分配状态
            }
        }

        // 如果没有现有的会场与活动相容，则分配一个新的会场
        colors[curr_activity] = num_colors + 1;
        assignActivities(activities, n, curr_activity + 1, colors, num_colors + 1, min_colors);

        colors[curr_activity] = 0; // 恢复为未分配状态
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n; // 活动数量
        Activity[] activities = new Activity[MAX_ACTIVITIES];
        int[] colors = new int[MAX_ACTIVITIES]; // 用来记录每个活动所分配的会场号
        int[] min_colors = {MAX_ACTIVITIES}; // 记录最少会场数量

        System.out.print("请输入活动数量: ");
        n = scanner.nextInt();

        System.out.println("请输入每个活动的起始时间和结束时间:");
        for (int i = 0; i < n; i++) {
            System.out.print("活动 " + (i + 1) + ": ");
            int start_time = scanner.nextInt();
            int end_time = scanner.nextInt();
            activities[i] = new Activity(start_time, end_time);
        }

        assignActivities(activities, n, 0, colors, 0, min_colors);

        System.out.println("需要的最少会场数量为: " + min_colors[0]);
    }
}

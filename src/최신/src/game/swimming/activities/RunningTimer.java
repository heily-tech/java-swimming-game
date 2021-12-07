package game.swimming.activities;

public class RunningTimer {
    private static long startTime;
    private static long resultMinTime;
    private static long resultSecTime;
    private static long thisTime;
    private static long runTime;
    private static long[] pcStartTime = {0, 0, 0, 0, 0, 0, 0};
    private static long[] pcThisTime = {0, 0, 0, 0, 0, 0, 0};
    private static long[] pcRunTime = {0, 0, 0, 0, 0, 0, 0};
    private static long[] pcResultMinTime = {0, 0, 0, 0, 0, 0, 0};
    private static long[] pcResultSecTime = {0, 0, 0, 0, 0, 0, 0};

    public static void startTimer() {
        startTime = System.currentTimeMillis();
    }

    public static void startTimer(int pc_num) {
        pcStartTime[pc_num] = System.currentTimeMillis();
    }

    public static String runningTime() {
        thisTime = System.currentTimeMillis();
        runTime = (int) (thisTime - startTime) / 1000;
        if (runTime >= 60) {
            resultMinTime = runTime / 60;
            resultSecTime = runTime % 60;
        } else
            resultSecTime = runTime;
        return resultMinTime + ":" + resultSecTime + "\n";
    }

    public static String pcRunningTimes(int pc_num) {
        pcThisTime[pc_num] = System.currentTimeMillis();
        pcRunTime[pc_num] = (int) (pcThisTime[pc_num] - pcStartTime[pc_num]) / 1000;
        if (pcRunTime[pc_num] >= 60) {
            pcResultMinTime[pc_num] = pcRunTime[pc_num] / 60;
            pcResultSecTime[pc_num] = pcRunTime[pc_num] % 60;
        } else
            pcResultSecTime[pc_num] = pcRunTime[pc_num];

            return pcResultMinTime[pc_num] + ":" + pcResultSecTime[pc_num] + "\n";
    }
}

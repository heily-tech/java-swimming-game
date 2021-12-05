import game.swimming.activities.RunningTimer;

import static game.swimming.activities.RunningTimer.*;

public class test extends Thread {
    long start, end;

    public static void main(String[] args) {
        test t = new test();
        t.start();
    }

    @Override
    public void run() {
        try {
            startTimer();
            System.out.println(runningTime());
            System.out.println("start");
            System.out.println(runningTime());
            Thread.sleep(1000);
            System.out.println(runningTime());
            System.out.println("~ing");
            System.out.println(runningTime());
            Thread.sleep(1000);
            System.out.println(runningTime());
            Thread.sleep(1000);
            System.out.println(runningTime());
            Thread.sleep(1000);
            System.out.println(runningTime());
            Thread.sleep(10000);
            System.out.println(runningTime());
            Thread.sleep(60000);
            System.out.println(runningTime());
            Thread.sleep(60000);
            System.out.println(runningTime());
            Thread.sleep(60000);
            System.out.println(runningTime());
            Thread.sleep(60000);
            System.out.println(runningTime());
            System.out.println("end");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

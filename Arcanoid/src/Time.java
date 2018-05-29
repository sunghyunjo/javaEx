import java.util.List;

class Time {
    private static long speedItemStartTime;
    private static long powerItemStartTime;

    static void checkItemTime(int size, List<Ball> ballList) {
        long endTime = System.currentTimeMillis();
        double speedItemTime = ((endTime - speedItemStartTime) / 1000.0);
        double powerItemTime = ((endTime - powerItemStartTime) / 1000.0);

        if (speedItemTime > 7) {
            for (int i = 0; i < size; i++)
                ballList.get(i).changeSpeed(Constants.BALL_SPEED);
        }
        if (powerItemTime > 7)
            Ball.setPower(false);
    }

    static void setSpeedItemStartTime(long speedItemStartTime) {
        Time.speedItemStartTime = speedItemStartTime;
    }

    static void setPowerItemStartTime(long powerItemStartTime) {
        Time.powerItemStartTime = powerItemStartTime;
    }
}

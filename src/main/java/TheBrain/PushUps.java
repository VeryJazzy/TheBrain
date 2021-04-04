package TheBrain;

public class PushUps {

    private static int current = 0;
    private static int target = 75;


    public static int getDay() {
        return current;
    }

    public static int getCurrent() {
        return current;
    }

    public static void resetCurrent() {
        current = 0;
    }

    public static void addTen() {
        PushUps.current += 10;
    }


    public static void setTarget(int target) {
        PushUps.target = target;
    }






}

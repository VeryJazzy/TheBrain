package TheBrain;

public class Counter {

    private static int current = 0;
    private static int target = 75;

    public static int getDay() {
        return current;
    }

    public static void resetCurrent() {
        current = 0;
    }

    public static void addTen() {
        Counter.current += 10;
    }

    public static void setTarget(int target) {
        Counter.target = target;
    }






}

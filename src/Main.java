import java.util.Arrays;
import java.util.Random;

public class Main {
    public static final int ARRAY_SIZE = 10_000_000;
    public static final int MIN_ARRAY_SIZE = 10_000;
    public static final String STRING_FORMAT = "%-30s %-10s %-10s\n";
    public static final String SINGLE_THREAD_STRING = "Single-threaded counting";
    public static final String MULTI_THREAD_STRING = "Multithreaded counting";


    public static void main(String[] args) {
        int[] array = initArray();

        System.out.printf(STRING_FORMAT, "", "Time, ns", "Amount");
        long startTime = System.nanoTime();
        int amount = sumBySingle(array);
        long endTime = System.nanoTime();
        System.out.printf(STRING_FORMAT, SINGLE_THREAD_STRING, endTime - startTime, amount);
        //startTime = System.currentTimeMillis();
        //methodToTime();
        //endTime = System.currentTimeMillis();
        //System.out.printf(MULTI_THREAD_STRING, endTime - startTime);
    }

    public static int[] initArray() {
        int[] array = new int[ARRAY_SIZE];
        Random random = new Random();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = random.nextInt(ARRAY_SIZE) + 1;
        }
        return array;
    }

    public static int sumBySingle(int[] array) {
        int sum = 0;
        for (int i = 0; i < ARRAY_SIZE; i++) {
            sum += array[i];
        }
        return sum;
    }

}

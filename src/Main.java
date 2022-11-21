import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static final int[] ARRAYS_SIZE = {10_000, 10_000_000};
    public static final String STRING_FORMAT = "%-30s %-10s %-10s\n";
    public static final String SINGLE_THREAD_STRING = "Single-threaded counting";
    public static final String MULTI_THREAD_STRING = "Multithreaded counting";


    public static void main(String[] args) {
        for (int arraySize : ARRAYS_SIZE)
        {
            int[] array = initArray(arraySize);
            System.out.printf("\nArray`s size = %s\n---------------------------------------------------\n",
                    arraySize);

            // 1 method - Single threaded
            System.out.printf(STRING_FORMAT, "", "Time, ns", "Amount");
            long startTime = System.nanoTime();
            int amount = sumBySingle(array);
            long endTime = System.nanoTime();
            System.out.printf(STRING_FORMAT, SINGLE_THREAD_STRING, endTime - startTime, amount);

            // 2 method - Multithreaded
            AmountCounter amountCounter = new AmountCounter(array);
            // limiting the number of threads
            int numOfThreads = Runtime.getRuntime().availableProcessors();
            ForkJoinPool forkJoinPool = new ForkJoinPool(numOfThreads);

            startTime = System.currentTimeMillis();
            amount = forkJoinPool.invoke(amountCounter);
            endTime = System.currentTimeMillis();
            System.out.printf(STRING_FORMAT, MULTI_THREAD_STRING, endTime - startTime, amount);
        }
    }

    public static int[] initArray(int arraySize) {
        int[] array = new int[arraySize];
        Random random = new Random();
        for (int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt(arraySize) + 1;
        }
        return array;
    }

    public static int sumBySingle(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }
}

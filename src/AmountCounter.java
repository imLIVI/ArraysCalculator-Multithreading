import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class AmountCounter extends RecursiveTask<Integer> {
    private int[] array;

    public AmountCounter(int[] array) {
        this.array = array;
    }

    @Override
    protected Integer compute() {
        if (array.length <= 2)
            return Arrays.stream(array).sum();

        AmountCounter left = new AmountCounter(Arrays.copyOfRange(array, 0, array.length / 2));
        AmountCounter right = new AmountCounter(Arrays.copyOfRange(array, array.length / 2, array.length));
        left.fork();
        right.fork();
        return left.join() + right.join();
    }
}

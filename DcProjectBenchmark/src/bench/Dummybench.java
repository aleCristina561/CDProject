package bench;

import java.math.BigDecimal;
import java.util.Random;

public class Dummybench implements IBenchmark{
    private boolean running;
    int[] array;
    int n;
    @Override
    public void cancel() {
        running=false;
    }

    @Override
    public void clean() {

    }

    @Override
    public void warmup(Object parameters) {

    }

    @Override
    public void warmup() {
    }

    @Override
    public void run() {
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < n - 1 && running; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1 && running; j++) {
                if (array[j] > array[j + 1]) {

                    // Swap arr[j] and arr[j+1]
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }

            // If no two elements were
            // swapped by inner loop, then break
            if (swapped == false)
                break;
        }
    }


    @Override
    public void initialize(Object parameters) {
        running=true;
        Random random=new Random();
        this.n=(Integer)parameters;
        this.array=new int[n];
        for(int i=0;i<n;i++){
            array[i]=random.nextInt(1000);
        }

    }

    @Override
    public void run(Object parameters) {

    }

    @Override
    public String getResult() {
        return "";
    }
}

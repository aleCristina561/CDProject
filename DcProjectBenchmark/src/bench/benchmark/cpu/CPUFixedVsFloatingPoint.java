package bench.benchmark.cpu;

import bench.IBenchmark;


public class CPUFixedVsFloatingPoint implements IBenchmark {

    private int result;
    private float result2;
    private int size;

    @Override
    public void initialize(Object params) {
        this.size = (Integer)params;
    }

    @Override
    public void warmup() {
        for (int i = 0; i < size; ++i) {
            result = i/256 ; // fixed
            result2= i/256; // floating
        }
    }

    @Override
    public void warmup(Object parameters) {

    }

    @Override
    public void run(Object options) {
        result = 0;

        switch ((Nb_repr)options) {
            case FLOATING:
                for (int i = 0; i < size; ++i)
                    result2 += i/256;/**/
                break;
            case FIXED:
                for (int i = 0; i < size; ++i)
                    result +=i/256;/**/
                break;
            default:
                break;
        }

    }

    @Override
    public void cancel() {

    }

    @Override
    public void clean() {
    }

    @Override
    public void run() {

    }

    @Override
    public String getResult() {
        return String.valueOf(result);
    }

}
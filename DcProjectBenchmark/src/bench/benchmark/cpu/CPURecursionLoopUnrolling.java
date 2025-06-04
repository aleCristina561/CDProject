package bench.benchmark.cpu;

import bench.IBenchmark;

public class CPURecursionLoopUnrolling implements IBenchmark {
    private long workload=2000;
    long sum=0;
    @Override
    public String getResult() {
        return "";
    }

    @Override
    public void run(Object parameters) {

    }

    public void run(Object bool,Object parameters) {
        boolean par=(boolean) bool;
        int i=(int)parameters;
        long l;
        if(par){
            l=recursive(1,workload,0);
            System.out.println("sum "+l);
        }
        else{
           l=recursiveUnrolled(1,i,workload,0);
            System.out.println("sum1 "+l);
        }
    }
    private long recursiveUnrolled(long start, int unrollLevel, long size, int counter){
        try {
            if (start > size) {
                System.out.println("counts"+counter);
                return 0;
            }
            int count=0;
            long current=start;
            while(count<unrollLevel && current<=size ) {
                if (isPrime(current))
                    sum = sum + current;
                current++;
                count++;
            }
            return sum+recursiveUnrolled(current,unrollLevel,size, counter + 1);

        }catch(StackOverflowError e){
            System.out.println("Reached nr " + start + "/" + size + " after " + counter + " calls.");
            return 0;
        }
    }
    public boolean isPrime(long x) {
        if (x <= 2)
            return true;
        for (int i = 2; i <= Math.sqrt(x); i++) {
            if (x % i == 0)
                return false;
            //System.out.println("mere");
        }
        return true;
    }
    private long recursive(long start, long size, int counter) {
        try {
            //System.out.println("pere");
            if (start > size) {
                System.out.println("count "+counter);
               return 0;
            }
            if(isPrime(start))
                sum = sum + start;
            return sum+recursive(start + 1, size, counter + 1);

            }catch(StackOverflowError e){
               System.out.println("Reached nr " + start + "/" + size + " after " + counter + " calls.");
               return 0;
            }

    }
    @Override
    public void run() {

    }

    @Override
    public void warmup(Object parameters) {

    }

    @Override
    public void warmup() {

    }

    @Override
    public void initialize(Object parameters) {
        workload = (Long)parameters;
    }

    @Override
    public void clean() {

    }

    @Override
    public void cancel() {

    }
}

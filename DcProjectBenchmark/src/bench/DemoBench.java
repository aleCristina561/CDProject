package bench;

import java.math.BigDecimal;

public class DemoBench implements IBenchmark {
    int n;
    @Override
    public void clean() {

    }

    @Override
    public String getResult() {
        return "";
    }

    @Override
    public void cancel() {

    }

    @Override
    public void warmup() {

    }

    @Override
    public void warmup(Object parameters) {

    }

    @Override
    public void run(Object parameters) {
        try{
            Thread.sleep((Integer)parameters);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }

    @Override
    public void initialize(Object parameters) {
        this.n = (Integer) parameters;
        run(n);
    }
}

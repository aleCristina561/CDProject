package bench;

import java.math.BigDecimal;

public interface IBenchmark {
    public void run();
    public void run(Object parameters);
    public void initialize(Object parameters);
    public void clean();
    public void cancel();
    public void warmup(Object parameters);
    public void warmup();
    public String getResult();
}

package bench;

public interface IBenchmark {
    public void run();
    public void run(Object parameters);
    public void initialize();
    public void clean();
    public void cancel();

}

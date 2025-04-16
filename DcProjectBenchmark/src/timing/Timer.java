package timing;

public class Timer implements ITimer{
    long elapsedTime;
    long startTime;
    long endTime;
    long totalTime;

    @Override
    public void start() {
        totalTime=0;
        startTime = System.nanoTime();
    }

    public void resume

    @Override
    public long stop() {
        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        totalTime += elapsedTime;
        return totalTime;
    }

}

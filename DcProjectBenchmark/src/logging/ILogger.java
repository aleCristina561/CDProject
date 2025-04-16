package logging;

public interface ILogger {
    public void write(String message,long time,String measuresec);
    public void close();
}

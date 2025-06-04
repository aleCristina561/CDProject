package logging;

import java.util.concurrent.TimeUnit;

public interface ILogger {
    public void write(String message,long time,String measuresec);
    public void write(String message);
    public void close();
    public void  writeTime(String message, long time, TimeUnit timeUnit);
    public void write1(String message,String measures2);
}

package logging;

import java.util.concurrent.TimeUnit;

public class ConsoleLogger implements ILogger {

    //public ConsoleLogger() {}

    @Override
    public void write(String message) {
        System.out.println(message);
    }
    public void write(String message, long time, String messagesec) {
        System.out.println(message+time+" "+messagesec);
    }
    @Override
    public void close() {
    }
    public void writeTime(String message, long time, TimeUnit timeunit) {
        System.out.println(message+time+" "+timeunit);
    }
    public void write1(String message,String message2) {
        System.out.println(message+message2);
    }
}

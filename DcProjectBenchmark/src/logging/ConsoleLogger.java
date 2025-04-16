package logging;

public class ConsoleLogger implements ILogger {

    //public ConsoleLogger() {}

    public void write(String message,long time,String messagesec) {
        System.out.println(message+time+" "+messagesec);
    }
    @Override
    public void close() {
    }
}

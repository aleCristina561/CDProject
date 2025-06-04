package logging;

import java.util.concurrent.TimeUnit;

public class Timeunit {
    public long Time(TimeUnit timeUnit,long duration) {
            switch (timeUnit) {
                case NANOSECONDS:
                    return duration;
                    case MICROSECONDS:
                        return duration/(1000);
                        case MILLISECONDS:
                            return duration/(1000000);
                            case SECONDS:
                                return duration/(1000000000);
                                default:
                                    return -1;

            }

    }
    public static void main(String[] args) {
        Timeunit timeunit = new Timeunit();
    }
}

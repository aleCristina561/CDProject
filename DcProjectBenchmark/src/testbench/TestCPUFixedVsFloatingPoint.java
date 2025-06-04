package testbench;

import logging.ConsoleLogger;
import logging.ILogger;
import logging.Timeunit;
import timing.ITimer;
import timing.Timer;
import bench.IBenchmark;
import bench.benchmark.cpu.CPUFixedPoint;
import bench.benchmark.cpu.CPUFixedVsFloatingPoint;
import bench.benchmark.cpu.Nb_repr;

import java.util.concurrent.TimeUnit;

public class TestCPUFixedVsFloatingPoint {

    public static void main(String[] args) {
        ITimer timer = new Timer();
        ILogger log = /* new FileLogger("bench.log"); */new ConsoleLogger();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;

        IBenchmark bench = new CPUFixedVsFloatingPoint();
        IBenchmark bench1=new CPUFixedPoint();
        bench.initialize(100000);
        bench.warmup();

        timer.start();
        bench.run(Nb_repr.FIXED);
//      bench.run(NumberRepresentation.FLOATING);
        long time = timer.stop();
        log.writeTime("Finished in ", time, timeUnit);
        log.write1("Result is ", bench.getResult());

        bench1.initialize(300);
        bench1.run(0);

        bench.clean();
        log.close();
    }
}



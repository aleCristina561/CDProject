package testbench;

import bench.Dummybench;
import bench.IBenchmark;
import logging.ConsoleLogger;
import logging.FileLogger;
import logging.ILogger;
import logging.Timeunit;
import timing.ITimer;
import timing.Timer;

import java.util.concurrent.TimeUnit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class TestDummyBench {
    public static void main(String[] args) {
        ITimer timer = new Timer();
        ILogger log=new ConsoleLogger();
        IBenchmark bench=new Dummybench();
        final int workload=100;
        bench.initialize(workload);
        timer.start();
        timer.pause();
        for(int i=0;i<12;++i){
            bench.initialize(workload);
            timer.resume();
            bench.run();
            long time=timer.pause();
            log.write("Run "+i+":",time,"ns");
        }
        long time=timer.stop();
        log.write("Finish in ",time," ns");

        Timeunit timeunit=new Timeunit();
        TimeUnit unit=TimeUnit.MILLISECONDS;
        time= timeunit.Time(unit,time);
        log.writeTime("Finish in ",time,unit);

    }
}
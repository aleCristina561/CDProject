package testbench;

import bench.DemoBench;
import bench.Dummybench;
import bench.IBenchmark;
import logging.ConsoleLogger;
import logging.ILogger;
import timing.ITimer;
import timing.Timer;

public class TestDemoBench {
    public static void main(String[] args) {
        ITimer timer = new Timer();
        IBenchmark bench = new DemoBench();

        timer.start();
        bench.initialize(100);
        long time= timer.stop();
        long offset=(time-100*(1000000))/(100*(1000000));//10^6 test it
        System.out.println(time);
        System.out.println(offset);
    }

}

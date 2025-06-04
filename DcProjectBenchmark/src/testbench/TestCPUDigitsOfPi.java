package testbench;

import bench.DemoBench;
import bench.IBenchmark;
import bench.benchmark.cpu.CPUDigitsOfPi;
import timing.ITimer;
import timing.Timer;

public class TestCPUDigitsOfPi {
    public static void main(String[] args) {
        ITimer timer = new Timer();
        IBenchmark bench = new CPUDigitsOfPi();

        for(int i=0;i<50;i++)
             bench.warmup(0);
        timer.start();
        //bench.initialize(100);
        for(int i=0;i<10;i++)
            bench.run(0);
        long time= timer.stop();
        //long offset=(time-100*(1000000))/(100*(1000000));//10^6 test it
        System.out.println(time);
       // System.out.println(offset);
    }
}

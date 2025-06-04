package testbench;

import bench.IBenchmark;
import bench.benchmark.cpu.CPURecursionLoopUnrolling;
import timing.ITimer;
import timing.Timer;

public class TestCPURecursionLoopUnrolling {
    public static void main(String[] args) {
        CPURecursionLoopUnrolling bench=new CPURecursionLoopUnrolling();
        ITimer timer=new Timer();
        //bench.initialize(200000000000L);
        timer.start();
        bench.run(false,0);
        System.out.println(timer.stop());
    }
}

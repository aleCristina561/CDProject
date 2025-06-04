package testbench;

import bench.benchmark.cpu.HDDRandomAccess;

public class TestHDDRandomAccess {
    public static void main(String[] args) {
        HDDRandomAccess bench = new HDDRandomAccess();
        Object[] initParams = {100L * 1024 * 1024}; // 100 MB
        bench.initialize(initParams);

        Object[] runParams = {"r", "fs", 4 * 1024}; // read, fixed size, 4KB buffer
        bench.run(runParams);
        System.out.println(bench.getResult());
    }
}

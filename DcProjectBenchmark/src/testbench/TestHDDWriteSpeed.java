package testbench;

import bench.hdd.HDDWriteSpeed;

public class TestHDDWriteSpeed {
    public static void main(String[] args) {
        HDDWriteSpeed bench = new HDDWriteSpeed();
        bench.run("fs",false);
    }
}


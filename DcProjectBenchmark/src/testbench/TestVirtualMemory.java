package testbench;

import bench.IBenchmark;
import bench.ram.VirtualMemoryBenchmark;
import logging.ConsoleLogger;
import logging.ILogger;

public class TestVirtualMemory {
    public static void main(String[] args) {
        ILogger logger= new ConsoleLogger();
        IBenchmark bench= new VirtualMemoryBenchmark();

        //Object[] params = new Object[] {
         //       2L * 1024 * 1024, // 256 MB
        //        4*1024               // 4 KB};

        Object[] options = {2147483648L, 4096};  // 2GB file, 4KB buffer
        bench.run(options);

        //bench.run(params);
        logger.write(bench.getResult());
        bench.clean();

        Object[] options2 = {8589934592L, 8192};  // 8GB file, 8KB buffer
        bench.run(options);
        logger.write(bench.getResult());
        bench.clean();

        logger.close();
    }
}

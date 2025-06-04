package bench.ram;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import timing.Timer;
import bench.IBenchmark;

/**
 * Maps a large file into RAM triggering the virtual memory mechanism. Performs
 * reads and writes to the respective file.<br>
 * The access speeds depend on the file size: if the file can fit the available
 * RAM, then we are measuring RAM speeds.<br>
 * Conversely, we are measuring the access speed of virtual memory, implying a
 * mixture of RAM and HDD access speeds (i.e., lower speeds).
 */
public class VirtualMemoryBenchmark implements IBenchmark {

    private String result = "";

    @Override
    public void initialize(Object params) {
        /* not today */
    }

    @Override
    public void warmup() {
        /* summer is coming anyway */
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Use run(Object[]) instead");
    }

    @Override
    public void warmup(Object parameters) {

    }

    @Override
    public void run(Object parameters) {
        if (parameters instanceof Object[]) {
            run((Object[]) parameters);
        } else {
            throw new IllegalArgumentException("Expected Object[] parameters");
        }
    }


    @Override
    public void cancel() {

    }

    public void run(Object[] options) {
        // Expected: {fileSize, bufferSize}
        Object[] params = (Object[]) options;
        long fileSize = Long.parseLong(params[0].toString()); // e.g. 2-16GB
        int bufferSize = Integer.parseInt(params[1].toString()); // e.g. 4KB+

        MemoryMapper core = null;

        try {
            // Adjust path as needed for your system
            new File("stefi/IdeaProjects/000_core").mkdirs();
            core = new MemoryMapper("stefi/IdeaProjects/000_core/test.dat", fileSize);

            byte[] buffer = new byte[bufferSize];
            Random rand = new Random();

            Timer timer = new Timer();

            // ===== WRITE TO VIRTUAL MEMORY =====
            timer.start();
            for (long i = 0; i < fileSize; i += bufferSize) {
                rand.nextBytes(buffer);          // 1. generate random content
                core.write(i,buffer);           // 2. write to memory-mapped file
            }
            long writeTime = timer.stop();       // in nanoseconds
            double writeSpeed = fileSize / (writeTime / 1e9) / (1024 * 1024); // MB/s

            result = String.format("\nWrote %d MB to virtual memory at %.2f MB/s",
                    fileSize / (1024 * 1024), writeSpeed); // 4. format output

            // ===== READ FROM VIRTUAL MEMORY =====
            timer.start();
            for (long i = 0; i < fileSize; i += bufferSize) {
                core.read(i,bufferSize);            // 5. read back from memory
            }
            long readTime = timer.stop();
            double readSpeed = fileSize / (readTime / 1e9) / (1024 * 1024); // MB/s

            result += String.format("\nRead %d MB from virtual memory at %.2f MB/s",
                    fileSize / (1024 * 1024), readSpeed); // 7. format output

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (core != null)
                core.purge(); // clean up mapped memory file
        }
    }
    public void clean(MemoryMapper core) {
        if (core != null)
            core.purge();
    }

    @Override
    public void clean() {

    }

    @Override
    public String getResult() {
        return result;
    }

}
package bench.hdd;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

import timing.Timer;
import java.io.*;



public class FileWriter {

    private static final int MIN_BUFFER_SIZE = 1024 * 1; // KB
    private static final int MAX_BUFFER_SIZE = 1024 * 1024 * 32; // MB
    private static final long MIN_FILE_SIZE = 1024 * 1024 * 1; // MB
    private static final long MAX_FILE_SIZE = 1024 * 1024 * 512; // MB
    private Timer timer = new Timer();
    private Random rand = new Random();
    private double benchScore;

    /**
     * Writes files on disk using a variable write buffer and fixed file size.
     *
     * @param filePrefix
     *            - Path and file name
     * @param fileSuffix
     *            - file extension
     * @param minIndex
     *            - start buffer size index
     * @param maxIndex
     *            - end buffer size index
     * @param fileSize
     *            - size of the benchmark file to be written in the disk
     * @throws IOException
     */
    public void streamWriteFixedFileSize(String filePrefix, String fileSuffix,
                                         int minIndex, int maxIndex, long fileSize, boolean clean)
            throws IOException {

        System.out.println("Stream write benchmark with fixed file size");
        int currentBufferSize = MIN_BUFFER_SIZE;
        int fileIndex = 0;
        benchScore = 0;

        while (currentBufferSize <= MAX_BUFFER_SIZE
                && fileIndex <= (maxIndex - minIndex)) {

            String fileName = filePrefix + (minIndex + fileIndex) + fileSuffix;
            writeFile(fileName, currentBufferSize, fileSize, clean);

            // Typically you want to double the buffer size each iteration
            currentBufferSize *= 2;
            fileIndex++;
        }

        benchScore /= (maxIndex - minIndex + 1);
        String partition = filePrefix.substring(0, filePrefix.indexOf(":\\"));
        System.out.println("File write score on partition " + partition + ": "
                + String.format("%.2f", benchScore) + " MB/sec");
    }

    /**
     * Writes files on disk using a variable file size and fixed buffer size.
     *
     * @param filePrefix - Path and file name
     * @param fileSuffix - File extension
     * @param minIndex   - Start file size index
     * @param maxIndex   - End file size index
     * @param bufferSize - Fixed buffer size to write the file
     * @param clean      - Flag to clean file before write
     * @throws IOException
     */
    public void streamWriteFixedBufferSize(String filePrefix, String fileSuffix,
                                           int minIndex, int maxIndex, int bufferSize, boolean clean)
            throws IOException {

        System.out.println("Stream write benchmark with fixed buffer size");
        long currentFileSize = MIN_FILE_SIZE;
        int fileIndex = 0;
        benchScore = 0;

        while (currentFileSize <= MAX_FILE_SIZE
                && fileIndex <= (maxIndex - minIndex)) {

            String fileName = filePrefix + (minIndex + fileIndex) + fileSuffix;
            writeFile(fileName, bufferSize, currentFileSize, clean);

            // Typically double the file size each iteration
            currentFileSize *= 2;
            fileIndex++;
        }

        benchScore /= (maxIndex - minIndex + 1);
        String partition = filePrefix.substring(0, filePrefix.indexOf(":\\"));
        System.out.println("File write score on partition " + partition + ": "
                + String.format("%.2f", benchScore) + " MB/sec");
    }


    /**
     * Writes a file with random binary content on the disk, using a given file
     * path and buffer size.
     */
    private void writeFile(String fileName, int bufferSize,
                           long fileSize, boolean clean) throws IOException {

        File folderPath = new File(fileName.substring(0, fileName.lastIndexOf(File.separator)));

        // Create directory if it doesn't exist
        if (!folderPath.isDirectory()) {
            folderPath.mkdirs();
        }

        final File file = new File(fileName);

        // Create stream writer with given buffer size
        FileOutputStream fileStream = new FileOutputStream(file);
        BufferedOutputStream outputStream = new BufferedOutputStream(fileStream, bufferSize);

        byte[] buffer = new byte[bufferSize];
        int i = 0;
        long toWrite = fileSize / bufferSize;

        // Start timing
        long startTime = System.nanoTime();

        while (i < toWrite) {
            // Generate random content to write
            rand.nextBytes(buffer);
            outputStream.write(buffer);
            i++;
        }

        outputStream.flush();
        outputStream.close();

        // End timing and record benchmark
        long endTime = System.nanoTime();
        double durationInSeconds = (endTime - startTime) / 1_000_000_000.0;
        double mbWritten = (double) fileSize / (1024 * 1024);
        double speed = mbWritten / durationInSeconds;

        // Store the score
        benchScore += speed;

        printStats(fileName, fileSize, bufferSize, speed);

        if (clean) {
            // Schedule file for deletion on JVM exit
            file.deleteOnExit();
        }
    }


    private void printStats(String fileName, long totalBytes, int bufferSize,double speed) {
        final long timeNano = timer.stop(); // time in nanoseconds

        NumberFormat nf = new DecimalFormat("#.00");

        // Convert time to seconds
        double seconds = timeNano / 1_000_000_000.0;

        // Convert bytes to megabytes (1 MB = 1024 * 1024)
        double megabytes = totalBytes / (1024.0 * 1024.0);

        // Calculate throughput (MB/s)
        double rate = megabytes / seconds;

        System.out.println("Done writing " + totalBytes + " bytes to file: "
                + fileName + " in " + nf.format(seconds * 1000) + " ms ("
                + nf.format(rate) + " MB/sec) with a buffer size of "
                + (bufferSize / 1024) + " kB");

        // Add to benchmark score
        benchScore += rate;
    }

}

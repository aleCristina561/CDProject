package bench.hdd;

import java.io.File;
import java.io.IOException;

import bench.IBenchmark;

public class HDDWriteSpeed implements IBenchmark {

    @Override
    public void run() {

    }

    @Override
    public void initialize(Object params) {
    }

    @Override
    public void warmup() {
    }

    @Override
    public void run(Object parameters) {

    }
    public void run(String options,Boolean cleans) {
        FileWriter writer = new FileWriter();
        // either "fs" - fixed size, or "fb" - fixed buffer
        String option = (String) options;
        // true/false whether the written files should be deleted at the end
        Boolean clean = (Boolean) cleans;

        String prefix = "/home/stefi/IdeaProjects/files";
        String suffix = ".dat";
        int minIndex = 0;
        int maxIndex = 8;
        long fileSize = 256; // 256, 512 MB, 1GB // type Long!
        int bufferSize = 4; // 4 KB

        try {
            if (option.equals("fs"))
                writer.streamWriteFixedFileSize(prefix, suffix, minIndex,
                        maxIndex, fileSize, clean);
            else if (option.equals("fb"))
                writer.streamWriteFixedBufferSize(prefix, suffix, minIndex,
                        maxIndex, bufferSize, clean);
            else
                throw new IllegalArgumentException("Argument "
                        + options.toString() + " is undefined");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(clean){
            File file=new File(prefix);
            file.delete();
        }
    }

    @Override
    public void clean() {
        // clean temp files here?
    }

    @Override
    public void cancel() {

    }

    @Override
    public void warmup(Object parameters) {

    }

    @Override
    public String getResult() {
        return null; // or MBps
    }
}

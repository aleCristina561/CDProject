package logging;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class FileLogger implements ILogger{
    String filename;
    File obj;

    @Override
    public void write(String message) {

    }

    public FileLogger(String fileName) {
        this.filename=fileName;
        try {
            File myObj = new File(filename);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                obj=myObj;
            } else {
                System.out.println("File already exists.");
                    }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    @Override
    public void write(String message,long time, String measuresec) {

           String fMessage=message+ time + " " + measuresec;
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(fMessage);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
    }

    @Override
    public void writeTime(String message, long time, TimeUnit timeUnit) {
        String fMessage=message+ time + " " + timeUnit;
    }
    @Override
    public void write1(String message, String measures2) {

    }
}

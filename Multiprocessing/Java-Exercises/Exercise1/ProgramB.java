import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class ProgramB {
    public static void main(String[] args) {
        // Create the process with ProcessBuilder class
        ProcessBuilder builder = new ProcessBuilder("java", "ProgramA");
        Process process = builder.start();

        // Write the input for the program
        Writer writer = new BufferedWriter(
            new OutputStreamWriter(process.getOutputStream())
        );
        writer.write("This is B text");
        writer.flush();
        writer.close();

        //Read the outpur of the program
        BufferedReader bfReader = new BufferedReader(
            new InputStreamReader(process.getInputStream())
        );
        String line;
        while ((line = bfReader.readLine()) != null) {
            System.out.println(line);
        }
        bfReader.close();
    }
}

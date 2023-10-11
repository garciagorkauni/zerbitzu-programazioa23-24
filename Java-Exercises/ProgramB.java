import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class ProgramB {
    public static void main(String[] args) {
        try {
            // Create the procesBuilder to execute the command
            ProcessBuilder processBuilder = new ProcessBuilder("java", "ProgramA");
            Process processA = processBuilder.start();

            // Get the outputStream of program A to enter its input
            OutputStream outputStreamA = processA.getOutputStream();
            Writer writerA = new BufferedWriter(new OutputStreamWriter(outputStreamA));
            
            // Get the input of the user
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Write the input of programA: ");
            String inputA = br.readLine();
            writerA.write(inputA + "\n");
            writerA.flush();
            writerA.close();

            // Read and show the output of program A
            BufferedReader readerA = new BufferedReader(new InputStreamReader(processA.getInputStream()));
            String lineA;
            while ((lineA = readerA.readLine()) != null) {
                System.out.println("ProgramA's output: " + lineA);
            }

            // Wait to program A finishes
            int exitCodeA = processA.waitFor();
            System.out.println("programA executed with exit code " + exitCodeA);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}


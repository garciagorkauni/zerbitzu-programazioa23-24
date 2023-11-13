import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Program {
    public static void main(String[] args) {
        // Create the process using Runtime class
        Process process = Runtime.getRuntime().exec("cmd /c dir");

        // Catch the output and print it
        BufferedReader bfReader = new BufferedReader(
            new InputStreamReader(process.getInputStream())
        );
        String line;
        while((line = bfReader.readLine()) != null){
            System.out.println(line);
        }
        bfReader.close();
    }
}

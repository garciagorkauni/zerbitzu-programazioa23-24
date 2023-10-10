import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class ProgramB {
    public static void main(String[] args) {
        String line;
        try {

            Process hijo = new ProcessBuilder(".//ProgramA").start();
            BufferedReader br = new BufferedReader(new InputStreamReader(hijo.getInputStream()));
            PrintStream ps = new PrintStream(hijo.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            // Ctrl-D to get out the loop
            while ((line = in.readLine()) != null) 
            {
                ps.println(line);
                ps.flush();
                if ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
            System.out.println(ps);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}


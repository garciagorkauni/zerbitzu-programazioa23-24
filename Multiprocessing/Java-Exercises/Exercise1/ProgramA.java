import java.util.Scanner;

public class ProgramA {
    public static void main(String[] args) {
        // Define the atributes
        String text;
        Scanner in = new Scanner(System.in);
        
        // Ask for the string, change and save it
        System.out.print("Enter some text: ");
        text = in.next();
        text = text.concat(" (Aded text)");

        // Show the modified text
        System.out.println("The modified text: " + text);

        // Close the scanner
        in.close();
    }
}

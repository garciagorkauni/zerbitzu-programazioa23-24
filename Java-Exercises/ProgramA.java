import java.util.Scanner;

public class ProgramA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String inputString = scanner.nextLine();
        scanner.close();

        String additionalString = " (Extra text)";
        String result = inputString + additionalString;

        System.out.println(result);
    }
}
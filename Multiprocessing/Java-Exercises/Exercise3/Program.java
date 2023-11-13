public class Program {
    public static void main(String[] args) {
        // Create the process
        Process process = Runtime.getRuntime().exec("cmd C/ notepad.exe");
    }
}

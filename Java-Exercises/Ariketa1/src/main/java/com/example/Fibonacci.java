package Ariketa1.src.main.java.com.example;

import java.util.Scanner;

public class Fibonacci extends Thread {

    int n;
    int[] suma;

    public Fibonacci(int n) {
        this.n = n;
        suma = new int[n];
    }

    public void run() {
        suma[0] = 0;
        suma[1] = 1;
        for (int i = 2; i < n; i++) {
            suma[i] = suma[i - 1] + suma[i - 2];
        }
    }

    public void printSeries() {
        for (int i = 0; i < n; i++) {
            System.out.print(suma[i] + " ");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Sartu sententziaren luzeera: ");
        int n = sc.nextInt();

        Fibonacci fibonacci = new Fibonacci(n);
        fibonacci.start();
        try {
            fibonacci.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        fibonacci.printSeries();
    }
}
